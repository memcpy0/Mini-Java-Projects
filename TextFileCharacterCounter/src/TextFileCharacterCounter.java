import java.io.*;

public class TextFileCharacterCounter {
	private static File targetFile; // 对目标文件的字符进行统计
	private static int enNum; // 英文字符
	private static int zhNum; // 中文字符

	public static void setFile(File f) {
		targetFile = f;
		enNum = zhNum = 0;
		try ( // 使用try with resource语法,在结束时自动关闭流
			FileReader rd = new FileReader(targetFile); // 使用字符输入流来统计字符
			BufferedReader bufRd = new BufferedReader(rd); // 使用缓冲,减少大文件读写的耗时
//			BufferedReader bufRd = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile), "utf-8"));
		) {
			String curLine;	// 每次从流中读取一行
			while ((curLine = bufRd.readLine()) != null) {
				enNum += countEnglishCharacterInLine(curLine);
				zhNum += countChineseCharacterInLine(curLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static int countEnglishCharacterInLine(String line) {
		int ec = 0;
		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i);
			if (Character.isUpperCase(c) || Character.isLowerCase(c)) { // 大写字母或小写字母
				++ec; 
			}
		}
		return ec;
	}
	
	private static int countChineseCharacterInLine(String line) {
		int cc = 0;
		for (int i = 0; i < line.length(); ++i) {
			char c = line.charAt(i); // 检查分配给字符的Unicode脚本的枚举常量是否为HAN(即汉字)
			if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
				++cc;
			}
		}
		return cc;
	}
	
	public static int englishNum() { return enNum; }
	public static int chineseNum() { return zhNum; }
}


