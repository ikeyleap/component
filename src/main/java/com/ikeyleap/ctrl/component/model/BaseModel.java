/*********************************************************************
 * 源代码版权归作者（们）所有
 *
 * 以 Apache License, Version 2.0 方式授权使用，具体参见：
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 ********************************************************************/
package com.ikeyleap.ctrl.component.model;

import java.util.Date;

/**
 * 所有entity类的超类. 
 * 
 * @author 李鹏
 */
public abstract class BaseModel extends IdModel {

	private String creator;
	private String lastUpdater;
	private Date createTime;
	private Date lastUpdateTime;
	private String number;
	private String name;
	private String description;
	private String remarks;
	
	
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 静态变量块
	 */
	static {

	}

	/**
	 * @return creater
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creater
	 *            要设置的 creater
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return lastUpdater
	 */
	public String getLastUpdater() {
		return lastUpdater;
	}

	/**
	 * @param lastUpdater
	 *            要设置的 lastUpdater
	 */
	public void setLastUpdater(String lastUpdater) {
		this.lastUpdater = lastUpdater;
	}

	/**
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            要设置的 createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * @param lastUpdateTime
	 *            要设置的 lastUpdateTime
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number
	 *            要设置的 number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            要设置的 description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

//	public void prePersist() {
//		if (StringUtils.isEmpty(getId()))
//			setId(CodeUtil.generate32Uuid() + CodeUtil.generateShortCode(this.getClass().getSimpleName()));
//
//	}
//
//	public void preUpdate() {
//	}
}