package com.ikeyleap.ctrl;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.ctrl.component.promptbox.JPromptbox;
import com.ikeyleap.ctrl.component.table.KXTable;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import java.awt.SystemColor;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class AppTest extends JFrame {

	private JPanel contentPane;
	private JPromptbox promptbox;
	public JPanel panel;
	private KXTable table;
	public JPanel panel_1;
	public JLabel lblNewLabel;
	public JSplitPane splitPane;
	public JPanel panel_2;
	public JPanel panel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
		setBounds(100, 100, 336, 278);
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

		this.panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		Object[][] cellData = { { "row1-col1", "row1-col2" }, { "row2-col1", "row2-col2" } };
		String[] columnNames = { "col1", "col2" };

		table = new KXTable(cellData, columnNames);
		panel.add(table, BorderLayout.CENTER);
		this.contentPane.add(this.panel);
		
		this.panel_1 = new JPanel();
		this.panel_1.setSize(new Dimension(400, 16));
		this.contentPane.add(this.panel_1, BorderLayout.NORTH);
		this.panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		
		this.lblNewLabel = new JLabel("New label");
		this.lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		this.lblNewLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		this.lblNewLabel.setPreferredSize(new Dimension(160, 23));
//		this.panel_1.add(this.lblNewLabel);
		
				promptbox = new JPromptbox(dataList, Person.class);
				this.panel_1.add(this.promptbox);
		
		this.splitPane = new JSplitPane();
		this.splitPane.setBorder(null);
		this.splitPane.setDividerSize(0);
		this.splitPane.setResizeWeight(0.3);
		this.contentPane.add(this.splitPane, BorderLayout.SOUTH);
		
		this.panel_2 = new JPanel();
		this.panel_2.setLayout(new BorderLayout(0, 0));
		JPromptbox promptbox_1 = new JPromptbox(dataList, Person.class);
		promptbox_1.getFormattedTextField().setBackground(SystemColor.info);
		this.panel_2.add(promptbox_1);
		this.splitPane.setRightComponent(this.panel_2);
		
		this.panel_3 = new JPanel();
		this.panel_3.setLayout(new BorderLayout(0, 0));
		this.panel_3.add(this.lblNewLabel);
		this.splitPane.setLeftComponent(this.panel_3);
	}

	public JPanel getPanel() {
		return this.panel;
	}
}
