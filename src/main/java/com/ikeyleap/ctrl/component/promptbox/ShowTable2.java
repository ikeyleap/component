package com.ikeyleap.ctrl.component.promptbox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import com.ikeyleap.ctrl.FilterListExample.MP3;
import com.ikeyleap.ctrl.component.ext.RowHeaderTable;
import com.ikeyleap.ctrl.component.util.DataBindingUtil;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.FilterList;
import ca.odell.glazedlists.TextFilterator;
import ca.odell.glazedlists.matchers.ThreadedMatcherEditor;
import ca.odell.glazedlists.swing.TextComponentMatcherEditor;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class ShowTable2 extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8785024425768526943L;
	private final JPanel contentPanel = new JPanel();
	public JTable table;
	public JScrollPane scrollPane;

	@SuppressWarnings("rawtypes")
	public List dataList = new ArrayList();

	private JPromptbox parent;
	@SuppressWarnings("rawtypes")
	private JTableBinding jTableBinding;
	public BeanProperty<JTable, Object> jTableBeanProperty;
	
	@SuppressWarnings("rawtypes")
	private Class clazz;
	private JTextField textField;
	
	private EventList eventList = new BasicEventList();

	/**
	 * @return the dataList
	 */
	@SuppressWarnings("rawtypes")
	public List getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	@SuppressWarnings("rawtypes")
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ShowTable2 dialog = new ShowTable2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ShowTable2() {
		initialize();
	}

	/**
	 * Create the dialog.
	 */
	public ShowTable2(JPromptbox _parent) {
		parent = _parent;
		initialize();
	}
	
	@SuppressWarnings("rawtypes")
	public ShowTable2(List dataList, JPromptbox _parent) {
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}
	
	@SuppressWarnings("rawtypes")
	public ShowTable2(List dataList,Class clazz, JPromptbox _parent) {
		this.clazz = clazz;
		parent = _parent;
		this.dataList = dataList;
		initialize();
	}

	@SuppressWarnings("unchecked")
	private void initialize() {
		IconFontSwing.register(FontAwesome.getIconFont());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(null);
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		this.contentPanel.setLayout(new BorderLayout(0, 0));
		table = new JTable();
		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
					System.out.println(">>>>>>>>>>>>>" + jTableBeanProperty.getValue(table));
					parent.setSelectedElement(jTableBeanProperty.getValue(table));
					parent.getFormattedTextField().setValue(parent.getSelectedElement());
					setVisible(false);
				}
			}
		});
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setModel(new DefaultTableModel());

		scrollPane = new JScrollPane(table);
		contentPanel.add(scrollPane);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("确定");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() > 0) {
					parent.setSelectedElement(jTableBeanProperty.getValue(table));
					parent.getFormattedTextField().setValue(parent.getSelectedElement());
					setVisible(false);
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		try {
			jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, dataList, table);
			
			JPanel filterPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) filterPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(filterPanel, BorderLayout.NORTH);
			
			JLabel searchIconLabel = new JLabel(IconFontSwing.buildIcon(FontAwesome.SEARCH, 12, new Color(0, 128, 0)));
			filterPanel.add(searchIconLabel);
			
			textField = new JTextField();
			filterPanel.add(textField);
			textField.setColumns(25);
			
			TextFilterator filterator = new TextFilterator() {
	            public void getFilterStrings(List baseList, Object element) {
	                MP3 mp3 = (MP3) element;
	                baseList.add(mp3.getAlbum());
	                baseList.add(mp3.getArtist());
	                baseList.add(mp3.getSong());
	            }
	        };
	        TextComponentMatcherEditor matcherEditor = new TextComponentMatcherEditor(textField, filterator);
	        FilterList filtered = new FilterList(eventList, new ThreadedMatcherEditor(matcherEditor));
//			table.setModel(new DefaultEventTableModel(filtered, tf));
			
			DataBindingUtil.initDataBindings(clazz, jTableBinding);
			jTableBinding.setEditable(false);
			bindTable();
			jTableBeanProperty = BeanProperty.create("selectedElement");

	        System.out.println("" + (table.getModel()));
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
	}


	private void bindTable() {
		jTableBinding.bind();
		RowHeaderTable rowHeaderTable = new RowHeaderTable(table, 40);
		rowHeaderTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setRowHeaderView(rowHeaderTable);
	}
}
