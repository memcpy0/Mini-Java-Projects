import java.util.*;
import shape.Graph;
import shape.GraphList;

public class GraphTest {
	public static void main(String[] args) {
		GraphList list = new GraphList();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("======================");
			System.out.println("||    1. 创建图形    ||");
			System.out.println("||    2. 检索图形    ||");
			System.out.println("||    3. 绘制图形    ||");
			System.out.println("||    4. 退出系统    ||");
			System.out.println("======================");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("(矩形:1, 圆形:2, 三角形:3)");
				System.out.print("请输入一个图形类型编号：");
				int shape = sc.nextInt();
				sc.nextLine();
				System.out.print("请输入该图形的名称：");
				String name = sc.nextLine();
				list.append(shape, name);	
				System.out.println("图形创建成功!");
				break;
			case 2:
				System.out.print("请输入要检索的图形名称：");
				sc.nextLine();
				String name1 = sc.nextLine();
				Graph g = list.getGraphByName(name1);
				if (g != null) list.getGraphByName(name1).paint();
				else System.out.println("不存在名称为" + name1 + "的图形!");
				break;
			case 3:
				System.out.print("请输入创建序号：");
				int index = sc.nextInt();
				System.out.println("下面顺序绘制该序号及之前的全部图形：");
				try {
					list.paintShapes(index);
				} catch (Exception e) {
					System.out.println("输入了错误的序号！");
				}
				break;
			case 4:
				sc.close();
				return;
			}	
		}
	}
}
