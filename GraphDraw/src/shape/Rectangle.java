package shape;

public class Rectangle extends Graph {
	public Rectangle(int gIdx, int gShape, String gName) {
		super(gIdx, gShape, gName);
	}

	public void paint() {
		System.out.println(this);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(getIdx()) + " ");
		sb.append("Rectangle " + getName());
		return sb.toString();
	}}
