package com.ikeyleap.cloud.ctrl.swing.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.ikeyleap.cloud.ctrl.swing.component.ext.back.PromptModel;
import com.ikeyleap.cloud.ctrl.swing.component.ext.back.PromptPanel;
import com.ikeyleap.cloud.ctrl.swing.component.ext.back.UtilPromptModel;
import com.ikeyleap.cloud.ctrl.swing.component.ext.bean.Person;
import com.ikeyleap.cloud.ctrl.swing.component.ext.util.DataBindingUtil;

public class JPromptPanel extends JComponent implements PromptPanel {

	private static final long serialVersionUID = -2299249311312882915L;

	private Set<ActionListener> actionListeners;
	private boolean doubleClickAction;
	private InternalModel internalModel;
	private InternalController internalController;
	private InternalView internalView;

	@SuppressWarnings("rawtypes")
	private List dataList = new ArrayList();

	/**
	 * @return the dataList
	 */
	public List getDataList() {
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	public JPromptPanel() {
		this(createModel(), new ArrayList());
	}


	public JPromptPanel(List dataList) {
		this(createModel(),dataList);
	}
	
	public JPromptPanel(PromptModel<?> model, List dataList) {
		setDataList(dataList);
		actionListeners = new HashSet<ActionListener>();
		doubleClickAction = false;

		internalModel = new InternalModel(model);
		internalController = new InternalController();
		internalView = new InternalView();

		setLayout(new GridLayout(1, 1));
		add(internalView);
	}

	public static PromptModel<?> createModel() {
		return new UtilPromptModel();
	}

	private static PromptModel<?> createModelFromValue(Object value) {
		throw new IllegalArgumentException("No model could be constructed from the initial value object.");
	}

	public void addActionListener(ActionListener actionListener) {
		actionListeners.add(actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		actionListeners.remove(actionListener);
	}

	private void fireActionPerformed() {
		for (ActionListener actionListener : actionListeners) {
			actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Date selected"));
		}
	}

	public void setDoubleClickAction(boolean doubleClickAction) {
		this.doubleClickAction = doubleClickAction;
	}

	public boolean isDoubleClickAction() {
		return doubleClickAction;
	}

	public PromptModel<?> getModel() {
		return internalModel.getModel();
	}

	@Override
	public void setVisible(boolean aFlag) {
		super.setVisible(aFlag);
	}

	@Override
	public void setEnabled(boolean enabled) {
		internalView.setEnabled(enabled);

		super.setEnabled(enabled);
	}

	private class InternalView extends JPanel {

		private static final long serialVersionUID = -6844493839307157682L;

		private JPanel centerPanel;
		private JPanel southPanel;

		public InternalView() {
			initialise();
		}

		/**
		 * Initialise the control.
		 */
		private void initialise() {
			this.setLayout(new java.awt.BorderLayout());
			this.setSize(200, 180);
			this.setPreferredSize(new java.awt.Dimension(200, 180));
			this.setOpaque(false);
			this.add(getCenterPanel(), java.awt.BorderLayout.CENTER);
			this.add(getSouthPanel(), java.awt.BorderLayout.SOUTH);

			JToolBar toolBar = new JToolBar();
			toolBar.setFloatable(false);
			add(toolBar, BorderLayout.NORTH);

			JButton btnNewButton = new JButton("S");
			toolBar.add(btnNewButton);

		}

		/**
		 * This method initializes southPanel
		 *
		 * @return javax.swing.JPanel
		 */
		private JPanel getSouthPanel() {
			southPanel = new JPanel();
			southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			southPanel.add(okButton);
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setActionCommand("Cancel");
			southPanel.add(cancelButton);

			return southPanel;
		}

		/**
		 * This method initializes centerPanel
		 *
		 * @return javax.swing.JPanel
		 */
		private JPanel getCenterPanel() {
			if (centerPanel == null) {
				centerPanel = new javax.swing.JPanel();
				centerPanel.setLayout(new java.awt.BorderLayout());
				centerPanel.setOpaque(false);

				JTable table;
				DefaultTableModel tableModel;
				tableModel = new DefaultTableModel(new Object[][] {}, new Object[] {});
				table = new JTable(tableModel) {
					private static final long serialVersionUID = -8110501438839938571L;

					public boolean isCellEditable(int row, int column) {
						return false;
					}// 表格不允许被编辑
				};

				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane scrollPane = new JScrollPane(table);
				table.addMouseListener(internalController);

//				dataList.add(new Person("Konstantin Scheglov", "kosta@nospam.com", "1234567890", "", ""));
//				dataList.add(new Person("Alexander Mitin", "mitin@nospam.com", "", "0987654321", ""));
//				dataList.add(new Person("Alexander Lobas", "lobas@nospam.com", "", "", "111-222-333-00"));
//				dataList.add(new Person("Andey Sablin", "sablin@nospam.com", "098765", "", "aaa-vvv-ddd"));
//				dataList.add(new Person("Mike Taylor", "taylor@instantiations.com", "503-598-4900", "", ""));
//				dataList.add(new Person("Eric Clayberg", "clayberg@instantiations.com", "+1 (503) 598-4900", "", ""));
//				dataList.add(new Person("Dan Rubel", "dan@instantiations.com", "503-598-4900", "", ""));

				try {
					DataBindingUtil.initDataBindings(dataList, Person.class, table);
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}

				if (DataBindingUtil.hasId(Person.class)) {
					TableColumn column_id_data = table.getColumn("id");
					column_id_data.setMaxWidth(0);
					column_id_data.setMinWidth(0);
					column_id_data.setPreferredWidth(0);
				}

				centerPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
			}
			return centerPanel;
		}

	}

	private class InternalController implements ActionListener, MouseListener {

		public void actionPerformed(ActionEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {// 点击几次，这里是双击事件
				JTable table = (JTable) e.getComponent();
				JOptionPane.showMessageDialog(table, table.getValueAt(table.getSelectedRow(), 0));
			}
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}

	protected class InternalModel implements TableModel, SpinnerModel, ChangeListener {

		private PromptModel<?> model;
		private Set<ChangeListener> spinnerChangeListeners;
		private Set<TableModelListener> tableModelListeners;

		public InternalModel(PromptModel<?> model) {
			this.spinnerChangeListeners = new HashSet<ChangeListener>();
			this.tableModelListeners = new HashSet<TableModelListener>();
			this.model = model;
			model.addChangeListener(this);
		}

		public PromptModel<?> getModel() {
			return model;
		}

		/**
		 * Part of SpinnerModel, year
		 */
		public void addChangeListener(ChangeListener arg0) {
			spinnerChangeListeners.add(arg0);
		}

		/**
		 * Part of SpinnerModel, year
		 */
		public void removeChangeListener(ChangeListener arg0) {
			spinnerChangeListeners.remove(arg0);
		}

		/**
		 * Part of TableModel, day
		 */
		public void addTableModelListener(TableModelListener arg0) {
			tableModelListeners.add(arg0);
		}

		/**
		 * Part of TableModel, day
		 */
		public void removeTableModelListener(TableModelListener arg0) {
			tableModelListeners.remove(arg0);
		}

		/**
		 * Part of TableModel, day
		 */
		public int getColumnCount() {
			return 7;
		}

		/**
		 * Part of TableModel, day
		 */
		public int getRowCount() {
			return 6;
		}

		/**
		 * Part of TableModel, day
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public Class getColumnClass(int arg0) {
			return Integer.class;
		}

		/**
		 * Part of TableModel, day
		 */
		public boolean isCellEditable(int arg0, int arg1) {
			return false;
		}

		/**
		 * Part of TableModel, day
		 */
		public void setValueAt(Object arg0, int arg1, int arg2) {
		}

		private void fireValueChanged() {
		}

		public void stateChanged(ChangeEvent e) {
			fireValueChanged();
		}

		@Override
		public Object getValue() {
			return null;
		}

		@Override
		public Object getNextValue() {
			return null;
		}

		@Override
		public Object getPreviousValue() {
			return null;
		}

		@Override
		public String getColumnName(int columnIndex) {
			return null;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return null;
		}

		@Override
		public void setValue(Object value) {

		}

	}

}
