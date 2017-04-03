/**
 * 
 */
package com.ikeyleap.ctrl.component.picker;

import javax.swing.JButton;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.ikeyleap.ctrl.component.util.IconUtil;

import jiconfont.icons.FontAwesome;

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
	
	private void initComponents(){
		JButton datePickerButton = this.getComponentToggleCalendarButton();
		datePickerButton.setText("");
		datePickerButton.setIcon(IconUtil.getIcon(FontAwesome.CALENDAR));
	}

}
