package pim_model;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMNote</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMEntity的备忘子类
 * @author：张平
 */
public class PIMNote extends PIMEntity {
	String noteText;
	
	public PIMNote() {
		type = "Note";
	}
	
	public PIMNote(String note, String prior) {
		type = "Note";
		this.noteText = note;
		this.priority = prior;
	}
	
	// 返回Note的文本
	public String getNoteText() {
		return noteText;
	}
	// 设置Note的文本
	public void setNoteText(String noteText) {
		this.noteText = noteText;
	} 
	
	public String toString() {
		return ("NOTE " + priority + " " + noteText);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMNote pa = (PIMNote)p;
    		return pa.noteText.equals(noteText) && pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMNote pa = (PIMNote)p;
    	this.noteText = pa.noteText;
    	this.priority = pa.priority;
    }
}
