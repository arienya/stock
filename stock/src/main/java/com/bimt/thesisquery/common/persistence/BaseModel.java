package com.bimt.thesisquery.common.persistence;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class BaseModel<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 当前实体分页对象
	 */
	protected Page<T> page;
	
//	@JSONField(serialize=false)
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}
}
