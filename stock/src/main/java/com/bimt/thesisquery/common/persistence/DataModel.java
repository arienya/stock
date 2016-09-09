package com.bimt.thesisquery.common.persistence;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public abstract class DataModel<T> extends BaseModel<T>{

	private static final long serialVersionUID = 1L;

	/**
	 * 实体编号（唯一标识）
	 */
	protected Long id;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Long createUser;
	
	protected int delFlag = -1;	// 删除标识,0删除1有效 
	
	public DataModel() {	
	}
	
	public DataModel(Long id) {
		this();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	@Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        DataModel<?> that = (DataModel<?>) obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
	
	/**
	 * 是否为新数据
	 * @return	true为新数据，false不是
	 */
	public boolean isNew() {
		if (id == null || id == 0l) {
			return true;
		}
		return false;
	}
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
