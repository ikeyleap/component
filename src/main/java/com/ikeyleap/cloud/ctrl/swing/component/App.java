package com.ikeyleap.cloud.ctrl.swing.component;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.cloud.ctrl.swing.component.ext.JPromptbox;
import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1165798769544236635L;
	private JPanel contentPane;

	public JPromptbox promptbox;
	public JInternalFrame login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
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
	public App() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		List dataList = new ArrayList();
		dataList.add(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
		dataList.add(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
		dataList.add(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
		dataList.add(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		dataList.add(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		dataList.add(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		dataList.add(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));

		this.promptbox = new JPromptbox(dataList);
		this.contentPane.add(this.promptbox);
		
	}

}
