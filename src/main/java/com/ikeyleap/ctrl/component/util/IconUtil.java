package com.ikeyleap.ctrl.component.util;

import java.awt.Color;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import jiconfont.IconCode;
import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class IconUtil {

	public static ImageIcon scale(ImageIcon icon, int width, int height) {
		icon.setImage(icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
		return icon;
	}

	public static ImageIcon scale(ImageIcon icon) {
		return scale(icon, 16, 16);
	}

	public static Icon getIcon(IconCode iconCode, float size, Color color) {
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon icon = IconFontSwing.buildIcon(iconCode, size, color);
		return icon;
	}
	
	public static Icon getIcon(IconCode iconCode, float size) {
		return getIcon(iconCode, size, new Color(0, 150, 0));
	}
	
	public static Icon getIcon(IconCode iconCode) {
		return getIcon(iconCode, 16, new Color(0, 150, 0));
	}
}
