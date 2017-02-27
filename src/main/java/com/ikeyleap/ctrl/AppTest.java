package com.ikeyleap.ctrl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.ctrl.component.promptbox.JPromptbox;
import com.ikeyleap.ctrl.component.table.KXTable;

@SuppressWarnings("serial")
public class AppTest extends JFrame {

	private JPanel contentPane;
	private JPromptbox promptbox;
	public JPanel panel;
	private KXTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppTest frame = new AppTest();
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
	public AppTest() {
		initialize();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 278);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		List dataList = new ArrayList();
		dataList.add(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		dataList.add(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		dataList.add(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		dataList.add(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		dataList.add(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		dataList.add(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		dataList.add(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));
		dataList.add(new Person("AndeySablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		dataList.add(new Person("Mikaylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		dataList.add(new Person("Eriayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		dataList.add(new Person("Danubel", "dan@instantiations.com", "503-598-4900", "", ""));
		this.contentPane.setLayout(new BorderLayout(0, 0));

		promptbox = new JPromptbox(dataList, Person.class);
		this.contentPane.add(promptbox, BorderLayout.NORTH);

		this.panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		table = new KXTable(cellData, columnNames);
		panel.add(table, BorderLayout.CENTER);
		this.contentPane.add(this.panel);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
