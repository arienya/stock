package com.bimt.thesisquery.dao;

import java.util.List;

import com.bimt.thesisquery.common.persistence.CrudDao;
import com.bimt.thesisquery.model.CompanyDataModel;

public interface CompanyDataDao extends CrudDao<CompanyDataModel> {
	
	/**
	 * 获取有数据的公司code
	 * @return
	 */
	public List<String> findAllCompanyCode();
}
