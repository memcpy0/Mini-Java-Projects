package abstract_class_ver;
// 邀约类
public class PIMAppointment extends PIMEntity {
	String date;
	String description;
	
	// 返回日期值
	public String getDate() {
		return date;
	}
	// 设置日期值
	public void setDate(String date) {
		this.date = date;
	}
	
	// 返回Appointment的描述
	public String getDescription() {
		return description;
	}
	// 设置Appointment的描述
	void setDescription(String description) {
		this.description = description;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString() {
		return ("Item " + PIMManager.itemCount + ": APPOINTMENT " + Priority + " " + date + " " + description);
	}
}
	