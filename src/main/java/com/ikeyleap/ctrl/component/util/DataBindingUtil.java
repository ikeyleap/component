//package com.ikeyleap.ctrl.component.util;
//
//import java.beans.IntrospectionException;
//import java.beans.PropertyDescriptor;
//import java.util.List;
//
//import javax.swing.JTable;
//
//import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
//import org.jdesktop.beansbinding.BeanProperty;
//import org.jdesktop.swingbinding.JTableBinding;
//import org.jdesktop.swingbinding.SwingBindings;
//
//import com.ikeyleap.ctrl.component.model.KXTableModel;
//
//import cn.hutool.core.bean.BeanUtil;
//
//public class DataBindingUtil {
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	public static void initDataBindings(KXTableModel model, Class clazz, JTable table) {
//		JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, model,
//				BeanProperty.create("dataList"), table);
//		PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(clazz);
//		for (PropertyDescriptor pd : props) {
//			if (!"class".equals(pd.getName())) {
//				jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
//						.setEditable(false);
//			}
//		}
//
//		jTableBinding.setEditable(false);
//		jTableBinding.bind();
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static void initDataBindings(List list, Class clazz, JTable table) {
//		JTableBinding jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, list, table);
//		PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(clazz);
//		for (PropertyDescriptor pd : props) {
//			if (!"class".equals(pd.getName())) {
//				jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
//						.setEditable(false);
//			}
//		}
//
//		jTableBinding.setEditable(false);
//		jTableBinding.bind();
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	public static void initDataBindings(Class clazz, JTableBinding jTableBinding) throws IntrospectionException {
//		PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(clazz);
//		for (PropertyDescriptor pd : props) {
//			if (!"class".equals(pd.getName())) {
//				jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName())
//						.setEditable(false);
//			}
//		}
//	}
//
//	@SuppressWarnings("rawtypes")
//	public static boolean hasId(Class clazz) {
//		boolean hasid = false;
//		PropertyDescriptor[] props = BeanUtil.getPropertyDescriptors(clazz);
//		for (PropertyDescriptor dp : props) {
//			if ("id".equals(dp.getName()))
//				hasid = true;
//		}
//		return hasid;
//	}
//
////	public static String getProperty(Object object, String name) throws Exception {
////		if (object != null) {
////			PropertyDescriptor proDescriptor = new PropertyDescriptor(name, object.getClass());
////			Object displayName = proDescriptor.getReadMethod().invoke(object);
////			return displayName.toString();
////		} else
////			return null;
////	}
//
//}
