package pim_view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;

import javax.swing.*;

import pim_model.PIMEntity;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：PIMEntityPanel
 * 创建时间：2022年6月1日 <br>
 * 类描述：创建/编辑PIMEntity的各个界面类的抽象父类
 * @author：张平
 */
public abstract class PIMEntityPanel extends JPanel {
	JComboBox prior; // 每个PIMEntity都有一个优先级
	JButton confirmButton; // 每个创建或编辑PIMEntity的界面都有确认、取消按钮和一个对话框
	JButton cancelButton;
	JDialog dialog;
	boolean changed; 
	
	PIMEntityPanel() {
		setLayout(new BorderLayout());

		prior = new JComboBox(); // 创建JComboBox,不一定使用
		prior.addItem("--Please Choose--");
		prior.addItem("High Priority");
		prior.addItem("Medium Priority");
		prior.addItem("Low Priority"); 
		confirmButton = new JButton("Confirm");
		
		confirmButton.addActionListener(event -> { // 点击后触发这一事件
			changed = true;
			dialog.setVisible(false); // 隐藏窗口
		});
		
		cancelButton = new JButton("Cancel"); // 点击后同样隐藏窗口
		cancelButton.addActionListener(event -> dialog.setVisible(false));
	}
	
	/*
	 * 获取对话框中输入,并包装为PIMEntity返回
	 */
	abstract public PIMEntity getEntity();
	
	/*
	 * 根据PIMEntity设置对话框文本
	 */
	abstract public void setEntity(PIMEntity p);
	
	/*
	 * 创建并在对话框中展示当前panel
	 * @param parent是所有者窗口,title是对话框标题
	 */
	public boolean showDialog(Component parent, String title) {
		changed = false;
		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame)parent;
		else
			owner = (Frame)SwingUtilities.getAncestorOfClass(Frame.class, parent);
		// 如果是第一次或者所有者改变,则创建对话框,使得相应对话框对象只创建一次
		if (dialog == null || dialog.getOwner() != owner) {
			dialog = new JDialog(owner, true);
			dialog.setIconImage(Resources.newShowEditIco.getImage());
			dialog.setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
			dialog.add(this); // 将当前panel添加进对话框
			dialog.getRootPane().setDefaultButton(confirmButton);
			dialog.pack();
		}
  
		dialog.setTitle(title);
        dialog.setLocationRelativeTo(owner); // 调整窗口位置
		dialog.setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
		dialog.setVisible(true); // 使对话框可见,在用户关闭这个对话框之前会阻塞
		return changed;
	}
}
