package pim_view;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.time.LocalDate;

import pim_model.PIMEntity;
import tools.*;

/**
 * <p>项目名称：PIM GUI<p>
 * 类名称：CalendarArea 
 * 创建时间：2022年5月28日 <br>
 * 类描述：日历区域类
 * @author：张宏喆
 */
public class CalendarArea extends JPanel {
	private PIMFrame topFrame;
	private JPanel rootPanel, basePanel;
	private String[][] monthCalendar;
	private String[] dayOfWeekName = {"Mon.", "Tue.", "Wed.", "Thu.", "Fri.", "Sat.", "Sun."};;
 
	private JLabel[] dayOfWeek = new JLabel[7];
	private CalendarBlock[] dayOfMonth = new CalendarBlock[42];
	private LocalDate date;
	
	public CalendarArea(LocalDate date, PIMFrame topFrame) { // 建立该年该月的日历
		setLayout(new BorderLayout());
		
		basePanel = new JPanel();
		basePanel.setLayout(new GridLayout(7, 7)); 
		this.date = date;
		this.monthCalendar = MyCalendar.getMonthCalendar(date.getYear(), date.getMonthValue());
		this.topFrame = topFrame;

		rootPanel = new JPanel();
		rootPanel.setLayout(new BorderLayout(1, 1));
		add(basePanel, BorderLayout.CENTER);
		add(rootPanel, BorderLayout.NORTH);
		
		paintAll();
	}
	
	private void paintAll() {
		// 开始绘制界面，同时为每个标签加入交互
		String tmp = Resources.sdf.format(date);
		String text = "THE CURRENT DATE IS " + tmp.substring(0, 7);  
		JLabel tip = new JLabel(text, Resources.timeLabelIco, JLabel.CENTER);
		tip.setFont(Resources.markedRomanFont);
		rootPanel.add(tip);

		for (int i = 0; i < 7; ++i) {
			dayOfWeek[i] = new JLabel(dayOfWeekName[i], Resources.calendarBlockWeekName, JLabel.CENTER);
			dayOfWeek[i].setFont(Resources.markedConSolasFont);
			dayOfWeek[i].setIconTextGap(-60);
			dayOfWeek[i].setOpaque(true);
			basePanel.add(dayOfWeek[i]);
		}
		int k = 0;
		// 创建六周42个CalendarBlock,用来表示每天
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j < 7; ++j) {
				LocalDate d = null;
				if (monthCalendar[i][j] != null) {					
					d = LocalDate.of(date.getYear(), date.getMonth(), Integer.parseInt(monthCalendar[i][j]));
				}
				dayOfMonth[k] = new CalendarBlock(monthCalendar[i][j], d, SwingConstants.CENTER, topFrame);
				dayOfMonth[k].setFont(Resources.conSolasFont);
				dayOfMonth[k].setBorder(BorderFactory.createEtchedBorder());
				dayOfMonth[k].setIconTextGap(-55);
				dayOfMonth[k].setOpaque(true);
				dayOfMonth[k].addMouseListener(dayOfMonth[k]);
				basePanel.add(dayOfMonth[k++]);
			}
		}
	}
}