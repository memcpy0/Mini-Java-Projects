package bank;

public class CashAccount extends BankAccount {
	// 储蓄账户记录所有资金流水,功能与BankAccount没有区别
	public CashAccount(int id, String name, String password, double balance) {
		super(id, name, password, balance);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAccountName() + "'s CashAccount: ");
		sb.append(String.valueOf(getBalance()));
		return sb.toString();
	}
}
