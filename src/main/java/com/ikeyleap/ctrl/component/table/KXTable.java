package com.ikeyleap.ctrl.component.table;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
import com.ikeyleap.ctrl.component.ext.RowHeaderTableModel;
import lombok.Getter;
import lombok.Setter;


/**
 * 扩展了一个Table，增加了行号、行头和自动调整列宽的功能
 * 直接包裹一个JTable即可使用，如：
 * KXTable table = new KXTable(new JTable(dataModel));
 * add(table, BorderLayout.CENTER);
 *
 * @author ikeyleap
 * @version 1.0
 * @date 2021/11/15 14:34
 * @description KXTable is a customized JTable with row header and auto-resize feature.
 */
@Setter
@Getter
public class KXTable extends JComponent {
    private final BorderLayout layout = new BorderLayout();
    private final int autoResizeMode = JTable.AUTO_RESIZE_OFF;
    private final Border rowLinesBorder = new EtchedBorder(EtchedBorder.LOWERED, null, null);

    private int selectionMode = ListSelectionModel.SINGLE_SELECTION;
    private boolean isShowGrid = false;
    private JScrollPane scrollPane;
    private JTable table;
    private RowHeaderTable rowLines;

    public KXTable() {
        this(new JTable());
    }

    public KXTable(JTable table) {
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

        table.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.INSERT
                    || e.getType() == javax.swing.event.TableModelEvent.DELETE
                    || e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                ((RowHeaderTableModel) rowLines.getModel()).setRowCount(table.getRowCount());
                rowLines.getSelectionModel().clearSelection();
                rowLines.revalidate(); // 更新行号组件。消除残影
            }
        });
    }

    public void refresh() {
        rowLines = new RowHeaderTable(table);
        rowLines.setShowGrid(isShowGrid);
        rowLines.setBorder(rowLinesBorder);
        scrollPane.setRowHeaderView(rowLines);
    }


}
