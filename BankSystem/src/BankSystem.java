import java.util.ArrayList;
import java.util.Scanner;
import bank.*;

public class BankSystem {
	private int acCount;
	private ArrayList<BankAccount> bankAccountList;
	
	public BankSystem() {
		acCount = 0;
		bankAccountList = new ArrayList<>();
	}
	
	// 开户
	public void openAccount(Scanner sc) { 
		System.out.print("选择账户类型(1: CashAccount, 2: CreditAccount)：");
		int choice = sc.nextInt();
		int id;
		String name, password;
		double balance, ceiling;
		
		switch (choice) {
		case 1: // CashAccount
			id = acCount; // 账户ID随开户数量增加
			System.out.print("输入现金账户名称：");
			name = sc.next();
			boolean hasSameName = false;
			for (BankAccount ba : bankAccountList) { // 检查是否有相同的账户名称
				if (ba.getAccountName().equals(name)) {
					hasSameName = true;
					break;
				}
			}
			if (hasSameName) {
				System.out.println("这一账户名称已被使用！");
			} else {
				System.out.print("输入账户密码：");
				password = sc.next();
				System.out.print("输入初始储蓄金额：");
				balance = sc.nextDouble();
				if (balance >= 0) {
					bankAccountList.add(new CashAccount(id, name, password, balance));
					System.out.println("您的账户已成功创建！账户ID为" + id + "！");
					++acCount;
				} else System.out.println("输入了错误的储蓄金额，请重新创建账户！");
			}
			break;
		case 2: // CreditAccount
			id = acCount;
			System.out.print("输入信用账户名称：");
			name = sc.next();
			boolean hasSameName2 = false;
			for (BankAccount ba : bankAccountList) { // 检查是否有相同的账户名称
				if (ba.getAccountName().equals(name)) {
					hasSameName2 = true;
					break;
				}
			}
			if (hasSameName2) {
				System.out.println("这一账户名称已被使用！");
			} else {
				System.out.print("输入账户密码：");
				password = sc.next();
				System.out.print("输入初始储蓄金额：");
				balance = sc.nextDouble();
				System.out.print("输入信用额度：");
				ceiling = sc.nextDouble();
				if (balance >= 0 && ceiling >= 0) {
					bankAccountList.add(new CreditAccount(id, name, password, balance, ceiling));
					System.out.println("您的账户已成功创建！账户ID为" + id + "！");
					++acCount;
				} else System.out.println("输入了错误的金额，请重新创建账户！");
			}
			break;
		default:
			System.out.println("错误输入！");
			break;
		}
	}
	
	// 登录账户
	public void login(Scanner sc) {
		System.out.print("输入账户名称：");
		String name = sc.next();
		System.out.print("输入账户密码：");
		String password = sc.next();
		
		BankAccount yourBA = null;
		for (BankAccount ba : bankAccountList) {
			if (ba.getAccountName().equals(name) && ba.getPassword().equals(password)) { 
				yourBA = ba;
				break;
			}
		}
		if (yourBA == null) {
			System.out.println("输入了错误的用户名或密码！");
		} else {
			System.out.println("成功登录！");
			int choice;
			double change;
			do {
				System.out.println("选择要使用的功能：");
				System.out.println("1. 存款");
				System.out.println("2. 取款");
				System.out.println("3. 检查账户余额");
				System.out.println("4. 改变账户名称");
				System.out.println("5. 打印最近6个事务");
				System.out.println("6. 登出账户"); // 登出账户
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.print("输入存款金额：");
					change = sc.nextDouble();
					yourBA.deposit(change);
					break;
				case 2:
					System.out.print("输入取款金额：");
					change = sc.nextDouble();
					yourBA.withdraw(change);
					break;
				case 3:
					yourBA.checkBalance();
					break;
				case 4:
					System.out.print("输入新的账户名称：");
					name = sc.next();
					yourBA.changeAccountName(name);
					break;
				case 5:
					yourBA.printTransactions();
					break;
				case 6:
					break;
				default:
					System.out.println("错误输入！");
					break;
				}
				
			} while (choice != 6);
		}
	}
	
	// 测试银行系统,相当于用户界面
	public static void main(String[] args) { 
		Scanner sc = new Scanner(System.in);
		BankSystem bs = new BankSystem();
		int choice;
		do {
			System.out.println("选择要使用的功能：");
			System.out.println("1. 开户");
			System.out.println("2. 登录账户");
			System.out.println("3. 退出系统");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				bs.openAccount(sc);
				break;
			case 2:
				bs.login(sc);
				break;
			case 3:
				break;
			default:
				System.out.println("错误输入！");
				break;
			}
		} while (choice != 3);
		sc.close();
	}
}
