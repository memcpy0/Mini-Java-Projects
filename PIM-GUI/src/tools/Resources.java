package tools;

import java.awt.Font;
import java.awt.Image;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：Resources
 * 创建时间：2022年6月1日 <br>
 * 类描述：资源类,用来存储各个图片资源和所用常量
 * @author：张宏喆
 */
public class Resources {
	public static int DIALOG_DEFAULT_WIDTH = 400;
	public static int DIALOG_DEFAULT_HEIGHT = 180;
	public static DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// 有坑点: SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 格式化对象
	 
	// 背景图片
	public static final ImageIcon calendarBlockCommon = new ImageIcon("img/calendarBlocks_common.jpg");
	public static final ImageIcon calendarBlockChosen = new ImageIcon("img/calendarBlocks_chosen.jpg");
	public static final ImageIcon calendarBlockMarked = new ImageIcon("img/calendarBlocks_marked.jpg");
	public static final ImageIcon calendarBlockWeekName = new ImageIcon("img/calendarBlocks_weekName.jpg");
	public static final ImageIcon itemViewerBorder = new ImageIcon("img/item_viewer_border.png");
	
	// 图标资源文件的路径
	public static final ImageIcon pimFrameIcon = new ImageIcon("ico/pim_frame.png");
	public static final ImageIcon saveIco = new ImageIcon("ico/save.png");
	public static final ImageIcon saveAsIco = new ImageIcon("ico/save_as.png");
	public static final ImageIcon loadIco = new ImageIcon("ico/load.png");
	public static final ImageIcon loadFromIco = new ImageIcon("ico/load_from.png");
	public static final ImageIcon newAppointmentIco = new ImageIcon("ico/new_appointment.png");
	public static final ImageIcon newContactIco = new ImageIcon("ico/new_contact.png");
	public static final ImageIcon newTodoIco = new ImageIcon("ico/new_todo.png");
	public static final ImageIcon newNoteIco = new ImageIcon("ico/new_note.png");
	public static final ImageIcon jumpToDateIco = new ImageIcon("ico/jump_to_date.png");
	public static final ImageIcon jumpToTodayIco = new ImageIcon("ico/jump_to_today.png");
	public static final ImageIcon showAllIco = new ImageIcon("ico/show_all.png");
	public static final ImageIcon showAppointsIco = new ImageIcon("ico/show_appoints.png");
	public static final ImageIcon showContactsIco = new ImageIcon("ico/show_contacts.png");
	public static final ImageIcon showNotesIco = new ImageIcon("ico/show_notes.png");
	public static final ImageIcon showTodosIco = new ImageIcon("ico/show_todos.png");
	public static final ImageIcon timeLabelIco = new ImageIcon("ico/time_label.png");
	public static final ImageIcon aboutIco = new ImageIcon("ico/about.png");
	public static final ImageIcon newShowEditIco = new ImageIcon("ico/new_show_edit.png");
	public static final ImageIcon infoMsgIco = new ImageIcon("ico/info_msg.png");
	
	// 字体
	public static final Font newRomanFont = new Font("Times New Roman", Font.PLAIN, 16);
	public static final Font markedRomanFont = new Font("Times New Roman", Font.BOLD, 16);
	public static final Font conSolasFont = new Font("Consola", Font.BOLD, 14);
	public static final Font markedConSolasFont = new Font("Consola", Font.BOLD, 18);
}