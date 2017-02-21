package com.ikeyleap.cloud.ctrl.swing.component.ext;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JPromptbox extends JComponent implements Promptbox {

	private static final long serialVersionUID = 2814777654384974503L;

	private JFormattedTextField formattedTextField;
	private JButton button;

	private JDialog dialog;
	private JPromptPanel promptPanel;

	public JPromptbox() {
		this(new JPromptPanel());
	}
	
	public JPromptbox(List dataList) {
		this(new JPromptPanel(dataList));
	}

	public JPromptbox(PromptModel<?> model, List dataList) {
		this(new JPromptPanel(model, dataList));
	}

	private JPromptbox(JPromptPanel promptPanel) {
		this.promptPanel = promptPanel;

		InternalEventHandler internalEventHandler = new InternalEventHandler();

		// Create Layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// Create and Add Components
		// Add and Configure TextField
		formattedTextField = new JFormattedTextField();
		PromptModel<?> model = promptPanel.getModel();
		formattedTextField.setEditable(false);
		add(formattedTextField);
		layout.putConstraint(SpringLayout.WEST, formattedTextField, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, formattedTextField);

		// Add and Configure Button
		button = new JButton();
		button.setFocusable(true);
		button.setText("...");
		add(button);
		layout.putConstraint(SpringLayout.WEST, button, 1, SpringLayout.EAST, formattedTextField);
		layout.putConstraint(SpringLayout.EAST, this, 0, SpringLayout.EAST, button);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, button);

		// Do layout formatting
		int h = (int) button.getPreferredSize().getHeight();
		int w = (int) promptPanel.getPreferredSize().getWidth();
		button.setPreferredSize(new Dimension(h, h));
		formattedTextField.setPreferredSize(new Dimension(w - h - 1, h));

		// Add event listeners
		addHierarchyBoundsListener(internalEventHandler);
		// addAncestorListener(listener)
		button.addActionListener(internalEventHandler);
		formattedTextField.addPropertyChangeListener("value", internalEventHandler);
		promptPanel.addActionListener(internalEventHandler);
		promptPanel.getModel().addChangeListener(internalEventHandler);
		long eventMask = MouseEvent.MOUSE_PRESSED;
		Toolkit.getDefaultToolkit().addAWTEventListener(internalEventHandler, eventMask);

	}

	public void addActionListener(ActionListener actionListener) {
		promptPanel.addActionListener(actionListener);
	}

	public void removeActionListener(ActionListener actionListener) {
		promptPanel.removeActionListener(actionListener);
	}

	public PromptModel<?> getModel() {
		return promptPanel.getModel();
	}

	public PromptPanel getJPromptInstantPanel() {
		return promptPanel;
	}

	private void showDialog() {
		dialog = new JDialog();
		dialog.setTitle("ceshi");
		dialog.setSize(600, 400);
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setModal(true);
		dialog.add(promptPanel);
		dialog.setVisible(true);
	}

	private void hideDialog() {
		if (dialog != null)
			dialog.setVisible(false);
	}

	private Set<Component> getAllComponents(Component component) {
		Set<Component> children = new HashSet<Component>();
		children.add(component);
		if (component instanceof Container) {
			Container container = (Container) component;
			Component[] components = container.getComponents();
			for (int i = 0; i < components.length; i++) {
				children.addAll(getAllComponents(components[i]));
			}
		}
		return children;
	}

	public boolean isDoubleClickAction() {
		return promptPanel.isDoubleClickAction();
	}

	public void setDoubleClickAction(boolean doubleClickAction) {
		promptPanel.setDoubleClickAction(doubleClickAction);
	}

	public void setVisible(boolean aFlag) {
		if (!aFlag) {
			hideDialog();
		}
		super.setVisible(aFlag);
	}

	public void setEnabled(boolean enabled) {
		button.setEnabled(enabled);
		promptPanel.setEnabled(enabled);
		formattedTextField.setEnabled(enabled);

		super.setEnabled(enabled);
	}

	private class InternalEventHandler implements ActionListener, HierarchyBoundsListener, ChangeListener,
			PropertyChangeListener, AWTEventListener {

		public void ancestorMoved(HierarchyEvent arg0) {
			hideDialog();
		}

		public void ancestorResized(HierarchyEvent arg0) {
			hideDialog();
		}

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == button) {
				showDialog();
			} else if (arg0.getSource() == promptPanel) {
				hideDialog();
			}
		}

		public void stateChanged(ChangeEvent arg0) {
			if (arg0.getSource() == promptPanel.getModel()) {
				PromptModel<?> model = promptPanel.getModel();
			}
		}

		public void propertyChange(PropertyChangeEvent evt) {
			// Short circuit if the following cases are found
			if (evt.getOldValue() == null && evt.getNewValue() == null) {
				return;
			}
			if (evt.getOldValue() != null && evt.getOldValue().equals(evt.getNewValue())) {
				return;
			}
			if (!formattedTextField.isEditable()) {
				return;
			}

			// If the field is editable and we need to parse the date entered
			if (evt.getNewValue() != null) {
				Calendar value = (Calendar) evt.getNewValue();
				PromptModel<?> model = new UtilPromptModel(value);
				formattedTextField.setValue(evt.getOldValue());
				promptPanel.getModel().setSelected(true);
			}

			// Clearing textfield will also fire change event
			if (evt.getNewValue() == null) {
				// Set model value unselected, this will fire an event
				getModel().setSelected(false);
			}
		}

		public void eventDispatched(AWTEvent event) {
			if (MouseEvent.MOUSE_CLICKED == event.getID() && event.getSource() != button) {
				Set<Component> components = getAllComponents(promptPanel);
				boolean clickInPopup = false;
				for (Component component : components) {
					if (event.getSource() == component) {
						clickInPopup = true;
					}
				}
				if (!clickInPopup) {
					hideDialog();
				}
			}
		}

	}
}
