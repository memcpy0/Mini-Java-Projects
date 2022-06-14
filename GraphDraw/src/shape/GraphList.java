package shape;
import java.util.ArrayList;
import java.math.*;

public class GraphList {
	private ArrayList<Graph> gList; // 图形数组
	public GraphList() {
		gList = new ArrayList<>();
	}
	
	// 在Graph列表末尾添加Graph对象
	public void append(int gShape, String gName) {
		switch (gShape) {
		case 1: // 表示矩形
			gList.add(new Rectangle(gList.size(), gShape, gName));
			break;
		case 2: // 表示圆形
			gList.add(new Circle(gList.size(), gShape, gName));
			break;
		case 3: // 表示三角形
			gList.add(new Triangle(gList.size(), gShape, gName));
			break;
		}
	}
	// 根据用户提供的序号检索图形
	public Graph getGraphByIndex(int index) {
		return gList.get(index);
	}
	// 根据用户提供的名字检索图形
	public Graph getGraphByName(String name) {
		for (Graph g : gList) {
			if (name.equals(g.getName()))
				return g;
		}
		return null;
	}
	
	// 根据用户提供的序号,绘制该序号及之前的所有图形
	public void paintShapes(int index) {
		rangePaint(0, index + 1);
	}
	// 私有方法,内部使用
	private void rangePaint(int off, int len) {
		if (off < 0 || off >= gList.size()) 
			throw new IllegalArgumentException("Illegal Argument");
		len = Math.min(off + len, gList.size());
		for (int i = off; i < len; ++i) {
			gList.get(i).paint();
		}
	}
}
