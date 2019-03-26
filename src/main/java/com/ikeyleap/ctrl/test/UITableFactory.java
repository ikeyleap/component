package com.ikeyleap.ctrl.test;
import com.ikeyleap.ctrl.component.table.KXListTable;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public final class UITableFactory {
	/**
	 * @wbp.factory
	 */
	public static KXListTable createKXListTable() {
		KXListTable listTable = new KXListTable();
		return listTable;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source table t
	 */
	public static KXListTable createKXListTable(JTable table) {
		KXListTable listTable = new KXListTable(table);
		return listTable;
	}
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source dm new ca.odell.glazedlists.swing.DefaultEventTableModel(filteredMP3s, tf)
	 */
	public static JTable createJTable(TableModel dm) {
		JTable table = new JTable(dm);
		return table;
	}
}