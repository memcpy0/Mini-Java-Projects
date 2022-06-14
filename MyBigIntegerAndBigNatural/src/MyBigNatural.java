
public class MyBigNatural extends MyBigInteger {
	public static final MyBigNatural ZERO = new MyBigNatural("0");
	public static final MyBigNatural ONE = new MyBigNatural("1");
	public static final MyBigNatural TWO = new MyBigNatural("2");
	public static final MyBigNatural TEN = new MyBigNatural("10");
	public static final MyBigNatural MAX_VALUE;
	public static final MyBigNatural MIN_VALUE = ZERO;
	static {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_DATA_LENGTH; ++i)
			sb.append("9");
		MAX_VALUE = new MyBigNatural(sb.toString());
	}
	
	public MyBigNatural(String val) {
		super(val);
		if (super.signum < 0) // 负数不是自然数
			throw new IllegalArgumentException("Illegal input val");
	}
	public MyBigNatural(MyBigInteger a) {
		super(a.d, a.signum);
		if (super.signum < 0) // 负数不是自然数
			throw new IllegalArgumentException("Illegal input val");
	}
	public MyBigNatural add(MyBigNatural a) {
		MyBigInteger r = super.add(a);
		return new MyBigNatural(r);
	}
	public MyBigNatural subtract(MyBigNatural a) {
		MyBigInteger r = super.subtract(a);
		return new MyBigNatural(r);
	}
	public MyBigNatural multiply(MyBigNatural a) {
		MyBigInteger r = super.multiply(a);
		return new MyBigNatural(r);
	}
	
	public MyBigInteger toMyInteger(MyBigNatural a) {
		return (MyBigInteger) a;
	}
	
	// 字符串表示
	@Override
	public String toString() {
		if (signum == 0) return "0";
		StringBuilder sb = new StringBuilder();
		for (int i = length - 1; i >= 0; --i) 
			sb.append((char)('0' + d[i]));
		return sb.toString();
	}
}
