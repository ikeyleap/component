/**
 * 
 */
package com.ikeyleap.ctrl.component.picker;

import javax.swing.JButton;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.ikeyleap.ctrl.component.util.IconUtil;

import jiconfont.icons.FontAwesome;

/**
 * @author lipeng
 *
 */
@SuppressWarnings("serial")
public class KXTimePicker extends TimePicker {

	public KXTimePicker() {
		super();
		initComponents();
	}

	public KXTimePicker(TimePickerSettings settings) {
		super(settings);
		initComponents();
	}

	private void initComponents(){
		JButton timePickerButton = this.getComponentToggleTimeMenuButton();
		timePickerButton.setText("");
		timePickerButton.setIcon(IconUtil.getIcon(FontAwesome.CLOCK_O));
		this.getSettings().setDisplaySpinnerButtons(true);
	}
}
