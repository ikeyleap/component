package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.ikeyleap.ctrl.component.KXTableModel;
import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
import com.ikeyleap.ctrl.component.util.DataBindingUtil;

@SuppressWarnings("serial")
public class KXTable extends JComponent {
	private final static int columnWidth = 40;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowLines;

	@SuppressWarnings("rawtypes")
	private KXTableModel model;

	public KXTable(int numRows, int numColumns) {
		this.table = new JTable(numRows, numColumns);
		initialize();
	}

	public KXTable(Object[][] rowData, Object[] columnNames) {
		this.table = new JTable(rowData, columnNames);
		initialize();
	}

	public KXTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		this.table = new JTable(dm, cm, sm);
		initialize();
	}

	public KXTable(TableModel dm, TableColumnModel cm) {
		this.table = new JTable(dm, cm);
		initialize();
	}

	public KXTable(TableModel dm) {
		this.table = new JTable(dm);
		initialize();
	}

	public KXTable() {
		table = new JTable();
		initialize();
	}

	@SuppressWarnings("rawtypes")
	public KXTable(KXTableModel model, Class clazz) {
		table = new JTable();
		this.model = model;
		DataBindingUtil.initDataBindings(model, clazz, table);
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		scrollPane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.rowLines = new RowHeaderTable(table, columnWidth);
		this.rowLines.setShowGrid(false);
		this.rowLines.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setRowHeaderView(this.rowLines);
		add(scrollPane, BorderLayout.CENTER);
	}

	/**
	 * @return the scrollPane
	 */
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	/**
	 * @param scrollPane
	 *            the scrollPane to set
	 */
	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
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
}
