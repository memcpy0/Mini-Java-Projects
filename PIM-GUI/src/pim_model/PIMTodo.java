package pim_model;

import java.time.LocalDate;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMTodo</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMEntity的待办事项子类
 * @author：张平
 */
public class PIMTodo extends PIMEntity implements SharedDate {
	LocalDate date;
	String todoText;
	
	public PIMTodo() {
		type = "Todo";
	}
	 
	public PIMTodo(LocalDate date, String todo, String prior) {
		type = "Todo";
		this.date = date;
		this.todoText = todo;
		this.priority = prior;
	}
	
	// 返回日期值
	public LocalDate getDate() {
		return date;
	}
	// 设置日期值
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	// 返回ToDo的文本
	public String getTodoText() {
		return todoText;
	}
	// 设置ToDo的文本
	public void setTodoText(String todoText) {
		this.todoText = todoText;
	}
	
	public String toString() {
		return ("TODO " + priority + " " + date + " " + todoText);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMTodo pa = (PIMTodo)p;
    		return pa.date.equals(date) && pa.todoText.equals(todoText)
    				&& pa.priority.equals(priority);
    	}
    	return false;
    }
   
    public void setEntity(PIMEntity p) {
    	PIMTodo pa = (PIMTodo)p;
    	this.date = pa.date;
    	this.todoText = pa.todoText;
    	this.priority = pa.priority;
    }
}