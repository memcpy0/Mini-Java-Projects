
// 备忘类
public class PIMNote extends PIMEntity {
	String noteText;
	
	// 返回Note的文本
	public String getNoteText() {
		return noteText;
	}
	// 设置Note的文本
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("NOTE " + Priority + " " + noteText);
	}
}
