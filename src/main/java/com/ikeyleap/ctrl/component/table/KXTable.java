package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import com.ikeyleap.ctrl.component.KXTableModel;
import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
import com.ikeyleap.ctrl.component.util.DataBindingUtil;

@SuppressWarnings("serial")
public class KXTable extends JComponent {
	private final BorderLayout layout = new BorderLayout();
	private final int autoResizeMode = JTable.AUTO_RESIZE_OFF;
	private Border rowLinesBorder = new EtchedBorder(EtchedBorder.LOWERED, null, null);
	
	private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
	private boolean isShowGrid = false;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowLines;

	@SuppressWarnings("rawtypes")
	private KXTableModel model;
	
	public KXTable() {
		this(new JTable());
	}
	
	public KXTable(JTable table) {
		this.table = table;
		init();
	}

	@SuppressWarnings("rawtypes")
	public KXTable(KXTableModel model, Class clazz) {
		table = new JTable();
		this.model = model;
		DataBindingUtil.initDataBindings(model, clazz, table);
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


	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	/**
	 * @return the rowLines
	 */
	public RowHeaderTable getRowLines() {
		return rowLines;
	}

	/**
	 * @param rowLines
	 *            the rowLines to set
	 */
	public void setRowLines(RowHeaderTable rowLines) {
		this.rowLines = rowLines;
	}

	@SuppressWarnings("unchecked")
	public void addRow(Object o) {
		model.addObject(o);
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

	private void refresh() {
		this.rowLines = new RowHeaderTable(table);
		this.rowLines.setShowGrid(false);
		this.rowLines.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setRowHeaderView(this.rowLines);
	}

	/**
	 * @return the model
	 */
	@SuppressWarnings("rawtypes")
	public KXTableModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	@SuppressWarnings("rawtypes")
	public void setModel(KXTableModel model) {
		this.model = model;
	}
}
