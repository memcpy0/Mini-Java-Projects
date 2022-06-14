package pim_view;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * <p>项目名称：PIM GUI
 * <p>类名称：PIMGUI
 * 创建时间：2022年5月31日 <br>
 * 类描述：创建主窗口的实例
 * @author：张平
 */
public class PIMGUI {
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			PIMFrame frame;
			try {
				frame = new PIMFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		});
	}
}
