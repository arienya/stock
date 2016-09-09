package com.bimt.thesisquery.dto;

import java.util.List;

public class SohuSearchDTO {
	private int status;
	private List<List<String>> hq;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<List<String>> getHq() {
		return hq;
	}
	public void setHq(List<List<String>> hq) {
		this.hq = hq;
	}
	
	
}
