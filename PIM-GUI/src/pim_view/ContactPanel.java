package pim_view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import pim_model.*;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：ContactPanel
 * 创建时间：2022年5月31日 <br>
 * 类描述：输入信息用来创建PIMContact的窗口
 * @author：张平
 */
public class ContactPanel extends PIMEntityPanel {
	private JTextField firstName;
	private JTextField lastName;
	private JTextField email; 
	
	public ContactPanel() {
		var panel = new JPanel(); // 面板的布局默认是流布局
		panel.setLayout(new GridLayout(3, 2)); // 我们使用网格布局 
		
		panel.add(new JLabel("    FirstName"));
		panel.add(firstName = new JTextField(""));
		 
		panel.add(new JLabel("    LastName"));
		panel.add(lastName = new JTextField(""));
		
		panel.add(new JLabel("    Email Address"));
		panel.add(email = new JTextField(""));
		add(panel, BorderLayout.CENTER); // 将输入框放在界面中央
		
		// 将两个按钮添加到南部
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	} 
	
	public void setEntity(PIMEntity p) {
		PIMContact pc = (PIMContact)p;
		firstName.setText(pc.getFirstName()); // 设置对话框文本 
		lastName.setText(pc.getLastName());
		email.setText(pc.getEmail());
	}
	 
	public PIMEntity getEntity() {
		String s1 = firstName.getText();
		String s2 = lastName.getText();
		String s3 = email.getText();
		if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty()) return null; // 填入信息为空时,返回null
		return new PIMContact(s1, s2, s3); // 获取对话框中输入,并包装为PIMContact返回
	}
}