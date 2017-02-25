package com.ikeyleap.ctrl;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;
import com.ikeyleap.ctrl.component.JPromptbox;

public class AppTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8440626069723637685L;
	private JPanel contentPane;
	private JPromptbox promptbox;
	

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
		setBounds(100, 100, 450, 300);
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
		dataList.add(new Person("AndeySablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
		dataList.add(new Person("Mikaylor", "taylor@instantiations.com", "503-598-4900", "", ""));
		dataList.add(new Person("Eriayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
		dataList.add(new Person("Danubel", "dan@instantiations.com", "503-598-4900", "", ""));
		
		promptbox = new JPromptbox(dataList, Person.class);
		this.contentPane.add(promptbox);
	}

}
