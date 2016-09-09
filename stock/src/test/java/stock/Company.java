package stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.bimt.thesisquery.dto.SohuSearchDTO;
import com.bimt.thesisquery.model.CompanyDataModel;
import com.bimt.thesisquery.model.CompanyModel;
import com.bimt.thesisquery.service.CompanyDataService;
import com.bimt.thesisquery.service.CompanyService;
import com.bimt.thesisquery.service.StrategyService;
import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class) // 表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-context.xml", "classpath:spring-context-jedis.xml" })
public class Company {
	private static final String userAgent = "Mozilla/5.0 (jsoup)";
    private static final int timeout = 5 * 1000;
    
    @Resource
	private CompanyService companyService;
    
    @Resource
	private CompanyDataService companyDataService;
    
    @Resource
	private StrategyService strategyService;
    
//    @Test
    public void insertAllCompany() {
    	List<CompanyModel> list = parseCompany();
    	int i = 0;
    	for (CompanyModel company : list) {
    		companyService.save(company);
    	}
    }
    
    /**
     * 获取所有公司数据进数据库
     */
//    @Test
    public void insertAllCompanyData() {
    	List<CompanyModel> list = parseCompany();
    	
    	for (CompanyModel cm : list) {
    		String json =  null;
            try {
                //搜狐股票行情历史接口
            	URL ur = new URL("http://q.stock.sohu.com/hisHq?code=cn_"+cm.getCode()+"&start=20150901&end=20160908&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp");
                //新浪股票行情历史接口
//                ur = new URL("http://biz.finance.sina.com.cn/stock/flash_hq/kline_data.php?&rand=random(10000)&symbol=sh600000&end_date=20160908&begin_date=20150101&type=plain");
                HttpURLConnection uc = (HttpURLConnection) ur.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream(),"GBK"));
                String line;
                String s = null;
                while((line = reader.readLine()) != null){
                    s = line;
                }
                json = s.substring(22, s.length() - 2);
                SohuSearchDTO ss = JSON.parseObject(json, SohuSearchDTO.class);
                
                if (ss.getStatus() == 0) {
                	List<List<String>> data = ss.getHq();	// 所有数据
                    for (List<String> d : data) {	// 保存每一天数据
                    	CompanyDataModel cd = new CompanyDataModel();
                    	cd.setCode(cm.getCode());
                    	cd.setDate(d.get(0));
                    	cd.setA(d.get(1));
                    	cd.setB(d.get(2));
                    	cd.setC(d.get(3));
                    	cd.setD(d.get(4));
                    	cd.setE(d.get(5));
                    	cd.setF(d.get(6));
                    	cd.setG(d.get(7));
                    	cd.setH(d.get(8));
                    	cd.setI(d.get(9));
                    	companyDataService.save(cd);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error code=" + cm.getCode());
            }
    	}
    }
    
    @Test
    public void gailvGra1() {
    	strategyService.gailvGra1();
    }

	public static void main(String[] args) {
		parseCompany();
	}
	
	/**
	 * 获取所有上市公司
	 * @return
	 */
	public static List<CompanyModel> parseCompany() {
		String url = "http://www.360doc.com/content/14/0125/23/13075110_347895023.shtml";
		
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(userAgent).timeout(timeout).get();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		Elements elements = doc.select("[name=sh] + ul");
		Elements a = elements.get(0).getElementsByTag("a");
		String cm = a.html();
		String[] c = cm.split("\n");
		List<CompanyModel> list = Lists.newArrayList();
		for (String c1 : c) {
			int b = c1.indexOf("(");
			int e = c1.indexOf(")");
			String name = c1.substring(0, b);
			String code = c1.substring(b+1, e);
			if (code.length() != 6) {
				continue;
			}
			CompanyModel company = new CompanyModel(code, name, "sh");
			list.add(company);
		}
		
		elements = doc.select("[name=sz] + ul");
		a = elements.get(0).getElementsByTag("a");
		cm = a.html();
		c = cm.split("\n");
		for (String c1 : c) {
			int b = c1.indexOf("(");
			int e = c1.indexOf(")");
			String name = c1.substring(0, b);
			String code = c1.substring(b+1, e);
			if (code.length() != 6) {
				continue;
			}
			CompanyModel company = new CompanyModel(code, name, "sz");
			list.add(company);
		}
		return list;
	}
}
