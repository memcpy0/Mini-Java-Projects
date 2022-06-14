package pim_model;

import java.time.LocalDate;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMAppointment</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMEntity的邀约子类
 * @author：张平
 */
public class PIMAppointment extends PIMEntity {
	LocalDate date;
	String description;
	
	public PIMAppointment() {
		type = "Appointment";
	}
	
	public PIMAppointment(LocalDate date, String desc, String prior) {
		type = "Appointment";
		this.date = date;
		this.description = desc;
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
	
	// 返回Appointment的描述
	public String getDescription() {
		return description;
	}
	// 设置Appointment的描述
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return ("APPOINTMENT " + priority + " " + date + " " + description);
	}

    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMAppointment pa = (PIMAppointment)p;
    		return pa.date.equals(date) && pa.description.equals(description)
    				&& pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMAppointment pa = (PIMAppointment)p;
    	this.date = pa.date;
    	this.description = pa.description;
    	this.priority = pa.priority;
    }
}
	