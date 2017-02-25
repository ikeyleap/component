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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
import com.ikeyleap.ctrl.component.util.DataBindingUtil;
import com.ikeyleap.ctrl.component.util.IconUtil;

public class ShowTable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785024425768526943L;
	private final JPanel contentPanel = new JPanel();
	public JTable table;
	public JScrollPane scrollPane;

	@SuppressWarnings("rawtypes")
	public List dataList = new ArrayList();

	private JPromptbox parent;
	@SuppressWarnings("rawtypes")
	private JTableBinding jTableBinding;
	public BeanProperty<JTable, Object> jTableBeanProperty;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;

	/**
	 * @return the dataList
	 */
	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

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
	
	@SuppressWarnings("rawtypes")
	public ShowTable(List dataList, JPromptbox _parent) {
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}
	
	@SuppressWarnings("rawtypes")
	public ShowTable(List dataList,Class clazz, JPromptbox _parent) {
		this.clazz = clazz;
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}

	@SuppressWarnings("unchecked")
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

		JButton btnFilerButton = new JButton("");
		btnFilerButton.setBackground(UIManager.getColor("Button.background"));
		btnFilerButton.setToolTipText("过滤");
		btnFilerButton.setIcon(IconUtil.scale(new ImageIcon(ShowTable.class.getResource("/com/ikeyleap/ctrl/component/graphics/filter-tool-black-shape.png")), 16, 16));
		btnFilerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshTable();
			}
		});
		toolBar.add(btnFilerButton);

		try {
			jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, dataList, table);
			DataBindingUtil.initDataBindings(clazz, jTableBinding);
			jTableBinding.setEditable(false);
			bindTable();
			jTableBeanProperty = BeanProperty.create("selectedElement");
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}


	private void bindTable() {
		jTableBinding.bind();
		RowHeaderTable rowHeaderTable = new RowHeaderTable(table, 40);
		rowHeaderTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setRowHeaderView(rowHeaderTable);
	}

	private void refreshTable() {
		jTableBinding.unbind();
		bindTable();
	}
}
