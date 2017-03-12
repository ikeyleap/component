package com.ikeyleap.ctrl.component.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class KXBaseContainers<T extends Component> extends JPanel {
	private final static double GOLDEN_RATIO = 0.618;
	private final JLabel KXLabel = new JLabel("");
	private final T comp;
	private int width;

	public KXBaseContainers(JLabel KXLabel, T comp, Component parent) {
		this.KXLabel.setText(KXLabel.getText());
		this.comp = comp;
		this.width = parent.getWidth();
		initialize();
	}

	public KXBaseContainers(String label, T comp, Component parent) {
		this.KXLabel.setText(label);
		this.comp = comp;
		this.width = parent.getWidth();
		initialize();
	}

	private void initialize() {
		setOpaque(false);

		// Create Layout
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, this.comp, 0, SpringLayout.EAST, this.KXLabel);
		layout.putConstraint(SpringLayout.SOUTH, this.comp, 0, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, this.comp, 5, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, this.KXLabel, 5, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, this.KXLabel, 0, SpringLayout.SOUTH, this);
		setLayout(layout);
		int h = (int) this.comp.getPreferredSize().getHeight();
		this.KXLabel.setPreferredSize(new Dimension((int) (this.width * (1 - GOLDEN_RATIO)), h));

		this.KXLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		this.KXLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		add(this.KXLabel);

		add(this.comp);
		// Do layout formatting

	}

	/**
	 * @return the comp
	 */
	public T getComp() {
		return comp;
	}
}
