/**
 Copyright 2004 Juan Heyns. All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, are
 permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this list of
 conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice, this list
 of conditions and the following disclaimer in the documentation and/or other materials
 provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY JUAN HEYNS ``AS IS'' AND ANY EXPRESS OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL JUAN HEYNS OR
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those of the
 authors and should not be interpreted as representing official policies, either expressed
 or implied, of Juan Heyns.
 */
package com.ikeyleap.cloud.ctrl.swing.component.back;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
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
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.ikeyleap.cloud.ctrl.swing.component.ext.constraints.DateSelectionConstraint;

/**
 * Created on 25 Mar 2004 Refactored 21 Jun 2004 Refactored 14 May 2009
 * Refactored 16 April 2010 Updated 26 April 2010 Updated 10 August 2012 Updated
 * 6 Jun 2015
 *
 * @author Juan Heyns
 * @author JC Oosthuizen
 * @author Yue Huang
 */
public class JPromptbox extends JComponent implements Promptbox {

	private static final long serialVersionUID = 2814777654384974503L;

	private Popup popup;
	private JFormattedTextField formattedTextField;
	private JButton button;

	private JPromptPanel promptPanel;

	/**
	 * Create a JDatePicker with a default calendar model.
	 */
	public JPromptbox() {
		this(new JPromptPanel());
	}

	/**
	 * Create a JDatePicker with an initial value, with a UtilCalendarModel.
	 *
	 * @param value
	 *            the initial value
	 */
	public JPromptbox(Calendar value) {
		this(new JPromptPanel(value));
	}

	/**
	 * Create a JDatePicker with an initial value, with a UtilDateModel.
	 *
	 * @param value
	 *            the initial value
	 */
	public JPromptbox(java.util.Date value) {
		this(new JPromptPanel(value));
	}

	/**
	 * Create a JDatePicker with an initial value, with a SqlDateModel.
	 *
	 * @param value
	 *            the initial value
	 */
	public JPromptbox(java.sql.Date value) {
		this(new JPromptPanel(value));
	}

	/**
	 * Create a JDatePicker with a custom date model.
	 *
	 * @param model
	 *            a custom date model
	 */
	public JPromptbox(PromptModel<?> model) {
		this(new JPromptPanel(model));
	}

	/**
	 * You are able to set the format of the date being displayed on the label.
	 * Formatting is described at:
	 *
	 * @param promptPanel
	 *            The DatePanel to use
	 */
	private JPromptbox(JPromptPanel promptPanel) {
		this.promptPanel = promptPanel;

		// Initialise Variables
		popup = null;
		promptPanel.setBorder(
				BorderFactory.createLineBorder(getColors().getColor(ComponentColorDefaults.Key.POPUP_BORDER)));
		InternalEventHandler internalEventHandler = new InternalEventHandler();

		// Create Layout
		SpringLayout layout = new SpringLayout();
		setLayout(layout);

		// Create and Add Components
		// Add and Configure TextField
		formattedTextField = new JFormattedTextField(new DateComponentFormatter());
		PromptModel<?> model = promptPanel.getModel();
		setTextFieldValue(formattedTextField, model.getYear(), model.getMonth(), model.getDay(), model.isSelected());
		formattedTextField.setEditable(false);
		add(formattedTextField);
		layout.putConstraint(SpringLayout.WEST, formattedTextField, 0, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, this, 0, SpringLayout.SOUTH, formattedTextField);

		// Add and Configure Button
		button = new JButton();
		button.setFocusable(true);
		Icon icon = ComponentIconDefaults.getInstance().getPopupButtonIcon();
		button.setIcon(icon);
		if (icon == null) {
			// reset to caption
			button.setText("...");
		} else {
			// remove text
			button.setText("");
		}
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

	private static ComponentColorDefaults getColors() {
		return ComponentColorDefaults.getInstance();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdatepicker.JDatePicker#setTextEditable(boolean)
	 */
	public void setTextEditable(boolean editable) {
		formattedTextField.setEditable(editable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdatepicker.JDatePicker#isTextEditable()
	 */
	public boolean isTextEditable() {
		return formattedTextField.isEditable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdatepicker.JDatePicker#setButtonFocusable(boolean)
	 */
	public void setButtonFocusable(boolean focusable) {
		button.setFocusable(focusable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jdatepicker.JDatePicker#getButtonFocusable()
	 */
	public boolean getButtonFocusable() {
		return button.isFocusable();
	}


	public PromptPanel getJPromptInstantPanel() {
		return promptPanel;
	}

	/**
	 * Called internally to popup the dates.
	 */
	private void showPopup() {
		if (popup == null) {
			PopupFactory fac = new PopupFactory();
			Point xy = getLocationOnScreen();
			promptPanel.setVisible(true);
			popup = fac.getPopup(this, promptPanel, (int) xy.getX(), (int) (xy.getY() + this.getHeight()));
			popup.show();
		}		

	}

	/**
	 * Called internally to hide the popup dates.
	 */
	private void hidePopup() {
		if (popup != null) {
			popup.hide();
			popup = null;
		}
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

	public boolean isShowYearButtons() {
		return promptPanel.isShowYearButtons();
	}

	public void setDoubleClickAction(boolean doubleClickAction) {
		promptPanel.setDoubleClickAction(doubleClickAction);
	}

	public void setShowYearButtons(boolean showYearButtons) {
		promptPanel.setShowYearButtons(showYearButtons);
	}

	private void setTextFieldValue(JFormattedTextField textField, int year, int month, int day, boolean isSelected) {
		if (!isSelected) {
			textField.setValue(null);
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day, 0, 0, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			textField.setValue(calendar);
		}
	}

	public void addDateSelectionConstraint(DateSelectionConstraint constraint) {
		promptPanel.addDateSelectionConstraint(constraint);
	}

	public void removeDateSelectionConstraint(DateSelectionConstraint constraint) {
		promptPanel.removeDateSelectionConstraint(constraint);
	}

	public void removeAllDateSelectionConstraints() {
		promptPanel.removeAllDateSelectionConstraints();
	}

	public Set<DateSelectionConstraint> getDateSelectionConstraints() {
		return promptPanel.getDateSelectionConstraints();
	}

	public int getTextfieldColumns() {
		return formattedTextField.getColumns();
	}

	public void setTextfieldColumns(int columns) {
		formattedTextField.setColumns(columns);
	}

	@Override
	public void setVisible(boolean aFlag) {
		if (!aFlag) {
			hidePopup();
		}
		super.setVisible(aFlag);
	}

	@Override
	public void setEnabled(boolean enabled) {
		button.setEnabled(enabled);
		promptPanel.setEnabled(enabled);
		formattedTextField.setEnabled(enabled);

		super.setEnabled(enabled);
	}

	/**
	 * This internal class hides the public event methods from the outside
	 */
	private class InternalEventHandler implements ActionListener, HierarchyBoundsListener, ChangeListener,
			PropertyChangeListener, AWTEventListener {

		public void ancestorMoved(HierarchyEvent arg0) {
			hidePopup();
		}

		public void ancestorResized(HierarchyEvent arg0) {
			hidePopup();
		}

		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == button) {
				if (popup == null) {
					showPopup();
				} else {
					hidePopup();
				}
			} else if (arg0.getSource() == promptPanel) {
				hidePopup();
			}
		}

		public void stateChanged(ChangeEvent arg0) {
			if (arg0.getSource() == promptPanel.getModel()) {
				PromptModel<?> model = promptPanel.getModel();
				setTextFieldValue(formattedTextField, model.getYear(), model.getMonth(), model.getDay(),
						model.isSelected());
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
				@SuppressWarnings("rawtypes")
				PromptModel model = new UtilCalendarModel(value);
				// check constraints
				if (!promptPanel.checkConstraints(model)) {
					// rollback
					formattedTextField.setValue(evt.getOldValue());
					return;
				}
				promptPanel.getModel().setDate(value.get(Calendar.YEAR), value.get(Calendar.MONTH),
						value.get(Calendar.DATE));
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
					hidePopup();
				}
			}
		}

	}

}
