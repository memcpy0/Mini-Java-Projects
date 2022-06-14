package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：AppointmentPanel
 * 创建时间：2022年5月31日 <br>
 * 类描述：输入信息用来创建PIMAppointment的窗口
 * @author：张平
 */
public class AppointmentPanel extends PIMEntityPanel {
	private JTextField date;
	private JTextField desc;
	
	public AppointmentPanel() {
		var panel = new JPanel(); // 面板的布局默认是流布局
		panel.setLayout(new GridLayout(3, 2)); // 我们使用网格布局
		
		panel.add(new JLabel("    Enter Date"));
		panel.add(date = new JTextField(""));
		
		panel.add(new JLabel("    Enter Description"));
		panel.add(desc = new JTextField(""));
		
		panel.add(new JLabel("    Choose Priority"));
		panel.add(prior);
		add(panel, BorderLayout.CENTER); // 将输入框放在界面中央
		 
		// 将两个按钮添加到南部
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
 
	public void setEntity(PIMEntity p) {
		PIMAppointment pa = (PIMAppointment)p; 
		desc.setText(pa.getDescription()); // 设置对话框文本
		date.setText(Resources.sdf.format(pa.getDate()));
		System.out.println(Resources.sdf.format(pa.getDate()));
		prior.setSelectedItem(pa.getPriority());
	}

	public PIMEntity getEntity() {
		String s1 = desc.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // 填入信息为空或未选择优先级时,返回null
		
		LocalDate d;
		try {
			d = LocalDate.from(Resources.sdf.parse(date.getText()));
		} catch (Exception e) {
			return null; // 日期解析错误,则返回null
		}
		return new PIMAppointment(d, s1, s2); // 获取对话框中输入,并包装为PIMAppointment返回
	}
}