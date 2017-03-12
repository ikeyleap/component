package com.ikeyleap.ctrl.component.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

@SuppressWarnings("serial")
public class KXLabelContainer extends JComponent {
	private JLabel label;
	private JComponent editor;

	private int labelAlignment = 7;
	private int labelLength = -1;

	private PropertyChangeListener propertyChangeListener = new PropertyChangeHandler();

	public KXLabelContainer() {
		this("", null);
	}

	public KXLabelContainer(String labelText) {
		this(labelText, null);
	}

	public KXLabelContainer(JComponent editor) {
		this("", editor);
	}

	public KXLabelContainer(String labelText, JComponent editor) {
		setLayout(new CompositeLayout());
		setBoundLabelText(labelText);
		setBoundEditor(editor);
		setBoundLabelUnderline(false);

		install();
	}

	private void install() {
		installDefault();
		installComponent();
		installListener();
	}

	private void installDefault() {
	}

	private void installComponent() {
		removeAll();
		if (getBoundLabel() != null) {
			add(getBoundLabel(), "label");
		}
		if (getBoundEditor() != null) {
			add(getBoundEditor(), "editor");
		}
	}

	private void installListener() {
		addPropertyChangeListener(this.propertyChangeListener);
	}

	public void setBoundLabel(JLabel label) {
		JLabel old = getBoundLabel();
		if (old != label) {
			this.label = label;
			doChangeAlignment();
			firePropertyChange("boundLabel", old, label);
		}
	}

	public JLabel getBoundLabel() {
		return this.label;
	}

	public void setBoundLabelAlignment(int alignment) {
		int old = getBoundLabelAlignment();
		if (old != alignment) {
			this.labelAlignment = alignment;
			doChangeAlignment();
			firePropertyChange("boundLabelAlignment", old, alignment);
		}
	}

	public int getBoundLabelAlignment() {
		return this.labelAlignment;
	}

	public void setBoundLabelLength(int len) {
		int old = getBoundLabelLength();
		if (old != len) {
			this.labelLength = len;
			firePropertyChange("boundLabelLength", old, len);
		}
	}

	public int getBoundLabelLength() {
		return this.labelLength;
	}

	public void setBoundLabelText(String labelText) {
		if (this.label == null) {
			setBoundLabel(new JLabel());
		}
		String old = getBoundLabelText();
		if (old != labelText) {
			this.label.setText(labelText);
			firePropertyChange("boundLabelText", old, labelText);
		}
	}

	public String getBoundLabelText() {
		if (this.label != null) {
			if (this.label.getText() != null) {
				return this.label.getText();
			}
			return "";
		}

		return "";
	}

	public void setBoundLabelUnderline(boolean b) {
		if (this.label == null) {
			setBoundLabel(new JLabel());
		}
		boolean old = isBoundLabelUnderline();
		if ((old != b) && ((this.label instanceof JLabel))) {
			((JLabel) this.label).setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
			firePropertyChange("boundLabelUnderLine", old, b);
		}
	}

	public void setUnderlineColor(Color val) {
		if ((val != null) && (this.label != null) && ((this.label instanceof JLabel))) {
			((JLabel) this.label).setBorder(new MatteBorder(0, 0, 1, 0, val));
		}
	}

	public boolean isBoundLabelUnderline() {
		if ((this.label != null) && ((this.label instanceof JLabel))) {
			return true;
		}

		return false;
	}

	public void setBoundEditor(JComponent editor) {
		JComponent old = getBoundEditor();
		if (old != editor) {
			this.editor = editor;
			firePropertyChange("boundEditor", old, editor);
			// if ((editor instanceof IBoundLabelControl)) {
			// ((IBoundLabelControl) editor).setLabelContainer(this);
			// }
		}
	}

	public JComponent getBoundEditor() {
		return this.editor;
	}

	private void doChangeAlignment() {
		switch (getBoundLabelAlignment()) {
		case 3:
			getBoundLabel().setHorizontalAlignment(4);
			getBoundLabel().setVerticalAlignment(3);
			break;
		case 2:
			getBoundLabel().setHorizontalAlignment(4);
			getBoundLabel().setVerticalAlignment(1);
			break;
		case 1:
			getBoundLabel().setHorizontalAlignment(0);
			getBoundLabel().setVerticalAlignment(1);
			break;
		case 8:
			getBoundLabel().setHorizontalAlignment(2);
			getBoundLabel().setVerticalAlignment(1);
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		default:
			getBoundLabel().setHorizontalAlignment(2);
			getBoundLabel().setVerticalAlignment(3);
		}
	}

	private void revalidateWithParent() {
		revalidate();
		Container ct = getParent();
		if (ct != null)
			ct.validate();
	}

	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		JComponent comp = getBoundEditor();
		if (comp != null)
			comp.setEnabled(enabled);
	}

	private class PropertyChangeHandler implements PropertyChangeListener {
		private PropertyChangeHandler() {
		}

		public void propertyChange(PropertyChangeEvent e) {
			String name = e.getPropertyName();
			if ((name.equals("boundLabel")) || (name.equals("boundEditor"))) {
				KXLabelContainer.this.installComponent();
				KXLabelContainer.this.revalidateWithParent();
			} else if ((name.equals("boundLabelAlignment")) || (name.equals("boundLabelLength"))
					|| (name.equals("boundLabelText")) || (name.equals("boundLabelUnderLine"))
					|| (name.equals("preferredSize"))) {
				KXLabelContainer.this.revalidateWithParent();
			}
		}
	}

	private class CompositeLayout implements LayoutManager {
		private final static double GOLDEN_RATIO = 0.618;
		private JComponent lab = null;
		private JComponent edt = null;

		private CompositeLayout() {
		}

		public void addLayoutComponent(String name, Component comp) {
			if (name.equals("label")) {
				this.lab = ((JComponent) comp);
			} else if (name.equals("editor")) {
				this.edt = ((JComponent) comp);
			}
		}

		public void removeLayoutComponent(Component comp) {
			if (comp.equals(this.lab)) {
				this.lab = null;
			} else if (comp.equals(this.edt)) {
				this.edt = null;
			}
		}

		public Dimension preferredLayoutSize(Container parent) {
			Insets insets = parent.getInsets();
			Dimension ediDim = getEdtDim();
			Dimension labDim = getLabDim(parent);

			int w = insets.left + insets.right;
			int h = insets.top + insets.bottom;

			switch (KXLabelContainer.this.getBoundLabelAlignment()) {
			case 1:
			case 2:
			case 8:
				h += ediDim.height + labDim.height;
				w += Math.max(ediDim.width, labDim.width);
				break;
			default:
				w += ediDim.width + labDim.width;
				h += Math.max(ediDim.height, labDim.height);
			}
			return new Dimension(w, h);
		}

		public Dimension minimumLayoutSize(Container parent) {
			return preferredLayoutSize(parent);
		}

		public void layoutContainer(Container parent) {
			Insets insets = parent.getInsets();
			Dimension pDim = parent.getSize();

			Dimension labDim = getLabDim(parent);

			int w = pDim.width - insets.left - insets.right;
			int h = pDim.height - insets.top - insets.bottom;

			int tempX = insets.left;
			int tempY = insets.top;

			int tempW = 0;
			int tempH = 0;
			switch (KXLabelContainer.this.getBoundLabelAlignment()) {
			case 1:
			case 2:
			case 8:
				tempW = w;
				tempH = Math.min(h, labDim.height);
				this.lab.setBounds(tempX, tempY, tempW, tempH);

				tempY += tempH;
				tempH = Math.max(h - labDim.height, 0);
				this.edt.setBounds(tempX, tempY, tempW, tempH);
				break;
			case 3:
				tempH = h;
				tempW = Math.max(w - labDim.width, 0);
				this.edt.setBounds(tempX, tempY, tempW, tempH);

				tempX += tempW;
				tempW = w - tempW;
				this.lab.setBounds(tempX, tempY, tempW, tempH);
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			default:
				tempH = h;
				tempW = Math.min(w, labDim.width);

				this.lab.setBounds(tempX, tempY, tempW, tempH);

				tempX += tempW;
				tempW = Math.max(w - labDim.width, 0);
				this.edt.setBounds(tempX, tempY, tempW, tempH);
			}
		}

		private Dimension getLabDim(Container parent) {
			Dimension labDim = new Dimension(0, 0);
			if (this.lab != null) {
				Dimension labPrfDim = this.lab.getPreferredSize();

				if ((KXLabelContainer.this.getBoundLabelAlignment() == 3)
						|| (KXLabelContainer.this.getBoundLabelAlignment() == 7)) {
					int labW = KXLabelContainer.this.getBoundLabelLength() == -1 ? labPrfDim.width
							: KXLabelContainer.this.getBoundLabelLength();
					int temp = (int) (parent.getWidth() * (1 - GOLDEN_RATIO));
					if (labPrfDim.width < temp) {
						labW = temp;
					}

					int labH = labPrfDim.height;
					labDim = new Dimension(labW, labH);
				} else {
					int labH = KXLabelContainer.this.getBoundLabelLength() == -1 ? labPrfDim.height
							: KXLabelContainer.this.getBoundLabelLength();

					int labW = labPrfDim.width;

					labDim = new Dimension(labW, labH);
				}
			}
			return labDim;
		}

		private Dimension getEdtDim() {
			if (this.edt == null) {
				return new Dimension(0, 0);
			}

			return this.edt.getPreferredSize();
		}
	}
}