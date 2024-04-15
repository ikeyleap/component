package com.ikeyleap.ctrl.component.model;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import lombok.Getter;

/**
 * @author lipeng
 * 
 */
@Getter
public class KXTableModel<T> extends AbstractModelObject {
    /**
     * -- GETTER --
     *
     * @return the dataList
     */
    private List<T> dataList = CollUtil.newArrayList();

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
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(List<T> dataList) {		
		List<T> oldValue = this.dataList;
		this.dataList = dataList;
		firePropertyChange("dataList", oldValue, dataList);
		firePropertyChange("dataListCount", oldValue.size(), dataList.size());
	}
}