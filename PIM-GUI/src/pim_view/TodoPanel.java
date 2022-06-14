package pim_view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：TodoPanel
 * 创建时间：2022年5月31日 <br>
 * 类描述：输入信息用来创建PIMTodo的窗口
 * @author：张平
 */
public class TodoPanel extends PIMEntityPanel {
	private JTextField todo;
	private JTextField date;
	
	public TodoPanel() {
		var panel = new JPanel(); // 面板的布局默认是流布局
		panel.setLayout(new GridLayout(3, 2)); // 我们使用网格布局
		
		panel.add(new JLabel("    Enter Text for Todo"));
		panel.add(todo = new JTextField(""));
		 
		panel.add(new JLabel("    Enter Date for Todo"));
		panel.add(date = new JTextField(""));
		
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
		PIMTodo pt = (PIMTodo)p;
		todo.setText(pt.getTodoText());  // 设置对话框文本
		date.setText(Resources.sdf.format(pt.getDate()));
		prior.setSelectedItem(pt.getPriority());
	}

	public PIMEntity getEntity() {
		String s1 = todo.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // 填入信息为空或未选择优先级时,返回null
		
		LocalDate d;
		try {
			d = LocalDate.from(Resources.sdf.parse(date.getText()));
		} catch (Exception e) {
			return null; // 日期解析错误,则返回null
		}
		return new PIMTodo(d, s1, s2); // 获取对话框中输入,并包装为PIMTodo返回
	}
}