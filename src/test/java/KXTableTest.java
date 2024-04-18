import com.ikeyleap.ctrl.component.table.KXTable;
import lombok.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

@Data
public class KXTableTest {
    private JPanel panel1;
    private JButton button1;
    private KXTable kxTable;

    public KXTableTest() {
        Object[][] cellData = {{"row1-col1", "row1-col2"}, {"row2-col1", "row2-col2"}};
        String[] columnNames = {"col1", "col2"};
        TableModel model = new DefaultTableModel(cellData, columnNames);
        setKxTable(new KXTable(new JTable(model)));
        panel1.add(kxTable, BorderLayout.CENTER);
        button1.addActionListener(e -> {
            System.out.println("Button clicked");
            DefaultTableModel model1 = (DefaultTableModel) kxTable.getTable().getModel();
            model1.addRow(new Object[] {new Date().toString(), "newRow1-col2"});
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("KXTableTest");

        frame.setContentPane(new KXTableTest().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
