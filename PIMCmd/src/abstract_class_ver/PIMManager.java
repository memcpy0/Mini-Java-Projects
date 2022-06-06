package abstract_class_ver;
import java.util.*;
import java.io.*;

public class PIMManager{
	static int itemCount = 0; // 用来表示有多少个事项,从1开始使用
	static String[] itemList = new String[200];  
	
	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to PIM.");
		Scanner sc = new Scanner(System.in);
		String op, input, date, text, priority, desc, firstName, lastName, email;
		do {
			System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
			// 支持List,Create,Save,Load,Quit命令
			op = sc.nextLine();
			switch (op) {
			case "List":
				System.out.println("There are "+ itemCount + " items.");
				for (int i = 1; i <= itemCount; ++i) {
					System.out.println(itemList[i]);
				}
				break;
			case "Create":
				System.out.println("Enter an item type ( todo, note, contact or appointment )");
				input = sc.nextLine();
				switch (input) {
				case "todo":
					PIMTodo todo = new PIMTodo();
					System.out.println("Enter date for todo item:"); // 待办事项的日期
					date = sc.nextLine();
					todo.setDate(date);
			
					System.out.println("Enter todo text:");	// 待办事项的说明文本
					text = sc.nextLine();
					todo.setTodoText(text);
					
					System.out.println("Enter todo priority:"); // 待办事项优先级 
					priority = sc.nextLine();
					todo.setPriority(priority); 
					
					itemList[++itemCount] = todo.toString();
					break; 
				case "note":
					PIMNote note = new PIMNote();
					System.out.println("Enter note text:"); // 备忘录说明文本 
					text = sc.nextLine();
					note.setNoteText(text);
					
					System.out.println("Enter note priority:"); // 备忘录优先级
					priority = sc.nextLine();
					note.setPriority(priority);
					
					itemList[++itemCount] = note.toString();
					break;
				case "appointment":
					PIMAppointment appointment = new PIMAppointment();
					System.out.println("Enter date for todo item:"); // 待办事项的日期
					date = sc.nextLine();
					appointment.setDate(date);
					
					System.out.println("Enter appointment description:"); // 待办事项的描述
					text = sc.nextLine();
					appointment.setDescription(text);
					
					System.out.println("Enter appointment priority:"); // 待办事项优先级
					priority = sc.nextLine();
					appointment.setPriority(priority);

					itemList[++itemCount] = appointment.toString();
					break;
				case "contact":
					PIMContact contact = new PIMContact();
					System.out.println("Enter firstname for contact item:"); // 联系人firstName 
					firstName = sc.nextLine();
					contact.setFirstName(firstName);
					
					System.out.println("Enter lastname for contact item:"); // 联系人lastName
					lastName = sc.nextLine();
					contact.setLastName(lastName);
					
					System.out.println("Enter email for contact item:"); // 联系人邮箱 
					email = sc.nextLine();
					contact.setEmail(email);
					
					System.out.println("Enter contact priority:"); // 联系人优先级
					priority = sc.nextLine();
					contact.setPriority(priority);

					itemList[++itemCount] = contact.toString();
				    break;
				default:
					System.out.println("the item type is not exist");
					break;
				}
				break;
			case "Save":
				System.out.println("Items have been saved.");
				break;
			case "Load":
				break;
			case "Quit":
				sc.close();
				break;
			default:
				System.out.println("the command is not exist");
			}
		} while (!op.equals("Quit"));
	}
}