package com.ikeyleap.ctrl.component.promptbox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import javax.swing.text.DefaultFormatter;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.ELProperty;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import cn.hutool.core.bean.BeanUtil;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

@SuppressWarnings("serial")
public class JPromptbox extends JComponent implements Promptbox {

	private JFormattedTextField formattedTextField;
	private JButton button;

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	private JDialog dialog;

	private Object selectedElement;

	@SuppressWarnings("rawtypes")
	private Class clazz;

	@SuppressWarnings("rawtypes")
	public EventList dataList = new BasicEventList();

	/**
	 * @return the dataList
	 */
	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setDataList(EventList dataList) {
		this.dataList = dataList;
	}

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public JPromptbox() {
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public JPromptbox(EventList dataList) {
		this.dataList = dataList;
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public JPromptbox(EventList dataList, Class clazz) {
		this.clazz = clazz;
		this.dataList = dataList;
		initialize();
	}

	/**
	 * @return the formattedTextField
	 */
	public JFormattedTextField getFormattedTextField() {
		return formattedTextField;
	}

	/**
	 * @param formattedTextField the formattedTextField to set
	 */
	public void setFormattedTextField(JFormattedTextField formattedTextField) {
		this.formattedTextField = formattedTextField;
	}

	/**
	 * @return the selectedElement
	 */
	public Object getSelectedElement() {
		return selectedElement;
	}

	/**
	 * @param selectedElement the selectedElement to set
	 */
	public void setSelectedElement(Object selectedElement) {
		Object oldElement = selectedElement;
		this.selectedElement = selectedElement;
		changeSupport.firePropertyChange("selectedElement", oldElement, selectedElement);
	}

	private void showDialog() {
		dialog = new ShowTable(dataList, clazz, this);
		dialog.setTitle("请选择。。。");
		dialog.setSize(500, 350);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	private void initialize() {
		IconFontSwing.register(FontAwesome.getIconFont());

		// Create Layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// Create and Add Components
		// Add and Configure TextField
		PromptBoxFormatter pbf = new PromptBoxFormatter("{0}的邮箱是{1}", "name,email");
		formattedTextField = new JFormattedTextField(pbf);
		formattedTextField.setEditable(false);
		add(formattedTextField);
		layout.putConstraint(SpringLayout.WEST, formattedTextField, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, formattedTextField);

		// Add and Configure Button
		button = new JButton();
		this.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDialog();
			}
		});
		button.setFocusable(true);
		button.setIcon(IconFontSwing.buildIcon(FontAwesome.SEARCH, 12, new Color(0, 128, 0)));
		add(button);
		layout.putConstraint(SpringLayout.WEST, button, 0, SpringLayout.EAST, formattedTextField);
		layout.putConstraint(SpringLayout.EAST, this, 0, SpringLayout.EAST, button);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, button);

		// Do layout formatting
		int h = (int) button.getPreferredSize().getHeight();
		int w = (int) 300;
		button.setPreferredSize(new Dimension(h, h));
		formattedTextField.setPreferredSize(new Dimension(w - h, h));
		initDataBindings();
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	protected void initDataBindings() {
		ELProperty<Object, Object> objectEvalutionProperty = ELProperty.create("${name}");
		BeanProperty<JFormattedTextField, String> jFormattedTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding<Object, Object, JFormattedTextField, String> autoBinding = Bindings.createAutoBinding(
				UpdateStrategy.READ, selectedElement, objectEvalutionProperty, formattedTextField,
				jFormattedTextFieldBeanProperty);
		autoBinding.bind();
	}

	private class PromptBoxFormatter extends DefaultFormatter {

		private static final long serialVersionUID = -4632778360397986037L;

		private String displayName;
		private String propertis;

		public PromptBoxFormatter(String displayName, String propertis) {
			super();
			this.displayName = displayName;
			this.propertis = propertis;
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			setValue(value);
			String result = "";
			if (value != null) {

				String[] a = propertis.split(",");
				Object[] s = new String[a.length];
				for (int i = 0; i < a.length; i++) {
					String display = (String) a[i];
					try {
						s[i] = BeanUtil.getFieldValue(value, display).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				result = MessageFormat.format(displayName, s);
			}
			return result;
		}

	}
}
