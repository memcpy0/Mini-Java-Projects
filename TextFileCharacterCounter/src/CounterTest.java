import java.util.Scanner;
import java.io.*;

public class CounterTest {
	public static void main(String[] args) {
		System.out.print("本程序统计指定文本文件中的中英文字符个数，");
		System.out.println("请输入指定文本文件的完整路径：");
		Scanner sc = new Scanner(System.in);
		String filePath = sc.nextLine();
		
		try {
			// 静态调用文本文件字符计数器类,统计字符个数
			TextFileCharacterCounter.setFile(new File(filePath)); // 设置字符计数的目标文件
			System.out.println("英文字符个数为(不含标点符号): " + TextFileCharacterCounter.englishNum());
			System.out.println("中文字符个数为(不含标点符号): " + TextFileCharacterCounter.chineseNum());
		} catch (Exception e) {
			System.out.println("请输入可读取文本文件的正确路径！");
			e.printStackTrace();
		}
	}
}

