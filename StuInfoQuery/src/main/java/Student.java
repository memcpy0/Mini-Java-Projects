
public class Student {
	private String sno;
	private String sname;
	private String ssex;
	private int sage;
	
	public Student() {}
	public Student(String no, String name, String sex, int age) {
		this.sno = no;
		this.sname = name;
		this.ssex = sex;
		this.sage = age;
	}
	public String getSno() { return sno; }
	public String getSname() { return sname; }
	public String getSsex() { return ssex; }
	public int getSage() { return sage; }
	public void setSno(String no) { this.sno = no; }
	public void setSname(String name) { this.sname = name; }
	public void setSsex(String sex) { this.sname = sex; }
	public void setSage(int age) { this.sage = age; }
}
