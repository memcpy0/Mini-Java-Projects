package shape;

public class Circle extends Graph {
	public Circle(int gIdx, int gShape, String gName) {
		super(gIdx, gShape, gName);
	}
	
	public void paint() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(getIdx()) + " ");
		sb.append("Circle " + getName());
		return sb.toString();
	}
}
