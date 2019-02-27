/**
 * 
 */
package com.ikeyleap.ctrl.component.picker;

import java.awt.Color;

import javax.swing.JButton;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

/**
 * @author lipeng
 *
 */
@SuppressWarnings("serial")
public class KXDatePicker extends DatePicker {

	public KXDatePicker() {
		super();
		initComponents();
	}

	public KXDatePicker(DatePickerSettings settings) {
		super(settings);
		initComponents();
	}

	private void initComponents() {
		IconFontSwing.register(FontAwesome.getIconFont());
		JButton datePickerButton = this.getComponentToggleCalendarButton();
		datePickerButton.setText("");
		datePickerButton.setIcon(IconFontSwing.buildIcon(FontAwesome.CALENDAR, 16, new Color(0, 128, 0)));
	}

}
