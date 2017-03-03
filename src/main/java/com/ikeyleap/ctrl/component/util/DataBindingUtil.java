package com.ikeyleap.ctrl.component.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

import javax.swing.JTable;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.ikeyleap.ctrl.component.KXTableModel;

public class DataBindingUtil {
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void initDataBindings(KXTableModel model, Class clazz, JTable table){
		JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, model, BeanProperty.create("dataList"), table);
		PropertyDescriptor[] props;
		try {
			props = getProps(clazz);
			for (PropertyDescriptor pd : props) {
				if (!"class".equals(pd.getName())) {
					jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
							.setEditable(false);
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initDataBindings(List list, Class clazz, JTable table){
		JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, list, table);
		PropertyDescriptor[] props;
		try {
			props = getProps(clazz);
			for (PropertyDescriptor pd : props) {
				if (!"class".equals(pd.getName())) {
					jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
							.setEditable(false);
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		
		jTableBinding.setEditable(false);
		jTableBinding.bind();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initDataBindings(Class clazz, JTableBinding jTableBinding) throws IntrospectionException {
		PropertyDescriptor[] props = getProps(clazz);
		for (PropertyDescriptor pd : props) {
			if (!"class".equals(pd.getName())) {
				jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
						.setEditable(false);
			}
		}
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
				if ("id".equals(dp.getName()))
					hasid = true;
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return hasid;
	}

	public static String getProperty(Object object, String name) throws Exception {
		if (object != null) {
			PropertyDescriptor proDescriptor = new PropertyDescriptor(name, object.getClass());
			Object displayName = proDescriptor.getReadMethod().invoke(object);
			return displayName.toString();
		} else
			return null;
	}

}
