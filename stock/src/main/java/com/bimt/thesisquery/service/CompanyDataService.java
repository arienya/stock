package com.bimt.thesisquery.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bimt.thesisquery.common.service.CrudService;
import com.bimt.thesisquery.dao.CompanyDataDao;
import com.bimt.thesisquery.model.CompanyDataModel;

@Service
@Transactional(readOnly = true)
public class CompanyDataService extends CrudService<CompanyDataDao, CompanyDataModel> {
	
}
