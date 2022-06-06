
// 联系人类
public class PIMContact extends PIMEntity {
	String firstName;
	String lastName;
	String email;
	
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
	void setEmail(String email) {
		this.email = email;
	}
	
	public void fromString(String ex) {
		
	}
	
	public String toString(){
		return ("CONTACT " + Priority + " " + firstName + " " + lastName + " " + email);
	}
}

