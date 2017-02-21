package com.ikeyleap.cloud.ctrl.swing.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;
import com.ikeyleap.cloud.ctrl.swing.component.ext.util.DataBindingUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class showTable extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public JTable table;
	public JScrollPane scrollPane;

	public List<Person> persons = new ArrayList<Person>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			showTable dialog = new showTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public showTable() {
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
					JOptionPane.showMessageDialog(table, table.getValueAt(table.getSelectedRow(), 0));
				}
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));

		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JToolBar toolBar = new JToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

		persons.add(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		persons.add(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		persons.add(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		persons.add(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		persons.add(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		persons.add(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		persons.add(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));

		try {
			DataBindingUtil.initDataBindings(persons, Person.class, table);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}

	// protected void initDataBindings() {
	// JTableBinding<Person, List<Person>, JTable> jTableBinding = SwingBindings
	// .createJTableBinding(UpdateStrategy.READ, persons, table);
	// //
	// jTableBinding.addColumnBinding(BeanProperty.create("email")).setColumnName("email").setEditable(false);
	// //
	// jTableBinding.addColumnBinding(BeanProperty.create("mobilePhone1")).setColumnName("mobilephone1")
	// .setEditable(false);
	// //
	// jTableBinding.addColumnBinding(BeanProperty.create("mobilePhone2")).setColumnName("mobilephone2")
	// .setEditable(false);
	// //
	// jTableBinding.addColumnBinding(BeanProperty.create("name")).setColumnName("name").setEditable(false);
	// //
	// jTableBinding.addColumnBinding(BeanProperty.create("phone")).setColumnName("phone").setEditable(false);
	// //
	// jTableBinding.setEditable(false);
	// jTableBinding.bind();
	// }
	//
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// protected void initDataBindings(List list, Class clazz) throws
	// IntrospectionException {
	// JTableBinding jTableBinding =
	// SwingBindings.createJTableBinding(UpdateStrategy.READ, list, table);
	// PropertyDescriptor[] props = getProps(Person.class);
	// for (PropertyDescriptor pd : props) {
	// if (!"class".equals(pd.getName())) {
	// jTableBinding.addColumnBinding(BeanProperty.create(pd.getName())).setColumnName(pd.getName()).setEditable(false);
	// }
	// }
	// jTableBinding.setEditable(false);
	// jTableBinding.bind();
	// }
	//
	// @SuppressWarnings("rawtypes")
	// private PropertyDescriptor[] getProps(Class clazz) throws
	// IntrospectionException {
	// // 如果不想把父类的属性也列出来的话，
	// // 那getBeanInfo的第二个参数填写父类的信息
	// BeanInfo bi = Introspector.getBeanInfo(clazz);
	// PropertyDescriptor[] props = bi.getPropertyDescriptors();
	//
	// return props;
	// }
}
