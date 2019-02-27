package com.ikeyleap.ctrl.component.tree;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

//定义节点类
@SuppressWarnings("serial")
public class IconNode extends DefaultMutableTreeNode {
	protected Icon icon;
	protected String txt;

	// 只包含文本的节点构造
	public IconNode(String txt) {
		super();
		this.txt = txt;
	}

	// 包含文本和图片的节点构造
	public IconNode(Icon icon, String txt) {
		super();
		this.icon = icon;
		this.txt = txt;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setText(String txt) {
		this.txt = txt;
	}

	public String getText() {
		return txt;
	}
}