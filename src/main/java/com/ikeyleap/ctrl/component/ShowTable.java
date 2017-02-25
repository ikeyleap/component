package com.ikeyleap.ctrl.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.BeanProperty;

import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;
import com.ikeyleap.cloud.ctrl.swing.component.ext.util.DataBindingUtil;

public class ShowTable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785024425768526943L;
	private final JPanel contentPanel = new JPanel();
	public JTable table;
	public JScrollPane scrollPane;

	public List<Person> persons = new ArrayList<Person>();

	private JPromptbox parent;
	public BeanProperty<JTable, Object> jTableBeanProperty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowTable dialog = new ShowTable();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowTable() {
		initialize();
	}

	/**
	 * Create the dialog.
	 */
	public ShowTable(JPromptbox _parent) {
		parent = _parent;
		initialize();
	}

	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(null);
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
					parent.setSelectedElement(jTableBeanProperty.getValue(table));
					parent.getFormattedTextField().setValue(parent.getSelectedElement());
					setVisible(false);
				}
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel());

		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("确定");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > 0) {
					parent.setSelectedElement(jTableBeanProperty.getValue(table));
					parent.getFormattedTextField().setValue(parent.getSelectedElement());
					setVisible(false);
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);

		persons.add(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		persons.add(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		persons.add(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		persons.add(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		persons.add(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		persons.add(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		persons.add(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));

		try {
			DataBindingUtil.initDataBindings(persons, Person.class, table);
			jTableBeanProperty = BeanProperty.create("selectedElement");
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}
}
