package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.ikeyleap.ctrl.component.ext.RowHeaderTable;

@SuppressWarnings("serial")
public class KXTable extends JTable {
	private final static int columnWidth = 40;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowHeaderTable;

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

	public KXTable(JTable refTable) {
		table = refTable;
		initialize();
	}

	private void initialize() {
		BorderLayout layout = new BorderLayout();
		setLayout(layout);

		scrollPane = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		rowHeaderTable = new RowHeaderTable(table, columnWidth);
		this.rowHeaderTable.setShowGrid(false);
		this.rowHeaderTable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setRowHeaderView(rowHeaderTable);
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

}
