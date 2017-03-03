package com.ikeyleap.ctrl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.ctrl.component.KXTableModel;
import com.ikeyleap.ctrl.component.table.KXTable;

@SuppressWarnings("serial")
public class BindTable extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public KXTable table;

	public JToolBar toolBar;
	public JButton btnNewButton;

	private KXTableModel<Person> model = new KXTableModel<Person>();

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

		model.addObject(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		model.addObject(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		model.addObject(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		model.addObject(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		model.addObject(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		model.addObject(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person("AndeySablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		model.addObject(new Person("Mikaylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		model.addObject(new Person("Eriayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		model.addObject(new Person("Danubel", "dan@instantiations.com", "503-598-4900", "", ""));

		this.scrollPane = new JScrollPane();

		this.table = new KXTable(model, Person.class);
		this.contentPane.add(this.table, BorderLayout.CENTER);

		this.toolBar = new JToolBar();
		this.contentPane.add(this.toolBar, BorderLayout.NORTH);

		this.btnNewButton = new JButton("New button");
		this.btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.addRow(new Person("11111111111111111", "kosta@nospam.com", "1234567890", "", ""));
			}
		});
		this.toolBar.add(this.btnNewButton);
	}

}
