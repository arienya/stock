package com.bimt.thesisquery.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bimt.thesisquery.common.service.BaseService;
import com.bimt.thesisquery.common.util.NumberUtils;
import com.bimt.thesisquery.dao.CompanyDataDao;
import com.bimt.thesisquery.model.CompanyDataModel;
import com.google.common.collect.Maps;

@Service
@Transactional(readOnly = true)
public class StrategyService extends BaseService<CompanyDataDao, CompanyDataModel> {

	public Map<String ,Integer> strategy1(String code) {
		int l = 0;	// 连涨天数
		String b = "";	// 开始日期
		Map<String ,Integer> result = Maps.newHashMap();// 日期， 连涨天数
		
		CompanyDataModel cd = new CompanyDataModel();
		cd.setCode(code);
		List<CompanyDataModel> list = dao.findList(cd);
		for (CompanyDataModel c : list) {
			if (NumberUtils.bai2double(c.getD()) > 0) {				
				if (l == 0) {
					b = c.getDate();
				}
				l++;
			} else {
				if (l != 0 && !b.equals("")) {
					result.put(b, l);
					l = 0;
					b = "";
				}
			}
		}
		return result;
	}
	
	/**
	 * 得出某个股票连续上涨天的次数
	 * @param code
	 * @return	map   key=z1,z2...z10  value=只上涨一天次数，只上涨两天次数。。。最大10天
	 */
	public Map<String ,Integer> strategy1Result(String code) {
		Map<String ,Integer> result = strategy1(code);
		Collection<Integer> values = result.values();
		
		Map<String ,Integer> result1 = Maps.newHashMap();
		int z1 = 0;	// 只涨一天次数
		int z2 = 0;	// 连涨2天次数
		int z3 = 0;	// 连涨3天次数
		int z4 = 0;	// 连涨4天次数
		int z5 = 0;	// 连涨5天次数
		int z6 = 0;	// 连涨6天次数
		int z7 = 0;	// 连涨7天次数
		int z8 = 0;	// 连涨8天次数
		int z9 = 0;	// 连涨9天次数
		int z10 = 0;	// 连涨10天次数
		
		for (Integer t : values) {
			switch (t) {
			case 1: 
				z1++;
				break;
			case 2: 
				z2++;
				break;
			case 3: 
				z3++;
				break;
			case 4: 
				z4++;
				break;
			case 5: 
				z5++;
				break;
			case 6: 
				z6++;
				break;
			case 7: 
				z7++;
				break;
			case 8: 
				z8++;
				break;
			case 9: 
				z9++;
				break;
			case 10: 
				z10++;
				break;
			}
		}
		result1.put("z1", z1);
		result1.put("z2", z2);
		result1.put("z3", z3);
		result1.put("z4", z4);
		result1.put("z5", z5);
		result1.put("z6", z6);
		result1.put("z7", z7);
		result1.put("z8", z8);
		result1.put("z9", z9);
		result1.put("z10", z10);
		return result1;
	}
	
	/**
	 * 第day天上涨概率     d天上涨次数/d-1天上涨次数+d天上涨次数
	 * @param day
	 */
	public void gailvGra1() {
		List<String> codes = dao.findAllCompanyCode();
		long z1 = 0;	// 所有标只涨1天次数
		long z2 = 0;
		long z3 = 0;
		long z4 = 0;
		long z5 = 0;
		long z6 = 0;
		long z7 = 0;
		long z8 = 0;
		long z9 = 0;
		long z10 = 0;
		for (String code : codes) {
			Map<String, Integer> map = this.strategy1Result(code);
			z1 += map.get("z1");
			z2 += map.get("z2");
			z3 += map.get("z3");
			z4 += map.get("z4");
			z5 += map.get("z5");
			z6 += map.get("z6");
			z7 += map.get("z7");
			z8 += map.get("z8");
			z9 += map.get("z9");
			z10 += map.get("z10");
			System.out.println(code+"计算完成。z1="+z1+", z2="+z2+", z3="+z3+", z4="+z4+", z5="+z5+", z6="+z6+", z7="+z7+", z8="+z8+", z9="+z9);
			System.gc();
		}
		System.out.println("连涨两天后第三天上涨概率："+((z3+z4+z5+z6+z7+z8+z9+z10)/(z2+z3+z4+z5+z6+z7+z8+z9+z10)));
		System.out.println("连涨三天后第四天上涨概率："+((z4+z5+z6+z7+z8+z9+z10)/(z3+z4+z5+z6+z7+z8+z9+z10)));
		System.out.println("连涨四天后第五天上涨概率："+((z5+z6+z7+z8+z9+z10)/(z4+z5+z6+z7+z8+z9+z10)));
		System.out.println("连涨五天后第六天上涨概率："+((z6+z7+z8+z9+z10)/(z7+z8+z9+z10)));
		System.out.println("连涨六天后第七天上涨概率："+((z7+z8+z9+z10)/(z8+z9+z10)));
		System.out.println("连涨七天后第八天上涨概率："+((z8+z9+z10)/(z9+z10)));
		System.out.println("连涨七天后第八天上涨概率："+((z8+z9+z10)/(z9+z10)));
	}
}
