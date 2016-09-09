package com.bimt.thesisquery.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bimt.thesisquery.common.persistence.BaseDao;
import com.bimt.thesisquery.common.persistence.BaseModel;
import com.bimt.thesisquery.common.persistence.Page;

/**
 * 查询基础服务类
 * @author xufeng
 *
 * @param <D>
 * @param <T>
 */
@Transactional(readOnly = true)
public abstract class BaseService<D extends BaseDao<T>, T extends BaseModel<T>> {
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T model) {
		return dao.get(model);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T model) {
		return dao.findList(model);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param model
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T model) {
		model.setPage(page);
		page.setList(dao.findList(model));
		return page;
	}
}
