package com.ikeyleap.ctrl.component.label;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 创建带下划线的自定义标签组件
 * 示例代码：KXLabel label1 = new KXLabel("Label下划线");
 * label1.setUnderLineColor(Color.BLUE);
 * label1.setPreferredSize(new Dimension(200, 16));
 * label1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
 * @author ikeyleap
 */
@Getter
@Setter
@SuppressWarnings("serial")
public class KXLabel extends JLabel {

	private Color underLineColor;

	public KXLabel(String text) {
		super(text);
	}

	/**
	 * @return the underLineColor
	 */
	public Color getUnderLineColor() {
		return underLineColor;
	}

	/**
	 * @param pUnderLineColor
	 *            the underLineColor to set
	 */
	public void setUnderLineColor(Color pUnderLineColor) {
		underLineColor = pUnderLineColor;
	}

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		JFrame f = new JFrame("JLabe with Under Line");
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(new Dimension(300, 200));
//		f.setLayout(new FlowLayout());
//
//		KXLabel label1 = new KXLabel("Label下划线");
//		f.add(label1);
//
//		KXLabel label2 = new KXLabel("Label下划线");
//		label2.setUnderLineColor(Color.BLUE);
//		f.add(label2);
//
//		KXLabel label3 = new KXLabel("Label(Border)下划线");
//		label3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//		// label3.setUnderLineColor(Color.BLUE);
//		label3.setPreferredSize(new Dimension(200, 16));
//		f.add(label3);
//
//		// f.pack();
//		f.setVisible(true);
//
//	}
}