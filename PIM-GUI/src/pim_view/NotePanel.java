package pim_view;

import java.awt.*;
import javax.swing.*;

import pim_model.*;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：NotePanel
 * 创建时间：2022年5月31日 <br>
 * 类描述：输入信息用来创建PIMNote的窗口
 * @author：张平
 */
public class NotePanel extends PIMEntityPanel {
	private JTextField note;

	public NotePanel() {
		var panel = new JPanel(); // 面板的布局默认是流布局
		panel.setLayout(new GridLayout(2, 2)); // 我们使用网格布局
		
		panel.add(new JLabel("    Enter Note Text"));
		panel.add(note = new JTextField(""));

		panel.add(new JLabel("    Choose Priority"));
		panel.add(prior); // 添加JComboBox
		add(panel, BorderLayout.CENTER); // 将输入框放在界面中央
		
		// 将两个按钮添加到南部
		var buttonPanel = new JPanel();
		buttonPanel.add(confirmButton);
		buttonPanel.add(cancelButton);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setEntity(PIMEntity p) {
		PIMNote pn = (PIMNote)p; // 设置对话框文本
		note.setText(pn.getNoteText()); 
		prior.setSelectedItem(pn.getPriority());
	}
	 
	public PIMEntity getEntity() {
		String s1 = note.getText();
		String s2 = (String) prior.getSelectedItem();
		if (s1.isEmpty() || s2.isEmpty() || s2.equals("--Please Choose--")) return null; // 填入信息为空或未选择优先级时,返回null
		return new PIMNote(s1, s2); // 获取对话框中输入,并包装为PIMNote返回
	}
}

