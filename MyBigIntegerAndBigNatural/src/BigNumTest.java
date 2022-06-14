public class BigNumTest {
	public static void main(String[] args) {
		System.out.println(MyBigInteger.NEGATIVE_ONE);
		System.out.println(MyBigInteger.ONE);
		System.out.println(MyBigInteger.TEN);
		System.out.println(MyBigInteger.TWO);
//		System.out.println(MyBigInteger.MIN_VALUE);
//		System.out.println(MyBigInteger.MAX_VALUE);
		
		System.out.println("测试大整数的加法、减法、乘法：");
		MyBigInteger a = new MyBigInteger("21030540007");
		MyBigInteger b = new MyBigInteger("11");
		System.out.println(a + "+" + b + "=" + a.add(b));
		System.out.println(a + "-" + b + "=" + a.subtract(b));
		System.out.println(a + "*" + b + "=" + a.multiply(b));
		MyBigInteger c = new MyBigInteger(String.valueOf(Long.MAX_VALUE));
		System.out.println(c + "*" + c + "=" + c.multiply(c));
		System.out.println("-1*1234=" + 
				MyBigInteger.NEGATIVE_ONE.multiply(new MyBigInteger("1234"))); // -1*1234

		System.out.println("测试大自然数的加法、减法、乘法：");
		MyBigNatural a1 = new MyBigNatural("21030540007");
		MyBigNatural b1 = new MyBigNatural("11");
		System.out.println(a1 + "+" + b1 + "=" + a.add(b));
		System.out.println(a1 + "-" + b1 + "=" + a.subtract(b));
		System.out.println(a1 + "*" + b1 + "=" + a.multiply(b));
		MyBigNatural c1 = new MyBigNatural(String.valueOf(Long.MAX_VALUE));
		System.out.println(c1 + "*" + c1 + "=" + c1.multiply(c1));
		
		try {
			MyBigNatural d1 = new MyBigNatural("-1"); // 抛出异常
			MyBigNatural d2 = new MyBigNatural("123456789101112");
			System.out.println(d1 + "*" + d2 + "=" + d1.multiply(d2));
		} catch (Exception e) {
			System.out.println("自然数不包含负整数！");
		}
	}
}
