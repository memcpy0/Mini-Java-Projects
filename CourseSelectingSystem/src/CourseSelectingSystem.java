import java.util.*;

class Book {
    private String BId;		// 教材号
    private String BName;	// 教材名
    private String ISBN;

    public String getBId() {
        return BId;
    }

    public void setBId(String id) {
        this.BId = id;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String name) {
        this.BName = name;
    }
    
    public String getISBN() {
    	return ISBN;
    }
    
    public void setISBN(String isbn) {
    	this.ISBN = isbn;
    }
}

class Course {
    private String CName;	// 课程名
    private String CId;		// 课程号
    private ArrayList<Book> CBooks = new ArrayList<>(); // 课程所用教材

    public String getCName() {
        return CName;
    }

    public void setCName(String name) {
        this.CName = name;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String id) {
        this.CId = id;
    }
    
    public ArrayList<Book> getCBooks() {
        return CBooks;
    }
    
    public void setCBooks(ArrayList<Book> books) {
    	this.CBooks = books;
    }
    
    public void addBooks(Book book){
        this.CBooks.add(book);
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(CName + " [");
        for (int i = 0; i < CBooks.size(); ++i) {
        	if (i != 0) sb.append(", ");
        	Book b = this.CBooks.get(i);
            sb.append(b.getBName());
        }
        sb.append("]");
        return sb.toString();
    }
}

class Student {
    private String StuId;
    private String StuName;
    private ArrayList<Course> StuCourses = new ArrayList<>();
    
    public String getStuId() {
        return StuId;
    }

    public void setStuId(String id) {
        this.StuId = id;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String name) {
        this.StuName = name;
    }

    public ArrayList<Course> getStuCourses() {
        return StuCourses;
    }
    
    public void setStuCourse(ArrayList<Course> courses) {
    	this.StuCourses = courses;
    }

    public void addCourses(Course course){
        this.StuCourses.add(course);
    }

    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("学生学号：" + StuId + "\n");
    	sb.append("学生姓名：" + StuName + "\n");
    	sb.append("课程名称和教材如下：" + "\n");
        for (int j = 0; j < StuCourses.size(); ++j) {
            Course c = StuCourses.get(j);
            sb.append("\t" + c + "\n");
        }
        return sb.toString();
    }
}

 
public class CourseSelectingSystem {
    private ArrayList<Student> students;
    
    CourseSelectingSystem() {
    	students = new ArrayList<>();
    }
    
    public static void main(String[] args) {
    	CourseSelectingSystem css = new CourseSelectingSystem();
        Scanner sc = new Scanner(System.in); 

        while (true) {
            System.out.println("欢迎进入学生选课系统，输入操作序号进行操作：");
            System.out.println("1. 添加信息");
            System.out.println("2. 查看信息");
            System.out.println("3. 退出");
            int command = Integer.parseInt(sc.nextLine());
            switch (command) {
                case 1: // 添加学生信息
                    css.addInfo(sc);
                    break;
                case 2: // 查看学生信息
                    css.showInfo();
                    break;
                case 3: // 退出
                    System.out.println("即将退出本系统，再见！");
                    return;
                default:
                    System.out.println("输入的命令不支持！");
            }
        }
    }

    private void showInfo() {
        for (int i = 0; i < students.size(); ++i) {
            Student stu = students.get(i);
            System.out.println(stu);
        }
    }

    private void addInfo(Scanner sc) {
    	Student stu = new Student();
        System.out.println("输入学生学号：");
        stu.setStuId(sc.nextLine());
        
        System.out.println("输入学生姓名：");
        stu.setStuName(sc.nextLine());
        
        while (true) {
            addCourses(stu, sc);
            System.out.println("还有课程要添加？(Y or N)");
            if (!"Y".equals(sc.nextLine())) break;
        }
        students.add(stu);
    }

    private void addCourses(Student stu, Scanner sc) {
        System.out.println("输入课程号：");
        Course c = new Course();
        c.setCId(sc.nextLine());
        System.out.println("输入课程名称：");
        c.setCName(sc.nextLine());

        while (true) {
            addBooks(c,sc);
            System.out.println("还有教材要添加？(Y or N)");
            if (!"Y".equals(sc.nextLine())) break;
        }
        stu.addCourses(c);
    }

    private static void addBooks(Course c, Scanner sc) {
        System.out.println("请输入所用书号：");
        Book bk = new Book();
        bk.setBId(sc.nextLine());
        System.out.println("请输入所用书名：");
        bk.setBName(sc.nextLine());
        c.addBooks(bk);
    }
}

