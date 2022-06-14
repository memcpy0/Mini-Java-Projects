package pim_view;

import java.awt.event.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI<p>
 * 类名称：CalendarBlock
 * 创建时间：2022年5月28日 <br>
 * 类描述：处理鼠标对日历方格的操作
 * @author：张宏喆
 */
class CalendarBlock extends JLabel implements MouseListener {
	private PIMFrame topFrame;
	private boolean marked;
	private LocalDate date;
	private PIMEntity todayItem;

	// 构造方法及属性设置方法
	public CalendarBlock(String text, LocalDate date, int horizontalAlignment, PIMFrame topFrame) {
		super(text, Resources.calendarBlockCommon, horizontalAlignment);
		this.date = date;
		this.topFrame = topFrame;
		marked = existsItem();
		if (marked) { // 表示这一天存在事件; 和鼠标移入日历方格时效果一样
			this.setIcon(Resources.calendarBlockMarked);
		}
	}

	// 重写的鼠标事件处理方法
	@Override
	public void mouseEntered(MouseEvent e) { // 当鼠标移入时
		if (this.marked || date != null) // 有标记(即有事件)或该方格存在日期
			this.setIcon(Resources.calendarBlockChosen);
	}

	@Override
	public void mouseExited(MouseEvent e) { // 当鼠标移出时
		if (this.marked) {
			this.setIcon(Resources.calendarBlockMarked); // 有标记就继续标记当天的方格
		} else {
			this.setIcon(Resources.calendarBlockCommon);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (date != null) { // 该方格存在日期
			this.setIcon(Resources.calendarBlockChosen); // 根据当天是否已有事件,分别弹出两种弹窗
			if (this.marked) { // 调用已有的item展示方法showItems
				showItems();
			} else { // 调用新建事件方法newItem
				newItem();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	private boolean existsItem() { // 该天是否存在事件
		ArrayList<PIMEntity> itemList = topFrame.getPIMManager().getItemList();
		for (PIMEntity p : itemList) {
			if ((p.getType().equals("Appointment") && ((PIMAppointment)p).getDate().equals(date))
				|| (p.getType().equals("Todo") && ((PIMTodo)p).getDate().equals(date))
			) {
				todayItem = p;
				return true;
			}
		}
		return false;
	}

	// 点击日历方格,如果当天没有事件,则弹出新建事件弹窗(类型选择)
	private void newItem() {
        int idx = JOptionPane.showOptionDialog(topFrame, "No Personal Information Exists. Now You Choose To...", 
            	"Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, Resources.infoMsgIco,
            	new Object[] {"Create New Appointment", "Create New Todo"}, "Appointment");
        if (idx == -1) return; // 选择-1表示关闭对话框
        else if (idx == 0) { // 选择0表示创建Appointment
        	topFrame.getMenuArea().newAppointment(date); // 创建事件后方格会变化
            topFrame.refresh(); // 可能会同时刷新左侧和右侧界面
        } else if (idx == 1) { // 选择1表示创建Todo
        	topFrame.getMenuArea().newTodo(date); 
            topFrame.refresh(); // 可能会同时刷新左侧和右侧界面
        }
	}
	  
	// 如果已有事件,则在左侧进行展示
	private void showItems() {
		ArrayList<PIMEntity> itemList = topFrame.getPIMManager().getItemList();
		ArrayList<PIMEntity> tmpList = new ArrayList<>();
		for (PIMEntity p : itemList) {
			if (
				(p.getType().equals("Appointment") && ((PIMAppointment)p).getDate().equals(date))
			 || (p.getType().equals("Todo") && ((PIMTodo)p).getDate().equals(date))
			) tmpList.add(p);
		}
		topFrame.getItemListArea().refreshLabelList(tmpList);
	}
}
