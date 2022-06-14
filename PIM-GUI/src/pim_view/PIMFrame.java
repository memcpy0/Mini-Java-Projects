package pim_view;

import java.util.*;
import java.lang.*;
import java.time.LocalDate;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：PIMFrame
 * 创建时间：2022年5月27日 <br>
 * 类描述：PIM GUI程序的顶层窗口
 * @author：张平
 */
public class PIMFrame extends JFrame { 
	private static final int DEFAULT_WIDTH = 1010;  // 顶层窗口的宽度 
	private static final int DEFAULT_HEIGHT = 810; // 顶层窗口的高度
	
	private MenuArea menuArea; // 菜单区域
	private ItemListArea itemListArea; // 信息列表区域
    private CalendarArea calendarArea; // 日历区域
    private JPanel itemListPanel, calendarPanel; 
    
    private PIMManager pimManager;
    private LocalDate date;
    
    public PIMManager getPIMManager() {
    	return pimManager;
    }
    
	public PIMFrame() throws IOException {
        date = LocalDate.now();
		
        pimManager = new PIMManager();
		setTitle("PIM GUI");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setIconImage(Resources.pimFrameIcon.getImage());
		 
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width; // 得到屏幕的宽度
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height; // 得到屏幕的高度
        setBounds((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2, DEFAULT_WIDTH, DEFAULT_HEIGHT); // 使程序窗口居中
        
		// 顶层窗口,顶上是菜单栏
		// 左边显示PIMEntity信息,右边是某月的日历,中间是一条分割线
        menuArea = new MenuArea(this);

        calendarPanel = new JPanel(new GridLayout());
        itemListPanel = new JPanel(new GridLayout());

		JSplitPane mainViewSeperator = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, itemListPanel, calendarPanel); // 左右分割两个版面 
        add(mainViewSeperator);
        mainViewSeperator.setDividerLocation(285);
        mainViewSeperator.setDividerSize(5);
        mainViewSeperator.setEnabled(false);
        
        // 日历区设置
        calendarArea = new CalendarArea(date, this);
        calendarPanel.add(calendarArea);
        // 条目列表区设置
        itemListArea = new ItemListArea(this);
        itemListPanel.add(itemListArea);
     
        this.setResizable(false); // 界面大小不可变
		pack();
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
    // Refresh刷新左部实体列表和主操作界面
    public void refresh() {
        System.out.println("Refreshed.");
        // 刷新左部实体列表
        // itemListPanel.remove(itemListArea);
        // itemListArea = new ItemListArea(this);
        // itemListPanel.add(itemListArea);
        itemListArea.refreshAll();
        // 刷新日历界面
        calendarPanel.remove(calendarArea);
        calendarArea = new CalendarArea(date, this);
        calendarPanel.add(calendarArea);
        
        repaint();
        revalidate();
        setVisible(true);
    }
    
    public MenuArea getMenuArea() {
    	return menuArea;
    }

    public ItemListArea getItemListArea() {
    	return itemListArea;
    }
}