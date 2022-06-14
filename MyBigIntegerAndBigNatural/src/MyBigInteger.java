import java.util.ArrayList;
public class MyBigInteger {
	// d为存储整数的数组,从头到尾逆序存储十进制整数
	protected final int[] d;
	// signum为符号位,0表示为0,-1表示负数,1表示正数
	protected final int signum;
	// len表示实际数据的长度(十进制位数)
	protected final int length;
	protected final static int MAX_DATA_LENGTH = 100000;
	public static final MyBigInteger ZERO = new MyBigInteger();
	public static final MyBigInteger ONE = new MyBigInteger("1");
	public static final MyBigInteger TWO = new MyBigInteger("2");
	public static final MyBigInteger NEGATIVE_ONE = new MyBigInteger("-1");
	public static final MyBigInteger TEN = new MyBigInteger("10");
	public static final MyBigInteger MAX_VALUE; // 最大的MyBigInteger对象
	public static final MyBigInteger MIN_VALUE; // 最小的MyBigInteger对象
	static {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < MAX_DATA_LENGTH; ++i)
			sb.append("9");
		MAX_VALUE = new MyBigInteger(sb.toString());
		MIN_VALUE = new MyBigInteger("-" + sb.toString());
	}
	
	// 空构造函数,得到表示0的大整数
	// 为节省空间,约定大整数0的data数组长为0
	public MyBigInteger() {	
		d = new int[0];
		signum = 0;
		length = 0;
	}
	// 用String传入数据加以构造,假设输入字符串不包含除+,-,数字之外的字符
	public MyBigInteger(String val) {
		int cursor = 0;
		final int len = val.length();
		if (len == 0) {
            throw new NumberFormatException("Zero length BigInteger");			
		}
        // 检查,最多只有一个前导符号
        int sign = 1;
        int index1 = val.lastIndexOf('-');
        int index2 = val.lastIndexOf('+');
        if (index1 >= 0) { // 存在-号
        	if (index1 != 0 || index2 >= 0) { // 但-号不为第一个符号或还存在+号
        		throw new NumberFormatException("Illegal embedded sign character");
        	}
        	sign = -1; // 为负数
        	cursor = 1;
        } else if (index2 >= 0) { // 存在+号
        	if (index2 != 0) { // 但+号不为第一个符号
                throw new NumberFormatException("Illegal embedded sign character");
        	}
        	cursor = 1;
        }
        if (cursor == len) { // val只含有一个符号
            throw new NumberFormatException("Zero length BigInteger");			
        }
        // 如果含有千分位,
        if (val.contains(",")) { // 如果含有","
        	if (checkFormat(val, cursor)) {
        		StringBuilder sb = new StringBuilder();
        		for (int i = cursor; i < len; ++i) {
        			if (val.charAt(i) != ',') 
        				sb.append(val.charAt(i));
        		}
        		val = sb.toString();
        	} else throw new NumberFormatException("Illegal embedded thousandth");
        }
        
        // 跳过前导0,计算数据位数
        while (cursor < len && 
        		Character.digit(val.charAt(cursor), 10) == 0) { // 十进制数位为0
        	++cursor;
        }
        if (cursor == len) { // 此时传入全0整数
        	d = new int[0];
        	signum = 0;
        	length = 0;
        	return;
        } 
        signum = sign;
        length = len - cursor;
        d = new int[length];
        for (int i = cursor; i < val.length(); ++i) {
        	int v = (int)(val.charAt(len - (i - cursor) - 1) - '0');
        	d[i - cursor] = v;
        }
	}
	
	private boolean checkFormat(String val, int cursor) {
		for (int i = val.length() - 4; i > cursor; i -= 4) {
			if (val.charAt(i) != ',') return false;
		}
		return val.charAt(cursor) != ',';
	}

	// 用传入的int[]和符号位构造(内部使用)
	protected MyBigInteger(int[] mag, int signum) {
		this.d = mag;
		this.signum = signum;
		this.length = mag.length;
	}

	// 比较两者的大小,a>b返回1,a=0返回0,a<b返回-1
	int compare(MyBigInteger a) {
		if (signum != a.signum) return (signum > a.signum ? 1 : -1); // 异号,哪个符号大数字就大
		if (length != a.length) {
			return (length > a.length ? (signum >= 0 ? 1 : -1) 	 // 同号,哪个长度大且为正数就大
					: (signum < 0 ? 1 : -1));					 // 哪个长度小且为负数就大
		}
		// 同号且数据长度相同
		for (int i = length - 1; i >= 0; --i) { // 从高位开始比较
			if (d[i] > a.d[i]) return 1;
			else if (d[i] < a.d[i]) return -1;
		}
		return 0; // 两个大整数相等
	}
	
	// 求大整数的绝对值
	public MyBigInteger abs(MyBigInteger a) {
		if (a.equals(ZERO)) return ZERO; // 0的绝对值为0
		if (a.signum != -1) return a; // 正数和负数的绝对值都是正数
		else return negate(a); // 负数的绝对值是正数(即其相反数)
	}
	// 求大整数的相反数
	public MyBigInteger negate(MyBigInteger a) {
		return new MyBigInteger(a.d, -a.signum);
	}
	
	// 高精度加法
	public MyBigInteger add(MyBigInteger a) {
		if (signum == 0 && a.signum == 0) return ZERO; 		// 都为0
		if (signum == 0) return a;							// 一个为0
		if (a.signum == 0) return this;
		// 两个大整数都不为0
		if (signum == a.signum) { // 同号时,转换为正整数的加法
			StringBuilder sb = new StringBuilder();
			int carry = 0; // 进位
			for (int i = 0; i < length || i < a.length; ++i) { // 从低位相加
				if (i < length) carry += d[i];
				if (i < a.length) carry += a.d[i]; // 两个对应位与进位相加
				sb.append((char)('0' + carry % 10));	// 个位数为该位结果
				carry /= 10;					// 十位数为新的进位
			}
			if (carry != 0) // 如果最后进位不为0,则直接赋给结果的最高位
				sb.append((char)('0' + carry));
			sb.append(signum == 1 ? "" : "-");	// 加法的符号为signum
			return new MyBigInteger(sb.reverse().toString());
		} else { // 异号时,转换为两个正整数之间的减法
			if (signum == -1) // this为负数,a为正数,则this+a等于a-abs(this)
				return a.subtract(abs(this));
			else // this为正数,a为负数,则this+a等于this-abs(a)
				return subtract(abs(a));
		}
	}
	
	// 高精度减法
	public MyBigInteger subtract(MyBigInteger a) {
		if (signum == 0 && a.signum == 0) return ZERO; 	// 都为0
		if (signum == 0) return negate(a); // this=0,返回-a
		if (a.signum == 0) return this; // a为0,this-a返回this
		
		// 两个大整数都不为0时
		if (signum != a.signum) { // 异号时
			if (signum == 1) { // a为负数,this-a=this+abs(a)
				return add(abs(a));
			} else { // this为负数,this-a=-(abs(this)+a)
				return negate(a.add(abs(this)));
			}
			// 可简化为new MyBigInteger(abs(a).add(abs(this)), signum);
		} 
		
		// 同号时,计算a,b的绝对值,用大的绝对值减去小的绝对值,作为结果的绝对值
		int cmp = compare(a);
		if (cmp == 0) return ZERO; // this-a=a-a=0
		int[] resultD = (cmp > 0 ? substract(d, a.d) : substract(a.d, d)); // 绝对值大减小
		return new MyBigInteger(resultD, cmp == signum ? 1 : -1);
	}
	
	// 内部使用
	private int[] substract(int[] a, int[] b) { // a>b,a-b
		ArrayList<Integer> c = new ArrayList<>();
		int higher = a[0], cur = -1, tmpB;
		for (int i = 0; i < a.length || i < b.length; ++i) {
			cur = higher;
			higher = (i + 1 < a.length) ? a[i + 1] : 0;
			tmpB = (i < b.length) ? b[i] : 0;
			if (cur < tmpB) {
				--higher;  // 向高位借1
				cur += 10; // 当前位+10
			}
			c.add((int)(cur - tmpB)); // 减法结果为当前位结果 
		}
		while (c.get(c.size() - 1) == 0) c.remove(c.size() - 1); // 去掉高位0
		int[] result = new int[c.size()];
		int len = 0;
		for (int x : c) result[len++] = x;
		return result;
	}
	
	// 高精度除法
	public MyBigInteger divide(MyBigInteger a) {
		return new MyBigInteger();
	}
	
	// 高精度乘法
	public MyBigInteger multiply(MyBigInteger a) {
		if (signum == 0 || a.signum == 0) return ZERO;

		int[] tmp = new int[length + a.length]; 
		for (int i = 0; i < a.length; ++i) { // this*a
			int carry = 0;
			for (int j = 0; j < length; ++j) {
				int t = a.d[i] * d[j] + carry + tmp[i + j];
				tmp[i + j] = (int)(t % 10);
				carry = t / 10; // 高位部分为新的进位
			}
			if (carry != 0) //a.d的每一位乘以d的最高位的数的进位
				tmp[length + i] = (int)(carry % 10);
		}
		// 两整数相乘,十进制位数要么为两数位数之和,要么为两个位数之和-1
		int len = length + a.length;
		// 检查最高位是否为0,如果为0,退一位
		while (tmp[len - 1] == 0 && len >= 1) --len;
		int[] result = new int[len];
		for (int i = 0; i < len; ++i) result[i] = tmp[i];
		return new MyBigInteger(result, signum * a.signum);
	}
	
	// 用于大整数的简单比较方法
	public boolean equals(MyBigInteger a) {
		if (signum != a.signum) return false;
		if (length != a.length) return false;
		for (int i = 0; i < length; ++i)
			if (d[i] != a.d[i]) return false;
		return true;
	}
	
	// 字符串表示
	public String toString() {
		if (signum == 0) return "0";
		StringBuilder sb = new StringBuilder();
		if (signum == -1) sb.append('-');
		for (int i = length - 1; i >= 0; --i) {
			sb.append((char)(48 + d[i]));
		}
		return sb.toString();
	}
}
