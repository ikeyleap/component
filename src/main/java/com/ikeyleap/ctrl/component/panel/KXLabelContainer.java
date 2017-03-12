package com.ikeyleap.ctrl.component.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

public class KXLabelContainer extends JComponent {
	private static final long serialVersionUID = -7126196734016811212L;
	private static final String PROPERTY_BOUND_LABEL = "boundLabel";
	private static final String PROPERTY_BOUND_LABEL_ALIGNMENT = "boundLabelAlignment";
	private static final String PROPERTY_BOUND_LABEL_LENGTH = "boundLabelLength";
	private static final String PROPERTY_BOUND_LABEL_TEXT = "boundLabelText";
	private static final String PROPERTY_BOUND_LABEL_UNDERLINE = "boundLabelUnderLine";
	private static final String PROPERTY_BOUND_EDITOR = "boundEditor";
	private JLabel label;
	private JComponent editor;
//	private JButton button;
//	private boolean buttonVisible = false;
//	protected KXLabelContainerDialog labelContainerDialog;
//	private Image buttonIcon = KDResourceManager.getImageOfRapid("labelcontainer_button.gif");
//	private Image buttonOverIcon = KDResourceManager.getImageOfRapid("labelcontainer_button_01.gif");

	private int labelAlignment = 7;
	private int labelLength = -1;

	private PropertyChangeListener propertyChangeListener = new PropertyChangeHandler();
	private LabelContainerMouseListener mouseListener = new LabelContainerMouseListener();

	public KXLabelContainer()
   {
     this("", null);
   }

	public KXLabelContainer(String labelText)
   {
     this(labelText, null);
   }

	public KXLabelContainer(JComponent editor)
   {
     this("", editor);
   }

	public KXLabelContainer(String labelText, JComponent editor)
   {
     setLayout(new CompositeLayout());
     setBoundLabelText(labelText);
     setBoundEditor(editor);
 
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
//		this.button = createButton();
		if (getBoundLabel() != null) {
			add(getBoundLabel(), "label");
		}
//		if (getBoundButton() != null) {
//			add(getBoundButton(), "button");
//		}
		if (getBoundEditor() != null) {
			add(getBoundEditor(), "editor");
		}
	}

	private void installListener() {
		addPropertyChangeListener(this.propertyChangeListener);
//		this.button.addMouseListener(this.mouseListener);
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

//	public Color getUnderlineColor() {
//		return this.label.getBorder();
//	}

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
//			if ((editor instanceof IBoundLabelControl)) {
//				((IBoundLabelControl) editor).setLabelContainer(this);
//			}
		}
	}

//	private JButton createButton() {
//		JButton btn = new JButton();
//		btn.setIcon(new ImageIcon(this.buttonIcon));
////		btn.setBorder(KingdeeBorders.createLabelContainerButtonBorder());
//
//		btn.setBackground(getBoundLabel().getBackground());
//		btn.setFocusable(false);
//		return btn;
//	}

	public JComponent getBoundEditor() {
		return this.editor;
	}

//	public JButton getBoundButton() {
//		return this.button;
//	}
//
//	public void setButtonIcon(Image icon) {
//		this.button.setIcon(new ImageIcon(icon));
//		this.buttonIcon = icon;
//	}
//
//	public void setButtonOverIcon(Image icon) {
//		this.buttonOverIcon = icon;
//	}

//	public boolean isButtonVisible() {
//		return this.buttonVisible;
//	}
//
//	public void setButtonVisible(boolean buttonVisible) {
//		this.buttonVisible = buttonVisible;
//	}

//	public void setLabelContainerDialog(KXLabelContainerDialog dialog) {
//		this.labelContainerDialog = dialog;
//	}
//
//	public KXLabelContainerDialog getLabelContainerDialog() {
//		return this.labelContainerDialog;
//	}

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

	protected void popupDialog() {
//		if (this.labelContainerDialog == null) {
//			this.labelContainerDialog = new DefaultLabelContainerDialog(null);
//		}

//		this.labelContainerDialog.show();
	}

//	private class DefaultLabelContainerDialog implements KXLabelContainerDialog {
//		private DefaultLabelContainerDialog() {
//		}
//
//		public void show() {
//		}
//	}

	private class LabelContainerMouseListener implements MouseListener {
		private LabelContainerMouseListener() {
		}

		public void mouseClicked(MouseEvent e) {
			KXLabelContainer.this.popupDialog();
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
//			KXLabelContainer.this.button.setIcon(new ImageIcon(KXLabelContainer.this.buttonOverIcon));
		}

		public void mouseExited(MouseEvent e) {
//			KXLabelContainer.this.button.setIcon(new ImageIcon(KXLabelContainer.this.buttonIcon));
		}
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
		public static final String LABEL = "label";
		public static final String EDITOR = "editor";
		public static final String BUTTON = "button";
		private JComponent lab = null;
		private JComponent edt = null;
		private JComponent btn = null;

		private CompositeLayout() {
		}

		public void addLayoutComponent(String name, Component comp) {
			if (name.equals("label")) {
				this.lab = ((JComponent) comp);
			} else if (name.equals("editor")) {
				this.edt = ((JComponent) comp);
			} else if (name.equals("button")) {
				this.btn = ((JComponent) comp);
			}
		}

		public void removeLayoutComponent(Component comp) {
			if (comp.equals(this.lab)) {
				this.lab = null;
			} else if (comp.equals(this.edt)) {
				this.edt = null;
			} else if (comp.equals(this.btn)) {
				this.btn = null;
			}
		}

		public Dimension preferredLayoutSize(Container parent) {
			Insets insets = parent.getInsets();
			Dimension ediDim = getEdtDim();
			Dimension labDim = getLabDim();
			Dimension btnDim = getBtnDim();

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
				h = Math.max(h, btnDim.height);
			}
			return new Dimension(w, h);
		}

		public Dimension minimumLayoutSize(Container parent) {
			return preferredLayoutSize(parent);
		}

		public void layoutContainer(Container parent) {
			Insets insets = parent.getInsets();
			Dimension pDim = parent.getSize();

			Dimension labDim = getLabDim();
			Dimension btnDim = getBtnDim();

			int w = pDim.width - insets.left - insets.right;
			int h = pDim.height - insets.top - insets.bottom;

			int tempX = insets.left;
			int tempY = insets.top;

//			if ((!isCompositeLabelVisible()) && (!isCompositeEditorVisible())
//					&& (!KXLabelContainer.this.isButtonVisible())) {
//				return;
//			}
//			if ((!isCompositeLabelVisible()) && (!KXLabelContainer.this.isButtonVisible())) {
//				this.edt.setBounds(tempX, tempY, w, h);
//				return;
//			}
//			if ((!isCompositeEditorVisible()) && (!KXLabelContainer.this.isButtonVisible())) {
//				this.lab.setBounds(tempX, tempY, w, h);
//				return;
//			}

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
				tempW =  tempW;
				this.lab.setBounds(tempX, tempY, tempW, tempH);

//				if (KXLabelContainer.this.isButtonVisible()) {
//					tempX += tempW;
//					this.btn.setBounds(tempX, tempY, btnDim.width, tempH);
//				}

				tempX += tempW;
				tempW = Math.max(w - labDim.width, 0);
				this.edt.setBounds(tempX, tempY, tempW, tempH);
			}
		}

		private Dimension getLabDim() {
			Dimension labDim = new Dimension(0, 0);
			if (this.lab != null) {
				Dimension labPrfDim = this.lab.getPreferredSize();

				if ((KXLabelContainer.this.getBoundLabelAlignment() == 3)
						|| (KXLabelContainer.this.getBoundLabelAlignment() == 7)) {
					int labW = KXLabelContainer.this.getBoundLabelLength() == -1 ? labPrfDim.width
							: KXLabelContainer.this.getBoundLabelLength();

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

		private Dimension getBtnDim() {
			if (this.btn == null) {
				return new Dimension(0, 0);
			}

			return this.btn.getPreferredSize();
		}

		private boolean isCompositeLabelVisible() {
			if (this.lab == null) {
				return false;
			}
			if ((KXLabelContainer.this.getBoundLabelText().length() == 0)
					&& (KXLabelContainer.this.getBoundLabelLength() <= 0)) {
				return false;
			}

			return true;
		}

		private boolean isCompositeEditorVisible() {
			if (this.edt == null) {
				return false;
			}

			return true;
		}
	}
}