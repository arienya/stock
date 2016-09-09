package com.bimt.thesisquery.common.persistence;

import java.util.List;

/**
 * @author xufeng
 *
 */
public interface BaseDao<T> {
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(Long id);
	
	/**
	 * 获取单条数据
	 * @param model
	 * @return
	 */
	public T get(T model);
	
	/**
	 * 查询数据列表
	 * @param model
	 * @return
	 */
	public List<T> findList(T model);
}
