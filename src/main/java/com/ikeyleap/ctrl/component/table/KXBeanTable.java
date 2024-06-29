package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.ikeyleap.ctrl.component.model.KXTableModel;
import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
//import com.ikeyleap.ctrl.component.util.DataBindingUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 扩展JTable，增加行号显示，增加行删除功能，绑定bean数据，支持自动刷新，主要用于查找带回空间
 *
 * @author ikey
 * @version 1.0
 *
 */
@Setter
@Getter
public class KXBeanTable extends JComponent {
	private final BorderLayout layout = new BorderLayout();
	private final int autoResizeMode = JTable.AUTO_RESIZE_OFF;
	private final Border rowLinesBorder = new EtchedBorder(EtchedBorder.LOWERED, null, null);
	
	private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
	private boolean isShowGrid = false;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowLines;


	private KXTableModel model = new KXTableModel();
	
	public KXBeanTable() {
		this(new JTable(), null);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public KXBeanTable(JTable table, List dataList) {
		this.table = table;
		model.setDataList(dataList);
		init();
	}

	@SuppressWarnings("rawtypes")
	public KXBeanTable(KXTableModel model, Class clazz) {
		table = new JTable();
		this.model = model;
//		DataBindingUtil.initDataBindings(model, clazz, table);
		init();
	}
	
	private void initRowLines() {
		rowLines = new RowHeaderTable(table);
		scrollPane.setRowHeaderView(rowLines);
		rowLines.setShowGrid(isShowGrid);
		rowLines.setBorder(rowLinesBorder);
	}
	
	private void initTable() {
		table.setSelectionMode(selectionMode);
		table.setAutoResizeMode(autoResizeMode);
	}
	
	private void init() {
		setLayout(layout);
		scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		initTable();
		initRowLines();
	}


	@SuppressWarnings("unchecked")
	public void addRow(Object o) {
		model.addObject(o);
		refresh();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addAll(List dataList) {
		model.setDataList(dataList);
		refresh();
	}

	@SuppressWarnings("unchecked")
	public void delRow() {
		if (table.getSelectedRow() >= 0) {
			Object o = model.getDataList().get(table.getSelectedRow());
			model.removeObject(o);
			refresh();
		}
	}

	public void refresh() {
		rowLines = new RowHeaderTable(table);
		rowLines.setShowGrid(isShowGrid);
		rowLines.setBorder(rowLinesBorder);
		scrollPane.setRowHeaderView(rowLines);
	}

}
