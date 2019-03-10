/*********************************************************************
 * 源代码版权归作者（们）所有
 *
 * 以 Apache License, Version 2.0 方式授权使用，具体参见：
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 ********************************************************************/
package com.ikeyleap.ctrl.component.model;

import com.ikeyleap.ctrl.component.AbstractModelObject;

/**
 * 所有entity类的超类. 
 * 
 * @author 李鹏
 */
public abstract class IdModel extends AbstractModelObject {
	
	private String id;
	

	/**
	 * 静态变量块
	 */
	static {

	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 根据ID来虚拟一个对象，相当于load方法，用于弱关联的外键对象关系
	 * 
	 * @param param 传入的参数ID
	 */
	public void read(String param) {
		this.setId(param);
	}

}