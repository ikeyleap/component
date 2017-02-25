package com.ikeyleap.cloud.ctrl.swing.component.ext.back;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.IntrospectionException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;
import com.ikeyleap.cloud.ctrl.swing.component.ext.util.DataBindingUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PromptBoxPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PromptBoxPanel() {
		initialize(null);
	}
	
	/**
	 * Create the panel.
	 */
	public PromptBoxPanel(List dataList) {
		initialize(dataList);
	}
	private void initialize(List dataList) {
		this.setLayout(new java.awt.BorderLayout());
		this.setSize(200, 180);
		this.setPreferredSize(new java.awt.Dimension(200, 180));
		this.setOpaque(false);
		//-------------------------------------------------------------
//		if (centerPanel == null) {
		JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new java.awt.BorderLayout());
			centerPanel.setOpaque(false);

			JTable table;
			DefaultTableModel tableModel;
			tableModel = new DefaultTableModel(new Object[][] {}, new Object[] {});
			table = new JTable(tableModel) ;
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
						JTable table = (JTable) e.getComponent();
						JOptionPane.showMessageDialog(table, table.getValueAt(table.getSelectedRow(), 0));
					}
				}
			});

			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			JScrollPane scrollPane = new JScrollPane(table);

			try {
				DataBindingUtil.initDataBindings(dataList, Person.class, table);
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}

			if (DataBindingUtil.hasId(Person.class)) {
				TableColumn column_id_data = table.getColumn("id");
				column_id_data.setMaxWidth(0);
				column_id_data.setMinWidth(0);
				column_id_data.setPreferredWidth(0);
			}

			centerPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
//		}
		add(centerPanel, BorderLayout.CENTER);
		//-------------------------------------------------------------
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(table, table.getValueAt(table.getSelectedRow(), 0));
			}
		});
		okButton.setActionCommand("OK");
		southPanel.add(okButton);
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
//				getRootPane().setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		southPanel.add(cancelButton);
		add(southPanel, BorderLayout.SOUTH);
		//------------------------------------------------------------

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		add(toolBar, BorderLayout.NORTH);

		JButton btnNewButton = new JButton("S");
		toolBar.add(btnNewButton);
	}

}
