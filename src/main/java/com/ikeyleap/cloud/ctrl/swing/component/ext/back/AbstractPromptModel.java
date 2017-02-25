package com.ikeyleap.cloud.ctrl.swing.component.ext.back;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public abstract class AbstractPromptModel<T> implements PromptModel<T> {

	public static final String PROPERTY_VALUE = "value";
	public static final String PROPERTY_SELECTED = "selected";

	private boolean selected;
	private Set<ChangeListener> changeListeners;
	private Set<PropertyChangeListener> propertyChangeListeners;

	public T getValue() {
		if (!selected) {
			return null;
		}
		return null;
	}

	public void setValue(T value) {
		T oldValue = getValue();
		boolean oldSelectedValue = isSelected();

		if (value != null) {
			selected = true;
		} else {
			selected = false;
		}

		fireChangeEvent();
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
		firePropertyChange("selected", oldSelectedValue, this.selected);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		T oldValue = getValue();
		boolean oldSelectedValue = isSelected();
		this.selected = selected;
		fireChangeEvent();
		firePropertyChange(PROPERTY_VALUE, oldValue, getValue());
		firePropertyChange(PROPERTY_SELECTED, oldSelectedValue, this.selected);
	}

	protected AbstractPromptModel() {
		changeListeners = new HashSet<ChangeListener>();
		propertyChangeListeners = new HashSet<PropertyChangeListener>();
		selected = false;
	}

	public synchronized void addChangeListener(ChangeListener changeListener) {
		changeListeners.add(changeListener);
	}

	public synchronized void removeChangeListener(ChangeListener changeListener) {
		changeListeners.remove(changeListener);
	}

	protected synchronized void fireChangeEvent() {
		for (ChangeListener changeListener : changeListeners) {
			changeListener.stateChanged(new ChangeEvent(this));
		}
	}

	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeListeners.add(listener);
	}

	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeListeners.remove(listener);
	}

	protected synchronized void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
			return;
		}

		for (PropertyChangeListener listener : propertyChangeListeners) {
			listener.propertyChange(new PropertyChangeEvent(this, propertyName, oldValue, newValue));
		}
	}

}