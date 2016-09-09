package com.bimt.thesisquery.common.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.bimt.thesisquery.common.persistence.CrudDao;
import com.bimt.thesisquery.common.persistence.DataModel;
import com.bimt.thesisquery.common.util.Const;

/**
 * 增删改查基础服务类
 * @author xufeng
 *
 * @param <D>	dao对象
 * @param <T>	model基类
 */
public class CrudService<D extends CrudDao<T>, T extends DataModel<T>> extends BaseService<D, T> {

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public int save(T model) {
		int result = 0;
		if (model.getId() == null || model.getId().longValue() == 0l){
//			if (SystemUtils.getCurrentUser() != null) {
//				model.setCreateUser(SystemUtils.getCurrentUser().getId());
//			}
			model.setCreateTime(new Date());
			model.setUpdateTime(new Date());
			model.setDelFlag(Const.DELETE_FLAG_1);
			result = dao.insert(model);
		}else{
			model.setUpdateTime(new Date());
			result = dao.update(model);
		}
		return result;
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}
	
	/**
	 * 删除数据
	 * @param id
	 */
	@Transactional(readOnly = false)
	public void delete(Long id) {
		dao.delete(id);
	}
}
