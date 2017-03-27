package com.ikeyleap.ctrl.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lipeng
 * 
 */
public class KXTableModel<T> extends AbstractModelObject {
	private List<T> dataList = new ArrayList<T>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addObject(T o) {
		List oldValue = dataList;
		dataList = new ArrayList(dataList);
		dataList.add(o);
		firePropertyChange("dataList", oldValue, dataList);
		firePropertyChange("dataListCount", oldValue.size(), dataList.size());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void removeObject(T o) {
		List oldValue = dataList;
		dataList = new ArrayList(dataList);
		dataList.remove(o);
		firePropertyChange("dataList", oldValue, dataList);
		firePropertyChange("dataListCount", oldValue.size(), dataList.size());
	}

	public int getDataListCount() {
		return dataList.size();
	}

	/**
	 * @return the dataList
	 */
	public List<T> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}