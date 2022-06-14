package bank;

public class CreditAccount extends BankAccount {
	// 信用卡账户记录所有资金流水,此外还应有一个信用额度
	private double ceiling;		// 信用额度
	private double remainder;   // 剩余额度
	
	public CreditAccount(int id, String name, String password, double balance, double ceiling) {
		super(id, name, password, balance);
		CheckingAccount.checkConstructor(ceiling);
		this.ceiling = ceiling;
		this.remainder = ceiling;
	}
	
	public double getCeiling() {
		return ceiling;
	}
	public void setCeiling(double ceiling) {
		this.ceiling = ceiling;
	}
	
	@Override
	public void withdraw(double change) { // Credit账户可以透支额度,因此需要重写父类的withdraw方法
		CheckingAccount.checkOverdrawn(change, getBalance() + remainder); // 检查是否过度透支
		CheckingAccount.checkNegativeWithdraw(change); // 检查是否取负数金额
		
		if (change <= remainder) remainder -= change;
		else {
			setBalance(getBalance() - (change - remainder));
			remainder = 0; // 用完额度
		}
		addTransaction("Withdraw", "-" + String.valueOf(change));
	}
	
	@Override
	public void checkBalance() { // 重写父类的checkBalance方法
		System.out.println("账户余额：" + String.format("%.1f", getBalance()));
		System.out.println(String.format("信用额度：%.1f，剩余额度：%.1f", ceiling, remainder));
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAccountName() + "'s CreditAccount: ");
		sb.append(String.valueOf(getBalance()));
		return sb.toString();
	}
}