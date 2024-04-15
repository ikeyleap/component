import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.ctrl.component.model.KXTableModel;
import com.ikeyleap.ctrl.component.table.KXBeanTable;

@SuppressWarnings("serial")
public class BindTable extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public KXBeanTable table;

	public JToolBar toolBar;
	public JButton btnAddButton;

	private KXTableModel<Person> model = new KXTableModel<Person>();
	private JButton btnDelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BindTable frame = new BindTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BindTable() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);

		model.addObject(new Person(new Date().getTime() + "", "Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		model.addObject(new Person(new Date().getTime() + "", "Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		model.addObject(new Person(new Date().getTime() + "", "Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		model.addObject(new Person(new Date().getTime() + "", "Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "AndeySablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		model.addObject(new Person(new Date().getTime() + "", "Mikaylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "Eriayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		model.addObject(new Person(new Date().getTime() + "", "Danubel", "dan@instantiations.com", "503-598-4900", "", ""));

		this.scrollPane = new JScrollPane();

		this.table = new KXBeanTable(model, Person.class);
		this.contentPane.add(this.table, BorderLayout.CENTER);

		this.toolBar = new JToolBar();
		this.contentPane.add(this.toolBar, BorderLayout.NORTH);

		this.btnAddButton = new JButton("Add");
		this.btnAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addRow(new Person(new Date().getTime() + "", "11111111111111111", "kosta@nospam.com", "1234567890", "", ""));
			}
		});
		this.toolBar.add(this.btnAddButton);
		
		this.btnDelButton = new JButton("Del");
		this.btnDelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.delRow();
			}
		});
		this.toolBar.add(this.btnDelButton);
	}

}
