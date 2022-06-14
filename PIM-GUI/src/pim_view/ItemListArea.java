package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：ItemListPanel
 * 创建时间：2022年6月3日 <br>
 * 类描述：事件列表区域类
 * @author：张平
 */
public class ItemListArea extends JPanel {
	private static final int DEFAULT_WIDTH = 270;  // 列表窗口的宽度 
	private static final int DEFAULT_HEIGHT = 750; // 列表窗口的高度
	
	private PIMFrame topFrame;
    private JScrollPane itemListScrollPane; // 添加滑动条
    private Box itemListBox; // 左侧条目列表容器
    
	public ItemListArea(PIMFrame topFrame) {
		this.topFrame = topFrame;
		itemListBox = Box.createVerticalBox();
		 
		itemListScrollPane = new JScrollPane(itemListBox);
		itemListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 总是有垂直滚动条
		itemListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		itemListScrollPane.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		itemListScrollPane.setAutoscrolls(true);
		add(itemListScrollPane, BorderLayout.CENTER);
		refreshAll();
	}
	
	public void refreshLabelList(ArrayList<PIMEntity> pl) {
		itemListBox.removeAll(); // 清空之前的JLabel列表
		itemListBox.revalidate();
		itemListScrollPane.revalidate();

		for (PIMEntity pe : pl) {
			JLabel tmpLabel = new JLabel("", Resources.itemViewerBorder, JLabel.CENTER);
			tmpLabel.setIconTextGap(-230);
			tmpLabel.setOpaque(true);
			switch (pe.getType()) {
			case "Appointment":
				PIMAppointment pa = (PIMAppointment) pe;
				String a = pa.getDescription();
				if (a.length() > 20) a = a.substring(0, 20) + "...";
				tmpLabel.setText(
					"<html><font weight:bold>" 
						+ "<body>Appointment:<br>"  
							+ Resources.sdf.format(pa.getDate()) + "<br>" + a 
						+ "</body></font></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showAppointment(pa);
                    }
                });
				break;
			case "Contact": 
				PIMContact pc = (PIMContact) pe;
				tmpLabel.setText(
					"<html><body>Contact: " + pc.getFirstName() + " " + pc.getLastName() + "<br>" + 
						pc.getEmail() + "<body></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
	                @Override
	                public void mousePressed(MouseEvent e) {
	                    super.mousePressed(e);
	                    showContact(pc);
	                }
	            });
				break;
			case "Todo": 
				PIMTodo pt = (PIMTodo) pe;				
				String b = pt.getTodoText();
				if (b.length() > 20) b = b.substring(0, 20) + "...";
				tmpLabel.setText(
				"<html><font weight:bold>"
					+ "<body>Todo:<br>"  
						+ Resources.sdf.format(pt.getDate()) + "<br>" + b 
					+ "<body></font></html>"
				);
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showTodo(pt);
                    }
                });
				break;
			case "Note":
				String c = ((PIMNote) pe).getNoteText();	 
				if (c.length() > 20) c = c.substring(0, 20) + "...";
				tmpLabel.setText("<html><body>Note:" + "<br>" + c + "<body></html>");
				tmpLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        showNote((PIMNote) pe);
                    }
                });
				break;
			}
			itemListBox.add(Box.createVerticalStrut(5));
			itemListBox.add(tmpLabel); // 重新添加JLabel
		}
        itemListBox.repaint();
        itemListBox.revalidate();
        itemListScrollPane.repaint();
        itemListScrollPane.revalidate();
	}
	
	public void refreshAll() {
		refreshLabelList(topFrame.getPIMManager().getItemList());
	}
	
    // 左侧条目点击后的弹窗(可用来展示、编辑和删除条目)
	private void showAppointment(PIMAppointment p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
        box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));

        LocalDate d = p.getDate();
        box.add(new JLabel(Resources.sdf.format(d) + " " + p.getPriority()));
        box.add(Box.createVerticalStrut(10));
        
        box.add(new JLabel(p.getDescription()));
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // 对齐这些信息
	    
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	private void showContact(PIMContact p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
   
        box.add(new JLabel("Name: " + p.getFirstName() + " " + p.getLastName()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel("Email Address: " + p.getEmail())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // 对齐这些信息
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}

	private void showTodo(PIMTodo p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
   
        LocalDate d = p.getDate();
        box.add(new JLabel(Resources.sdf.format(d) + " " + p.getPriority()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel(p.getTodoText())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // 对齐这些信息
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	private void showNote(PIMNote p) {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(new JLabel(p.getType() + ":"));
        box.add(Box.createVerticalStrut(20));
    
        box.add(new JLabel(p.getPriority()));
        box.add(Box.createVerticalStrut(10));

        box.add(new JLabel(p.getNoteText())); 
        box.add(Box.createVerticalStrut(40));
	    panel.add(box); // 对齐这些信息
		ShowDialog sd = new ShowDialog("Show and Edit", true, p, panel);
		sd.setVisible(true);
	}
	
	class ShowDialog extends JDialog { // 用来展示信息的类
		public ShowDialog(String title, boolean modal, PIMEntity p, JPanel infos) {
			super(topFrame, title, modal);
			setSize(Resources.DIALOG_DEFAULT_WIDTH, Resources.DIALOG_DEFAULT_HEIGHT);
			setResizable(true); // 可调整对话框大小,以便观看完整信息
			setLocationRelativeTo(topFrame);
			
			JPanel panel = new JPanel();
			JButton comfirmButton = new JButton("Confirm");
			comfirmButton.addActionListener(event -> dispose()); // 点击确认按钮后退出
			JButton editButton = new JButton("Edit"); // 点击编辑按钮进入编辑界面
			editButton.addActionListener(event -> {
				dispose();  
				editEntity(p);
			});
			JButton deleteButton = new JButton("Delete"); // 点击删除按钮删除条目
			deleteButton.addActionListener(event -> {
				topFrame.getPIMManager().getItemList().remove(p);
				topFrame.refresh(); // 可能不刷新界面,或只刷新左侧界面,或全部刷新
				dispose();
			});
			
			Box box = Box.createHorizontalBox();
			box.add(comfirmButton);
			box.add(Box.createHorizontalStrut(10));
			box.add(editButton);
			box.add(Box.createHorizontalStrut(10));
			box.add(deleteButton);
			panel.add(box);
			
			add(infos, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);
		}
	}
	
	private void editEntity(PIMEntity ex) { 
		PIMEntity p = topFrame.getMenuArea().editPanel(ex);
		// 说明用户点击Cancel按钮,没有输入数据;或点击Confirm按钮,但编辑使得数据错误,即为空
		// 或与原对象ex相等,即没有修改
		if (p == null) return; // 直接返回
		// 或正确进行了修改
		ex.setEntity(p);
		topFrame.refresh(); // 可能不刷新界面,或只刷新左侧界面,或全部刷新
	}
}
