package com.ikeyleap.ctrl.component.promptbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.common.collect.Lists;
import com.ikeyleap.ctrl.component.model.ColModel;
import com.ikeyleap.ctrl.test.TestKXListTable;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.swing.DefaultEventTableModel;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class ShowTable extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785024425768526943L;
	private final JPanel contentPanel = new JPanel();
	public JTable table;
	public JScrollPane scrollPane;

	@SuppressWarnings("rawtypes")
	public EventList dataList = new BasicEventList();

	private JPromptbox parent;

	@SuppressWarnings({ "rawtypes", "unused" })
	private Class clazz;
	private JTextField textField;

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
	public ShowTable(EventList dataList, JPromptbox _parent) {
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public ShowTable(EventList dataList, Class clazz, JPromptbox _parent) {
		this.clazz = clazz;
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}

	private void initialize() {
		// build a JTable
		List<ColModel> tableList = Lists.newArrayList();

		tableList.add(new ColModel("number", "编码"));
		tableList.add(new ColModel("name", "名称"));

		IconFontSwing.register(FontAwesome.getIconFont());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(null);
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));

		textField = new JTextField();
		textField.setColumns(25);


		table = //new JTable();
		TestKXListTable.creatTable(TestKXListTable.getTableFormat(tableList), dataList, textField);
		this.table.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("rawtypes")
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
					parent.setSelectedElement(((DefaultEventTableModel)table.getModel()).getElementAt(table.getSelectedRow()));
					parent.getFormattedTextField().setValue(parent.getSelectedElement());
					setVisible(false);
				}
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.setModel(new DefaultTableModel());

//		TableFormat tf = TestKXListTable.getTableFormat(tableList);

		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("确定");
		okButton.addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > 0) {
					parent.setSelectedElement(((DefaultEventTableModel)table.getModel()).getElementAt(table.getSelectedRow()));
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

		JPanel filterPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) filterPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(filterPanel, BorderLayout.NORTH);

		JLabel searchIconLabel = new JLabel(IconFontSwing.buildIcon(FontAwesome.SEARCH, 12, new Color(0, 128, 0)));
		filterPanel.add(searchIconLabel);

		filterPanel.add(textField);

	}

}
