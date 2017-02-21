package com.ikeyleap.cloud.ctrl.swing.component.ext.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

import javax.swing.JTable;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;

public class DataBindingUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initDataBindings(List list, Class clazz, JTable table) throws IntrospectionException {
		JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, list, table);
		PropertyDescriptor[] props = getProps(Person.class);
		for (PropertyDescriptor pd : props) {
			if (!"class".equals(pd.getName())) {
				jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName()).setEditable(false);
			}
		}
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}

	@SuppressWarnings("rawtypes")
	public static PropertyDescriptor[] getProps(Class clazz) throws IntrospectionException {
		// 如果不想把父类的属性也列出来的话，
		// 那getBeanInfo的第二个参数填写父类的信息
		BeanInfo bi = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] props = bi.getPropertyDescriptors();

		return props;
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean hasId(Class clazz) {
		boolean hasid = false;
		PropertyDescriptor[] props;
		try {
			props = getProps(clazz);
			for (PropertyDescriptor dp : props) {
				if ("id".equals(dp.getName())) hasid = true;
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hasid;
	}
}
