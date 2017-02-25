package com.ikeyleap.ctrl.component.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconUtil {

	public static ImageIcon scale(ImageIcon icon, int width, int height) {
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}

	public static ImageIcon scale(ImageIcon icon) {
		return scale(icon, 16, 16);
	}

}
