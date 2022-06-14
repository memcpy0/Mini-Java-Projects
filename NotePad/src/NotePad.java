import java.util.ArrayList;
import java.util.Scanner;
/*
 * 记事本类，实现不定数量记录的存储
 * 能够获得已经存储的记录的数量
 * 能够追加记录
 * 能够展示已经存储的全部记录、或其中任何一个记录
 * 能够删除已经存储的全部记录、或其中任何一条记录
 */
public class NotePad {
	private ArrayList<String> notePad;
	public NotePad() {
		notePad = new ArrayList<>();
	}
	
	// 返回记录数量
	public int size() {
		return notePad.size();
	}
	
	// 获取一条记录
	public String getNote(int index) {
		return notePad.get(index);
	}
	
	// 获取全部记录
	public ArrayList<String> getAllNotes() {
		ArrayList<String> result = new ArrayList<>();
		for (String note : notePad) {
			result.add(note);
		}
		return result;
	}
	
	// 追加一条记录到最后
	public void addNote(String note) {
		notePad.add(note);	
	}
	
	// 删除一个记录
	public void removeNote(int index) {
		notePad.remove(index);
	}
	
	// 删除全部记录
	public void removeAllNotes() {
//		while (!notePad.isEmpty()) {
//			notePad.removeLast(); // 迭代删除最后一个记录
//		}
		notePad.clear();
	}
	
	// 修改一个记录
	public void setNote(int index, String note) {
		notePad.set(index, note);
	}
} 