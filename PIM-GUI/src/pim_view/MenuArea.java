package pim_view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import pim_model.*;
import tools.Resources;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：MenuFrame
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIM GUI顶层窗格(PIMFrame)中的菜单区域类
 * @author：张平
 */
public class MenuArea extends JPanel {
	private static final int DEFAULT_WIDTH = 600;  // 菜单窗口的宽度 
	private static final int DEFAULT_HEIGHT = 400; // 菜单窗口的高度
	
	// 弹出的各种窗口
	private TodoPanel todoPanel = null;
	private AppointmentPanel appointPanel = null;
	private NotePanel notePanel = null;
	private ContactPanel contactPanel = null;
	private JFileChooser chooser = null; // 文件对话框
	
	// 对顶层窗格的引用
	private PIMFrame topFrame = null;
	
	public MenuArea(PIMFrame topFrame) {
		this.topFrame = topFrame;
		
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		/* 文件菜单 */
		var fileMenu = new JMenu("File"); 
		fileMenu.setMnemonic('F'); // 为菜单添加助记符,选择这些顶层菜单可按下ALT+助记字母
		
		// 文件菜单包含保存、加载、另存为、从文件加载、退出这几个菜单项		
		var saveItem = new JMenuItem("Save", Resources.saveIco); // 保存到默认数据文件
		saveItem.setMnemonic('S');
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl S")); // 设置键盘加速器,可用Ctrl S保存
		saveItem.setToolTipText("Click to save all.");
		saveItem.setIcon(Resources.saveIco);
		
		var loadItem = new JMenuItem("Load", Resources.loadIco); // 从默认数据文件中加载对象数据
		loadItem.setMnemonic('L');
		loadItem.setAccelerator(KeyStroke.getKeyStroke("ctrl L")); // 设置键盘加速器,可用Ctrl L加载
		loadItem.setToolTipText("Click to load from local files.");
		
		var saveAsItem = new JMenuItem("Save As...", Resources.saveAsIco); // 另存为指定的数据文件
		saveAsItem.setToolTipText("Click to save all to selected"
				+ " file.");
		
		var loadFromItem = new JMenuItem("Load From...", Resources.loadFromIco); // 从指定的数据文件中加载对象
		loadFromItem.setToolTipText("Click to load from selected file.");
 		
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(saveAsItem); 
		fileMenu.add(loadFromItem);
		fileMenu.addSeparator(); // 向文件菜单中添加分隔符
		var exitItem = fileMenu.add(new AbstractAction("Exit") { // 添加退出菜单项和退出动作
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		exitItem.setDisplayedMnemonicIndex(1); // 设置x为助记符
		exitItem.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); // 设置键盘加速器,可用Ctrl X退出程序
		exitItem.setToolTipText("Click to exit.");

		chooser = new JFileChooser(); // 在多个窗体中重用一个文件选择器
		chooser.setCurrentDirectory(new File(".")); // 使用当前工作目录
		chooser.setSelectedFile(new File(topFrame.getPIMManager().getDataFilePath())); // 默认选择项目目录下的默认数据存储文件
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // 只允许文件选择器选择文件和目录
		chooser.setFileFilter(new FileNameExtensionFilter("dat", "DAT")); // 只允许文件选择器从.dat文件中选择
		chooser.setAcceptAllFileFilterUsed(false); // 禁用All files过滤器
		
		/* 编辑菜单 */
		var editMenu = new JMenu("Edit");
		editMenu.setMnemonic('E'); // 为菜单添加助记符
		
		// 编辑菜单包含新建待办事项、联系人、提示等菜单项
		var newAppointItem = new JMenuItem("New Appointment", Resources.newAppointmentIco);
		newAppointItem.setDisplayedMnemonicIndex(4);
		newAppointItem.setToolTipText("Click to create a new appointment.");
		newAppointItem.setAccelerator(KeyStroke.getKeyStroke("ctrl A")); // 设置键盘加速器,可用Ctrl A新建约会
		
		var newContactItem = new JMenuItem("New Contact", Resources.newContactIco);
		newContactItem.setDisplayedMnemonicIndex(4);
		newContactItem.setToolTipText("Click to create a new contact.");
		newContactItem.setAccelerator(KeyStroke.getKeyStroke("ctrl C")); // 设置键盘加速器,可用Ctrl C新建约会
		
		var newNoteItem = new JMenuItem("New Note", Resources.newNoteIco);
		newNoteItem.setDisplayedMnemonicIndex(4);
		newNoteItem.setToolTipText("Click to create a new note.");
		newNoteItem.setAccelerator(KeyStroke.getKeyStroke("ctrl N")); // 设置键盘加速器,可用Ctrl N新建提示
		
		var newTodoItem = new JMenuItem("New Todo", Resources.newTodoIco);
		newTodoItem.setDisplayedMnemonicIndex(4);
		newTodoItem.setToolTipText("Click to create a new todo.");
		newTodoItem.setAccelerator(KeyStroke.getKeyStroke("ctrl T")); // 设置键盘加速器,可用Ctrl T新建TODO
		
		editMenu.add(newAppointItem);
		editMenu.add(newContactItem);
		editMenu.add(newNoteItem);
		editMenu.add(newTodoItem);
				
        /* 导航菜单 */
		var navigateMenu = new JMenu("Navigate");
		navigateMenu.setMnemonic('N'); // 为菜单添加助记符

		// 导航菜单包含（日历）跳转至指定年月、跳转至今日、显示所有记录/待办事项/联系人/约定/等菜单项
		var jumpToDateItem = new JMenuItem("Jump to Date", Resources.jumpToDateIco);
		jumpToDateItem.setMnemonic('J');
		jumpToDateItem.setToolTipText("Click to jump to date assigned.");
		jumpToDateItem.setAccelerator(KeyStroke.getKeyStroke("ctrl J")); // 设置键盘加速器,可用Ctrl L加载
		
		var jumpToTodayItem = new JMenuItem("Jump to Today", Resources.jumpToTodayIco);
		jumpToTodayItem.setToolTipText("Click to jump to today."); 

		var showAllItem = new JMenuItem("Show All", Resources.showAllIco);
		showAllItem.setMnemonic('S');
		showAllItem.setToolTipText("Click to show all your information."); 

		var showAppointmentsItem = new JMenuItem("Show appointments", Resources.showAppointsIco);
		showAppointmentsItem.setToolTipText("Click to show all your appointments."); 
		showAppointmentsItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift A"));
		
		var showContactsItem = new JMenuItem("Show Contacts", Resources.showContactsIco);
		showContactsItem.setToolTipText("Click to show all your contacts."); 
		showContactsItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift C"));
		
		var showNotesItem = new JMenuItem("Show Notes", Resources.showNotesIco);
		showNotesItem.setToolTipText("Click to show all your notes."); 
		showNotesItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift N"));
		
		var showTodosItem = new JMenuItem("Show Todos", Resources.showTodosIco);
		showTodosItem.setToolTipText("Click to show all your todos."); 
		showTodosItem.setAccelerator(KeyStroke.getKeyStroke("ctrl shift T"));
		
		navigateMenu.add(jumpToDateItem);
		navigateMenu.add(jumpToTodayItem);
		navigateMenu.addSeparator(); // 向文件菜单中添加分隔符
		navigateMenu.add(showAllItem);
		navigateMenu.add(showAppointmentsItem);
		navigateMenu.add(showContactsItem);
		navigateMenu.add(showNotesItem);
		navigateMenu.add(showTodosItem);
		
		/* 帮助菜单 */
		var helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H'); // 为菜单添加助记符
		
		var aboutItem = new JMenuItem("About PIM GUI", Resources.aboutIco);
		aboutItem.setMnemonic('A');
		aboutItem.setToolTipText("Click to see how to use this program.");
		
		helpMenu.add(aboutItem);
		
		/* 菜单栏 */
		var menuBar = new JMenuBar();
		menuBar.add(fileMenu); // 将顶层菜单添加到菜单栏中
		menuBar.add(editMenu);
		menuBar.add(navigateMenu);
		menuBar.add(helpMenu);
		
		topFrame.setJMenuBar(menuBar); // 顶层窗口中添加菜单栏

		/* 菜单栏交互,为各个菜单项添加事件 */
		saveItem.addActionListener(event -> save());
		saveAsItem.addActionListener(event -> saveAs());
		loadItem.addActionListener(event -> load());
		loadFromItem.addActionListener(event -> loadFrom());
		
		newAppointItem.addActionListener(event -> newAppointment(LocalDate.now())); // 几个对应的动作类
		newContactItem.addActionListener(event -> newContact());
		newNoteItem.addActionListener(event -> newNote());
		newTodoItem.addActionListener(event -> newTodo(LocalDate.now()));
		
		jumpToDateItem.addActionListener(event -> { jumpToDate(); });
		jumpToTodayItem.addActionListener(event -> { jumpToToday(); });
		showAllItem.addActionListener(event -> { topFrame.getItemListArea().refreshAll(); }); // 刷新左侧JLabel列表
		showAppointmentsItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Appointment"));
		});
		showContactsItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Contact"));
		});
		showNotesItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Note"));
		});
		showTodosItem.addActionListener(event -> {
			topFrame.getItemListArea().refreshLabelList(
				getType(topFrame.getPIMManager().getItemList(), "Todo")); 
		});
		
		aboutItem.addActionListener(event -> { // 帮助界面
			JFrame jf = new JFrame("About PIM GUI");
			jf.setIconImage(Resources.aboutIco.getImage());
			jf.setSize(420, 180);
			jf.setLayout(new BorderLayout());
			Box b = Box.createVerticalBox();
			b.add(new JLabel("Personal Information Manager with Graphic User Interface"));
			b.add(Box.createVerticalStrut(10));
			b.add(new JLabel("实现作者：张平, 张宏喆"));
			b.add(new JLabel("完成时间：2022年6月3日"));
			b.add(new JLabel("使用方法：通过菜单加载/保存数据、新建记录；操作日历查看信息；点击"));
			b.add(new JLabel("左侧列表中的记录进行编辑和删除；点击日历方格创建与时间相关的记录"));
			jf.add(new JLabel(" "), BorderLayout.NORTH);
			jf.add(new JLabel(" "), BorderLayout.SOUTH);
			jf.add(new JLabel(" "), BorderLayout.WEST);
			jf.add(new JLabel(" "), BorderLayout.EAST);
			jf.add(b, BorderLayout.CENTER);
			jf.setResizable(false);
			jf.setLocationRelativeTo(topFrame);
			jf.setVisible(true);
		});
//		menuBar.setVisible(true);
	}
	
	/*
	 * 默认在项目目录下保存为"PIMDatabase.dat"
	 * 调用过saveAs()后可能会改变默认保存路径
	 */
	private void save() { 
        System.out.println("Saving...");
        try {
        	topFrame.getPIMManager().saveData();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // 提示对话框
        String[] tmp = topFrame.getPIMManager().getDataFilePath().split("\\\\");
        String filePath = tmp[tmp.length - 1];
        JOptionPane.showMessageDialog(topFrame, "Saving Data Into " + filePath, 
        	"Save", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Save Finished.");
	}
	
	/*
	 * 默认加载项目目录下的"PIMDatabase.dat"
	 * 调用过loadFrom后可能会改变默认加载路径
	 */
	private void load() { 
        System.out.println("Loading...");
        try {
        	topFrame.getPIMManager().loadData();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        // 提示对话框
        String[] tmp = topFrame.getPIMManager().getDataFilePath().split("\\\\");
        String filePath = tmp[tmp.length - 1];
        JOptionPane.showMessageDialog(topFrame, "Loading Data From " + filePath,
        	"Load", JOptionPane.INFORMATION_MESSAGE);
		topFrame.refresh(); 
		// 从文件中加载后刷新(当文件数据相同时无效果;否则文件数据会覆盖内存数据)
		System.out.println("Load Finished.");
	}
	
	/*
	 * 可以选择目录,会自动在该目录下新建一个PIMDatabase.dat
	 * 可以选择已存在的dat文件,方法会删除该文件,并新建同名文件
	 * 还可输入带.dat扩展名的新文件名,创建新文件
	 */
	private void saveAs() {
		try {
			JOptionPane.showMessageDialog(null, "Please Input Extension Name "
					+ "'.dat' Manually", "Tip", JOptionPane.WARNING_MESSAGE);
			int result = chooser.showSaveDialog(topFrame); // 显示保存对话框
			if (result == JFileChooser.APPROVE_OPTION) {
				String filename = null;
				File file = chooser.getSelectedFile();
                if (file.isDirectory()) { // 选择了一个目录,则在该目录下新建一个数据文件
                    filename = chooser.getSelectedFile().getPath() + "PIMDatabase.dat";
                } else if ( // 选择了一个文件且文件以.dat结尾,或者输入一个带有.dat的文件名
                		new FileNameExtensionFilter("dat", "DAT").accept(file)) {
                	if (file.isFile()) // 若文件存在则删除,后续会新建同名文件
                		file.delete();
                    filename = file.getPath();
                }
				topFrame.getPIMManager().setDataFilePath(filename);
				save(); // 调用save方法
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Something Wrong Happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	private void loadFrom() {
		try {
			int result = chooser.showOpenDialog(topFrame); // 显示打开对话框
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = chooser.getSelectedFile();
				if (file.isFile() && // 选择了一个文件
            		new FileNameExtensionFilter("dat", "DAT").accept(file)) { // 如果选择的文件以.dat结尾
					topFrame.getPIMManager().setDataFilePath(file.getPath());
					load(); // 调用load方法,其内部进行刷新
				}
            }
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Something Wrong happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/* --- */
	private PIMEntity callPanel(PIMEntity ex, String title, PIMEntityPanel pep) {
		if (pep == null) {
			switch (ex.getType()) {
			case "Appointment": pep = new AppointmentPanel(); break;
			case "Contact": pep = new ContactPanel(); break;
			case "Note": pep = new NotePanel(); break;
			case "Todo": pep = new TodoPanel(); break;
			} // 暂时不显示窗口
		}
		pep.setEntity(ex); // ex对象用于交换数据
		if (pep.showDialog(topFrame, title)) { // 调用showDialog展示对话框窗口
			// 用户输入数据,点击Confirm按钮进入此处,得到一个PIMEntity对象
			return pep.getEntity(); // 可能为空(即用户输入错误数据/无输入)与ex相等的对象(在Edit时),或者是正确对象
		}
		return null; // 如果用户点击的是Cancel按钮,则返回null 
	}
	
	public void newContact() { // 设置默认对话框
		PIMContact ex = new PIMContact("your firstname", "your lastname", "xxxxxx@xxxx");
		PIMContact p = (PIMContact)callPanel(ex, "New Contact", contactPanel);
		if (p != null && !p.equals(ex)) { // 如果数据无错且与默认值不同
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	 
	public void newNote() { // 设置默认对话框
		PIMNote ex = new PIMNote("your note text", "--Please Choose--");
		PIMNote p = (PIMNote)callPanel(ex, "New Note", notePanel);
		if (p != null) { // 如果数据无错(一定与默认值不同)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public void newAppointment(LocalDate d) { // 设置默认对话框,用户从这里进入添加实体		
		PIMAppointment ex = new PIMAppointment(d, "your description", "--Please Choose--");		
		PIMAppointment p = (PIMAppointment)callPanel(ex, "New Appointment", appointPanel);
		if (p != null) { // 如果数据无错(一定与默认值不同)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public void newTodo(LocalDate d) { // 设置默认对话框
		PIMTodo ex = new PIMTodo(d, "your todo text", "--Please Choose--");
		PIMTodo p = (PIMTodo)callPanel(ex, "New Todo", todoPanel);
		if (p != null) { // 如果数据无错(一定与默认值不同)
			topFrame.getPIMManager().addPIMEntity(p);
			topFrame.refresh();
		}
	}
	
	public PIMEntity editPanel(PIMEntity ex) { // 设置编辑对话框
		switch (ex.getType()) { // 根据ex的不同类型,调用不同的edit对话框
		case "Appointment": return callPanel(ex, "Edit Appointment", todoPanel);
		case "Contact": return callPanel(ex, "Edit Contact", contactPanel);
		case "Note": return callPanel(ex, "Edit Note", notePanel);
		case "Todo": return callPanel(ex, "Edit Todo", appointPanel);
		}
		return null; // 不会执行
	}
	
	/* -- */
	public void jumpToDate() {
        String targetDate = (String)JOptionPane.showInputDialog(topFrame, "Please Input Target Date Like 2022-06", 
        		"Input", JOptionPane.INFORMATION_MESSAGE); 
		LocalDate d = null;
		try {
			if (targetDate == null) return; // 用户点击取消
			if (targetDate.equals("")) { // 用户什么都没输入
				JOptionPane.showMessageDialog(topFrame, "Please Input a NONEMPTY Date", 
					"Tip", JOptionPane.WARNING_MESSAGE); // 弹出警告
			} else {
				d = LocalDate.from(Resources.sdf.parse(targetDate + "-01")); // 用户输入某年-某月
				topFrame.setDate(d); // 如果无异常,则跳转到该年-月
				topFrame.refresh();
			}
		} catch (Exception e) { // 如果输错日期
			JOptionPane.showMessageDialog(null, "Something Wrong Happens...", "Wrong",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void jumpToToday() {
		topFrame.setDate(LocalDate.now());
		topFrame.refresh();
	}
	
	private ArrayList<PIMEntity> getType(ArrayList<PIMEntity> ls, String type) {
		// 得到某种类型的对象集合
		ArrayList<PIMEntity> ans = new ArrayList<>();
		for (PIMEntity p : ls) {
			if (p.getType().equals(type)) {
				ans.add(p);
			}
		}
		return ans;
	}
}
