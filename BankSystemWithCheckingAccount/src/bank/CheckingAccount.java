package bank;

public class CheckingAccount {
	private static final double eps = 1e-8;
	// 小于函数,用于浮点数比较
	private static boolean less(double a, double b) {
		return (a - b) < (-eps); // 只有小于b-eps的数a才能判断为小于b
	}
	
	// 大于函数,用于浮点数比较
	private static boolean greater(double a, double b) {
		return (a - b) > eps; // 只有大于b+eps的数a才能判断为大于b
	}
	
	// 检查是否存储负数金额进入账户
	public static void checkNegativeDeposit(double amount) {
		if (less(amount, 0.0)) {
			throw new IllegalArgumentException("A negative amount is deposited");
		}
	}
	
	// 检查实例化账户对象时、是否以负数余额/额度构造
	public static void checkConstructor(double amount) {
		if (less(amount, 0.0)) {
			throw new IllegalArgumentException("The account is constructed with a negative amount");
		}
	}
	
	// 检查（储蓄/信用）账户是否过度取款
	public static void checkOverdrawn(double change, double amount) {
		if (greater(change, amount)) {
			throw new IllegalArgumentException("The account is overdrawn");
		}
	}
	
	// 检查取款金额是否为负数
	public static void checkNegativeWithdraw(double amount) {
		if (less(amount, 0.0)) { 
			throw new IllegalArgumentException("A negative amount is withdrawn");
		}
	}
}

