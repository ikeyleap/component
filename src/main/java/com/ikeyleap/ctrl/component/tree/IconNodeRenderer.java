package com.ikeyleap.ctrl.component.tree;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;


@SuppressWarnings("serial")
public class IconNodeRenderer extends DefaultTreeCellRenderer {
	// 重写该方法
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus); // 调用父类的该方法
		Icon icon = ((IconNode) value).getIcon();// 从节点读取图片
		String txt = ((IconNode) value).getText(); // 从节点读取文本
		setIcon(icon);// 设置图片
		setText(txt);// 设置文本
		return this;
	}
}
