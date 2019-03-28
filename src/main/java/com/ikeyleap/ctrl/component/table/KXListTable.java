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

@SuppressWarnings("serial")
public class KXListTable extends JComponent {
	private final BorderLayout layout = new BorderLayout();
	private final int autoResizeMode = JTable.AUTO_RESIZE_OFF;
	private final Border rowLinesBorder = new EtchedBorder(EtchedBorder.LOWERED, null, null);

	private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
	private boolean isShowGrid = false;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowLines;
	
	private KXTableModel model = new KXTableModel();

	public KXListTable() {
		table = new JTable();
	}

	public KXListTable(KXTableModel model) {
		this.table = new JTable();
		this.model = model;
	}

	public KXListTable(JTable table) {
		this.table = table;
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
	
	public void refresh() {
		rowLines = new RowHeaderTable(table);
		rowLines.setShowGrid(isShowGrid);
		rowLines.setBorder(rowLinesBorder);
		scrollPane.setRowHeaderView(rowLines);
	}

	public KXTableModel getModel() {
		return model;
	}

	public void setModel(KXTableModel model) {
		this.model = model;
	}

}
