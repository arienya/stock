package com.bimt.thesisquery.common.persistence;

/**
 * 增删改查DAO基类
 * @author xufeng
 *
 * @param <T>
 */
public interface CrudDao<T> extends BaseDao<T> {
	
	
	/**
	 * 插入数据
	 * @param model
	 * @return
	 */
	public int insert(T model);
	
	/**
	 * 更新数据
	 * @param model
	 * @return
	 */
	public int update(T model);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param id
	 * @see public int delete(T model)
	 * @return
	 */
	public int delete(Long id);
	
	/**
	 * 删除数据（一般为逻辑删除，更新del_flag字段为1）
	 * @param model
	 * @return
	 */
	public int delete(T model);
	
}
