package bank;

public abstract class BankAccount {
	private int id;				// 账户ID
	private String acName;		// 账户名称
	private String password;	// 账户密码
	private double balance;		// 余额
	String[] transactions;		// 记录最后6条事务信息
	
	public BankAccount(int id, String name, String password, double balance) {
		this.id = id;
		this.acName = name;
		this.password = password;
		this.balance = balance;
		transactions = new String[6];
	}
	
	public int getId() {
		return id;
	}
	public String getAccountName() {
		return acName;
	}
	public String getPassword() {
		return password;
	}
	public double getBalance() {
		return balance;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setAccountName(String name) {
		this.acName = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	// 存款
	public void deposit(double money) {
		if (money < 0) throw new IllegalArgumentException("negative deposit");
		this.balance += money;
		addTransaction("Deposit", "+" + String.valueOf(money)); // 增加存款事务
	}
	// 取款
	public void withdraw(double money) {
		if (money < 0 || money > balance) 
			throw new IllegalArgumentException("illegal withdrawal");
		this.balance -= money;
		addTransaction("Withdraw", "-" + String.valueOf(money));
	}
	// 改变账户名称
	public void changeAccountName(String name) {
		setAccountName(name);
	}
	// 查询储蓄余额
	public void checkBalance() { // 保留一位小数格式
		System.out.println("账户余额：" + String.format("%.1f", balance));
	}
	// 记录事务,最多六条
	public void addTransaction(String type, String change) {
		int i;
		for (i = 0; i < 6; ++i) { 
			if (transactions[i] == null) {
				transactions[i] = "事务类型: " + type + ",余额变化: " + change;
				break;
			}
		}
		if (i == 6) { // 无空处时,将后五条前移一位
			for (int j = 0; j < 5; ++j)
				transactions[j] = transactions[j + 1];
			transactions[5] = "事务类型: " + type + ", 余额变化: " + change;
		}
		
	}
	// 输出记录的事务
	public void printTransactions() {
		System.out.println("最近的6个事务如下所示:");
		for (int i = 0; i < 6; ++i) {
			if (transactions[i] == null) break;
			System.out.println("事务" + i + ": " + transactions[i]);
		}
	}
	
	// 用于输出,可以重载
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(acName + "'s Acount: ");
		sb.append(String.valueOf(balance));
		return sb.toString();
	}
}

