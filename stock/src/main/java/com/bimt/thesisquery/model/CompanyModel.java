package com.bimt.thesisquery.model;

import com.bimt.thesisquery.common.persistence.DataModel;

public class CompanyModel extends DataModel<CompanyModel> {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String market;
	
	public CompanyModel(String code, String name, String market) {
		this.code = code;
		this.name = name;
		this.market = market;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	
}
