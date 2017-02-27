package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.ikeyleap.ctrl.component.ext.RowHeaderTable;

@SuppressWarnings("serial")
public class KXTable extends JComponent {
	private final static int columnWidth = 40;
	private JScrollPane scrollPane;
	private JTable table;
	private RowHeaderTable rowLines;

	public KXTable(int numRows, int numColumns) {
		this.table = new JTable(numRows, numColumns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

	public KXTable(Object[][] rowData, Object[] columnNames) {
		this.table = new JTable(rowData, columnNames) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

	public KXTable(TableModel dm, TableColumnModel cm, ListSelectionModel sm) {
		this.table = new JTable(dm, cm, sm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

	public KXTable(TableModel dm, TableColumnModel cm) {
		this.table = new JTable(dm, cm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

	public KXTable(TableModel dm) {
		this.table = new JTable(dm) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

	public KXTable() {
		table = new JTable() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		initialize();
	}

//	public KXTable(JTable refTable) {
//		this.table = new JTable() {
//			@Override
//			public boolean isCellEditable(int row, int column) {
//				return false;
//			}
//		};
//		table = refTable;
//		initialize();
//	}

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

}
