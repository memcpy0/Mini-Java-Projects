package abstract_class_ver;
// 待办事项类
public class PIMTodo extends PIMEntity implements SharedDate {
	String date;
	String toDoText;
	
	// 返回日期值
	public String getDate() {
		return date;
	}
	// 设置日期值
	public void setDate(String date) {
		this.date = date;
	}
	
	// 返回ToDo的文本
	public String getTodoText() {
		return toDoText;
	}
	// 设置ToDo的文本
	void setTodoText(String toDoText) {
		this.toDoText = toDoText;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("Item " + PIMManager.itemCount + ": TODO " + Priority + " " + date + " " + toDoText);
	}	
}

