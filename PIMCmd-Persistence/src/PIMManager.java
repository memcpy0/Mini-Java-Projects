
import java.util.*;
import java.io.*;

public class PIMManager {
	static String dataFilePath = "PIMDatabase.dat";
	static File dataFile = new File(dataFilePath);
	static LinkedList<PIMEntity> itemList = new LinkedList<>();
	
	// 将itemList对象数组中的对象保存到文件中
	private static void saveData() {
		if (dataFile.canWrite()) { // 可写文件
			try (					
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
			) {
				oos.writeObject(itemList); // 序列化集合对象
				oos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 对数据文件反序列化,重新生成对象数组itemList
	private static void loadData() {
		if (dataFile.canRead() && dataFile.length() > 0) { // 可读文件,长度大于0时读取
			try (
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile));
			) {
				itemList = (LinkedList<PIMEntity>)ois.readObject(); // 将对象反序列化
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		if (!dataFile.exists()) { // 如果数据文件不存在
			dataFile.createNewFile(); // 新建一个数据文件
		} else loadData(); // 如果数据文件存在,则加载数据
		
		System.out.println("Welcome to PIM.");
		Scanner sc = new Scanner(System.in);
		String op, input, date, text, priority, desc, firstName, lastName, email;
		do {
			System.out.println("---Enter a command (supported commands are List Create Save Load Quit)---");
			// 支持List,Create,Save,Load,Quit命令
			op = sc.nextLine();
			switch (op) {
			case "List": // 输出所有对象,由于实现了对象的toString方法,这里输出相应的字符串表示
				System.out.println("There are "+ itemList.size() + " items.");
				for (int i = 0; i < itemList.size(); ++i) {
					System.out.println("Item " + (i + 1) + ": " + itemList.get(i));
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
					
					itemList.add(todo);
					break; 
				case "note":
					PIMNote note = new PIMNote();
					System.out.println("Enter note text:"); // 备忘录说明文本 
					text = sc.nextLine();
					note.setNoteText(text);
					
					System.out.println("Enter note priority:"); // 备忘录优先级
					priority = sc.nextLine();
					note.setPriority(priority);
					
					itemList.add(note);
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

					itemList.add(appointment);
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

					itemList.add(contact);
				    break;
				default:
					System.out.println("the item type is not exist");
					break;
				}
				break;
			case "Save":
				saveData();
				System.out.println("Items have been saved.");
				break;
			case "Load":
				loadData();
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