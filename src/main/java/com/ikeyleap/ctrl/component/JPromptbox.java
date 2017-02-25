package com.ikeyleap.ctrl.component;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyDescriptor;
import java.text.ParseException;

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

public class JPromptbox extends JComponent implements Promptbox {

	private static final long serialVersionUID = 2814777654384974503L;

	private JFormattedTextField formattedTextField;
	private JButton button;

	private JDialog dialog;

	private Object selectedElement;

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public JPromptbox() {
		initialize();
	}

	/**
	 * @return the formattedTextField
	 */
	public JFormattedTextField getFormattedTextField() {
		return formattedTextField;
	}

	/**
	 * @param formattedTextField
	 *            the formattedTextField to set
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
	 * @param selectedElement
	 *            the selectedElement to set
	 */
	public void setSelectedElement(Object selectedElement) {
		Object oldElement = selectedElement;
		this.selectedElement = selectedElement;
		changeSupport.firePropertyChange("selectedElement", oldElement, selectedElement);
	}

	private void showDialog() {
		dialog = new ShowTable(this);
		dialog.setTitle("请选择。。。");
		dialog.setSize(400, 300);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.setVisible(true);
	}

	private void initialize() {
		// Create Layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// Create and Add Components
		// Add and Configure TextField
		formattedTextField = new JFormattedTextField(new PromptBoxFormatter("name"));
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
		button.setText("...");
		add(button);
		layout.putConstraint(SpringLayout.WEST, button, 1, SpringLayout.EAST, formattedTextField);
		layout.putConstraint(SpringLayout.EAST, this, 0, SpringLayout.EAST, button);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, button);

		// Do layout formatting
		int h = (int) button.getPreferredSize().getHeight();
		int w = (int) 300;
		button.setPreferredSize(new Dimension(h, h));
		formattedTextField.setPreferredSize(new Dimension(w - h - 1, h));
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

	public static String getProperty(Object object, String name) throws Exception {
		PropertyDescriptor proDescriptor = new PropertyDescriptor(name, object.getClass());
		Object displayName = proDescriptor.getReadMethod().invoke(object);
		System.out.println("get userName:" + displayName.toString());
		return displayName.toString();
	}
	
	private class PromptBoxFormatter extends DefaultFormatter {

		/**
		 * 
		 */
		private static final long serialVersionUID = -4632778360397986037L;
		
		private String displayName;
		
		public PromptBoxFormatter(String displayName) {
			 this.displayName =  displayName;
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			String result = "";
			try {
				result = getProperty(value, displayName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}

	}
}
