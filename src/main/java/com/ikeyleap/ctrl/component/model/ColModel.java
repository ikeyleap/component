/**
 * 
 */
package com.ikeyleap.ctrl.component.model;

/**
 * @author lipeng
 *
 */
public class ColModel {
	private String propertyNames;
	private String columnLabels;
	@SuppressWarnings("rawtypes")
	private Class Type;
	private Boolean editable = Boolean.FALSE;
	
	public ColModel(String propertyNames, String columnLabels, Boolean editable) {
		super();
		this.propertyNames = propertyNames;
		this.columnLabels = columnLabels;
		this.editable = editable;
	}


	public ColModel(String propertyNames, String columnLabels) {
		super();
		this.propertyNames = propertyNames;
		this.columnLabels = columnLabels;
	}

	public ColModel(String columnLabels) {
		super();
		this.columnLabels = columnLabels;
	}

	@SuppressWarnings("rawtypes")
	public ColModel(String propertyNames, String columnLabels, Class type) {
		super();
		this.propertyNames = propertyNames;
		this.columnLabels = columnLabels;
		Type = type;
	}

	@SuppressWarnings("rawtypes")
	public ColModel(String propertyNames, String columnLabels, Class type, Boolean editable) {
		super();
		this.propertyNames = propertyNames;
		this.columnLabels = columnLabels;
		Type = type;
		this.editable = editable;
	}

	public String getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}

	public String getColumnLabels() {
		return columnLabels;
	}

	public void setColumnLabels(String columnLabels) {
		this.columnLabels = columnLabels;
	}

	@SuppressWarnings("rawtypes")
	public Class getType() {
		return Type;
	}

	@SuppressWarnings("rawtypes")
	public void setType(Class type) {
		Type = type;
	}


	public Boolean getEditable() {
		return editable;
	}


	public void setEditable(Boolean editable) {
		this.editable = editable;
	}


}
