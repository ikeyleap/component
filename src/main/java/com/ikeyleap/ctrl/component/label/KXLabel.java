package com.ikeyleap.ctrl.component.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @blog http://www.micmiu.com
 * @author Michael
 */
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

//	public void paint(Graphics g) {
//		super.paint(g);
//
//		Rectangle r = g.getClipBounds();
//		int xoffset = 0, yoffset = 0, pointX = 0, pointY = 0, point2X = 0, point2Y = 0;
//
//		// 根据border的设置 计算出下划线的起止Point
//		if (null != this.getBorder() && null != this.getBorder().getBorderInsets(this)) {
//			Insets inserts = this.getBorder().getBorderInsets(this);
//			// System.out.println(inserts);
//			xoffset = inserts.left;
//			yoffset = inserts.bottom;
//		}
//		pointX = xoffset;
//		pointY = point2Y = r.height - yoffset - getFontMetrics(getFont()).getDescent();
//		point2X = pointX + getFontMetrics(getFont()).stringWidth(getText());
//		if (null != underLineColor) {
//			g.setColor(underLineColor);
//		}
//
//		g.drawLine(pointX, pointY, point2X, point2Y);
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("JLabe with Under Line");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(new Dimension(300, 200));
		f.setLayout(new FlowLayout());

		KXLabel label1 = new KXLabel("Label下划线");
		f.add(label1);

		KXLabel label2 = new KXLabel("Label下划线");
		label2.setUnderLineColor(Color.BLUE);
		f.add(label2);

		KXLabel label3 = new KXLabel("Label(Border)下划线");
		label3.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		// label3.setUnderLineColor(Color.BLUE);
		label3.setPreferredSize(new Dimension(200, 16));
		f.add(label3);

		// f.pack();
		f.setVisible(true);

	}
}