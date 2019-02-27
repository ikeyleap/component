/**
 * 
 */
package com.ikeyleap.ctrl.component.picker;

import java.awt.Color;

import javax.swing.JButton;

import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

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

	private void initComponents() {
		IconFontSwing.register(FontAwesome.getIconFont());
		JButton timePickerButton = this.getComponentToggleTimeMenuButton();
		timePickerButton.setText("");
		timePickerButton.setIcon(IconFontSwing.buildIcon(FontAwesome.CLOCK_O, 16, new Color(0, 128, 0)));
		this.getSettings().setDisplaySpinnerButtons(true);
	}
}
