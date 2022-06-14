package pim_model;

/**
 * <p>项目名称：PIM GUI</p>
 * <p>类名称：PIMContact</p>
 * 创建时间：2022年5月30日 <br>
 * 类描述：PIMEntity的联系人子类
 * @author：张平
 */
public class PIMContact extends PIMEntity {
	String firstName;
	String lastName;
	String email;
	
	public PIMContact() {
		type = "Contact";
	}
	
	public PIMContact(String f, String l, String e) {
		type = "Contact";
		firstName = f;
		lastName = l;
		email = e;
	}
	
	public PIMContact(String f, String l, String e, String prior) {
		this(f, l, e);
		priority = prior;
	}
	
	// 返回firstName
	public String getFirstName() {
		return firstName;
	}
	// 设置firstName
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	// 返回lastName
	public String getLastName() {
		return lastName;
	}
	// 设置lastName
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// 返回email信息
	public String getEmail() {
		return email;
	}
	// 设置email信息
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return ("CONTACT " + priority + " " + firstName + " " + lastName + " " + email);
	}
	
    public boolean equals(PIMEntity p) {
    	if (p.getType().equals(type)) {
    		PIMContact pa = (PIMContact)p;
    		return pa.firstName.equals(firstName) && pa.lastName.equals(lastName) 
    			&& pa.email.equals(email) && pa.priority.equals(priority);
    	}
    	return false;
    }
    
    public void setEntity(PIMEntity p) {
    	PIMContact pa = (PIMContact)p;
    	this.firstName = pa.firstName;
    	this.lastName = pa.lastName;
    	this.email = pa.email;
    	this.priority = pa.priority;
    }
}