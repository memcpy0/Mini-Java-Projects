package shape;

public abstract class Graph {
	private int gIdx;		// 图形序号
	private int gShape;		// 图形形状
	private String gName;	// 图形名称
	public Graph(int gIdx, int gShape, String gName) {
		this.gIdx = gIdx;
		this.gShape = gShape;
		this.gName = gName;
	}
	
	// 绘制方法
	public abstract void paint();
	
	public int getIdx() {
		return gIdx;
	}
	public int getShape() {
		return gShape;
	}
	public String getName() {
		return gName;
	}
	public void setIdx(int idx) {
		this.gIdx = idx;
	}
	public void setShape(int shape) {
		this.gShape = shape;
	}
	public void setName(String name) {
		this.gName = name;
	}
	
	// 可用于绘制方法或输出,需要重载
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(gIdx) + " ");
		sb.append(String.valueOf(gShape) + " ");
		sb.append(gName);
		return sb.toString();
	}
}
