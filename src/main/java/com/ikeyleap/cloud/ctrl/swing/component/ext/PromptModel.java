package com.ikeyleap.cloud.ctrl.swing.component.ext;

import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeListener;

public interface PromptModel<T> {

	T getValue();

	void setValue(T value);

	boolean isSelected();

	void setSelected(boolean selected);

	void addChangeListener(ChangeListener changeListener);

	void removeChangeListener(ChangeListener changeListener);

	void addPropertyChangeListener(PropertyChangeListener listener);

	void removePropertyChangeListener(PropertyChangeListener listener);

}
