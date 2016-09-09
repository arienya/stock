package com.bimt.thesisquery.util;

/**
 * yahoo股票数据接口工具类
 * @author 祁丛生 2013.01.02
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bimt.thesisquery.model.StockDataModel;

public class GetDataFromYahooUtil {

	public static final String YAHOO_FINANCE_URL = "http://table.finance.yahoo.com/table.csv?";
	public static final String YAHOO_FINANCE_URL_TODAY = "http://download.finance.yahoo.com/d/quotes.csv?";

	/**
	 * 根据 股票编码、开始日期、结束日期 获取股票数据
	 * 
	 * @author 祁丛生
	 * @param stockName
	 *            沪市：000000.ss 深市：000000.sz
	 * @param fromDate
	 *            开始日期
	 * @param toDate
	 *            结束日期
	 * @return List<StockDataModel>
	 */
	public static List<StockDataModel> getStockCsvData(String stockName, String fromDate, String toDate) {
		List<StockDataModel> list = new ArrayList<StockDataModel>();
		String[] datefromInfo = fromDate.split("-");
		String[] toDateInfo = toDate.split("-");
		String code = stockName.substring(0, 6);
		;

		String a = (Integer.valueOf(datefromInfo[1]) - 1) + "";// a – 起始时间，月
		String b = datefromInfo[2];// b – 起始时间，日
		String c = datefromInfo[0];// c – 起始时间，年
		String d = (Integer.valueOf(toDateInfo[1]) - 1) + "";// d – 结束时间，月
		String e = toDateInfo[2];// e – 结束时间，日
		String f = toDateInfo[0];// f – 结束时间，年

		String params = "&a=" + a + "&b=" + b + "&c=" + c + "&d=" + d + "&e=" + e + "&f=" + f;
		String url = YAHOO_FINANCE_URL + "s=" + stockName + params;

		URL MyURL = null;
		URLConnection con = null;
		InputStreamReader ins = null;
		BufferedReader in = null;
		try {
			MyURL = new URL(url);
			con = MyURL.openConnection();
			ins = new InputStreamReader(con.getInputStream(), "UTF-8");
			in = new BufferedReader(ins);

			String newLine = in.readLine();// 标题行

			while ((newLine = in.readLine()) != null) {
				String stockInfo[] = newLine.trim().split(",");
				StockDataModel sd = new StockDataModel();
				sd.setCode(code);
				sd.setDate(stockInfo[0]);
				sd.setOpen(Float.valueOf(stockInfo[1]));
				sd.setHigh(Float.valueOf(stockInfo[2]));
				sd.setLow(Float.valueOf(stockInfo[3]));
				sd.setClose(Float.valueOf(stockInfo[4]));
				sd.setVolume(Float.valueOf(stockInfo[5]));
				sd.setAdj(Float.valueOf(stockInfo[6]));
				list.add(sd);
			}

		} catch (Exception ex) {
			return null; // 无交易数据
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
		}
		return list;
	}

	/**
	 * 根据 股票编码、日期 获取股票数据
	 * 
	 * @author 祁丛生
	 * @param stockName
	 *            沪市：000000.ss 深市：000000.sz
	 * @param date
	 *            日期
	 * @return StockDataModel
	 */
	public static StockDataModel getStockCsvData(String stockName, String date) {
		List<StockDataModel> list = getStockCsvData(stockName, date, date);
		return ((list.size() > 0) ? list.get(0) : null);
	}

	/**
	 * 根据 股票编码 获取当天股票数据
	 * 
	 * @author 祁丛生
	 * @param stockName
	 *            沪市：000000.ss 深市：000000.sz
	 * @return StockDataModel
	 */
	public static StockDataModel getStockCsvData(String stockName) {
		String date = String.format("%1$tF", new Date());
		List<StockDataModel> list = getStockCsvData(stockName, date, date);
		return ((list != null && list.size() > 0) ? list.get(0) : null);
	}

//	public static void main(String[] args) {
//		GetDataFromYahooUtil stockUtil = new GetDataFromYahooUtil();
//		StockDataModel sd = stockUtil.getStockCsvData("600629.ss", "2012-12-31");
//		System.out.println(sd);
//	}
	
	public static void main(String[] args) {
        URL ur = null;
        try {
            //搜狐股票行情历史接口
          ur = new URL("http://q.stock.sohu.com/hisHq?code=cn_300228&start=20150101&end=20160908&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp");
            //新浪股票行情历史接口
//            ur = new URL("http://biz.finance.sina.com.cn/stock/flash_hq/kline_data.php?&rand=random(10000)&symbol=sh600000&end_date=20160908&begin_date=20150101&type=plain");
            HttpURLConnection uc = (HttpURLConnection) ur.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ur.openStream(),"GBK"));
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}