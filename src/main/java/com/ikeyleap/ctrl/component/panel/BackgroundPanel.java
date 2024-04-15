package com.ikeyleap.ctrl.component.panel;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * 有背景图片的Panel类
 * 
 * @author tntxia
 * 
 */
public class BackgroundPanel extends JPanel {


	private Image image = null;

	public BackgroundPanel(Image image) {
		this.image = image;
	}
	
	public BackgroundPanel(String imaURL) {
		java.net.URL imgURL = getClass().getResource(imaURL);
		ImageIcon icon = new ImageIcon(imgURL);
		this.image = icon.getImage();
	}

	// 固定背景图片，允许这个JPanel可以在图片上添加其他组件
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}