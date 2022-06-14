import java.util.Scanner;
import java.util.ArrayList;
/*
 * 记事本测试和交互类，实现记事本类与用户的交互
 * 向用户提示操作信息，接受用户的请求
 * 1. 添加，2. 删除，3. 检索，4. 展示
 */
public class NotePadTest {
	public static void main(String[] args) {
		NotePad pad = new NotePad();
		Scanner sc = new Scanner(System.in);
		System.out.println("欢迎使用记事本程序！");
		int op, idx;
		boolean flag = true;
		do {
			System.out.println("==================================");
			System.out.println("请输入要执行的功能编号：");
			System.out.println("1. 添加记录");
			System.out.println("2. 删除记录"); // 单个或全部记录
			System.out.println("3. 检索记录"); // 单个或全部记录
			System.out.println("4. 修改记录"); 
			System.out.println("5. 显示已记录数");
			System.out.println("6. 退出");
			
			op = sc.nextInt();
			switch (op) {
			case 1:
				System.out.print("请输入记录内容：");
				String s = sc.next();
				pad.addNote(s);
				System.out.println("记录添加成功！");
				break;
			case 2:
				System.out.print("请输入要删除第几条记录（输入0删除全部记录）：");
				idx = sc.nextInt();
				if (idx == 0) pad.removeAllNotes();
				else if (idx > 0 && idx <= pad.size()) {
					pad.removeNote(idx - 1);
					System.out.println("第" + idx + "条记录删除成功！");
				} else System.out.println("无该条记录！");
				break;
			case 3:
				System.out.print("请输入要显示第几条记录（输入0显示全部记录）：");
				idx = sc.nextInt();
				if(idx == 0){
					ArrayList<String> ls = pad.getAllNotes();
					int i = 0;
					for(String s1 : ls) {
						System.out.println(i + ". " + s1);
						++i;
					}
				} else if (idx > 0 && idx <= pad.size()) {
					System.out.println("第"+ idx + "条记录：" + pad.getNote(idx - 1));
				} else System.out.println("无该条记录！");
				break;
			case 4:
				System.out.print("请输入要修改第几条记录：");
				idx = sc.nextInt();
				if (idx > 0 && idx <= pad.size()) {
					System.out.print("请输入修改后的内容：");
					String str = sc.next();
					pad.setNote(idx - 1, str);
					System.out.println("记录修改成功！");
				} else System.out.println("无该条记录！");
				break;
			case 5:
				System.out.println("目前一共有" + pad.size() + "条记录！");
				break;
			case 6:
				System.out.println("您已退出程序！");
				break;
			default:
				System.out.println("输入错误，请重新输入！");
				break;
			}	
		}  while (op != 6);
		sc.close();
	}
}
