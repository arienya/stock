package com.bimt.thesisquery.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bimt.thesisquery.common.service.CrudService;
import com.bimt.thesisquery.dao.CompanyDao;
import com.bimt.thesisquery.model.CompanyModel;

@Service
@Transactional(readOnly = true)
public class CompanyService extends CrudService<CompanyDao, CompanyModel> {
	
}
