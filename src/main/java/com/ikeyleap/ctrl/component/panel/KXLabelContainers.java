package com.ikeyleap.ctrl.component.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.ikeyleap.ctrl.component.label.KXLabel;

@SuppressWarnings("serial")
public class KXLabelContainers<T extends Component> extends JPanel {
	private final static double GOLDEN_RATIO = 0.618;
	private final KXLabel KXLabel = new KXLabel("");
	private T comp;
	private int width;

	public KXLabelContainers(T comp, Component parent) {
		this("", comp, parent.getWidth());
	}
	
	public KXLabelContainers(String label, T comp, Component parent) {
		this(label, comp, parent.getWidth());
	}
	
	private KXLabelContainers(String label, T comp, int width) {
		this.KXLabel.setText(label);
		this.KXLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		this.KXLabel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		this.comp = comp;
		this.width = width;
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

	/**
	 * @param comp the comp to set
	 */
	public void setComp(T comp) {
		this.comp = comp;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return this.KXLabel.getText();
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.KXLabel.setText(label);
	}
}
