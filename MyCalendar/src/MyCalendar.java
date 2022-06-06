/**
 * 根据用户的输入,输出该年日历、或该月日历、或该天星期
 * java MyCalendar 2025
 * java MyCalendar 4 2025
 * java MyCalendar 4 25 2025
 */
import java.util.*;
import java.time.*;

/**
 * @author 张平 21030540006
 * @since 2022/04/09
 */
public class MyCalendar {
	private static int[][] monthDay = {
		{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31},
		{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}
	};
	private static String[] weekDay = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	/**
	 * 判断是否为闰年
	 * @param y 年
	 * @return true表示是闰年
	 */
	private static boolean isLeapYear(int y) {
		return (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0));
	}
	
	/**
	 * 计算某年某月有几天
	 * @param y 年
	 * @param m 月
	 * @return 某年某月的天数
	 */
	private static int getMonthDay(int y, int m) {
		return isLeapYear(y) ? monthDay[0][m] : monthDay[1][m];
	}
	
	/**
	 * 使用Kim Larson's formula计算指定的年月日是星期几
	 * @param y 年
	 * @param m 月
	 * @param d 日
	 * @return 0~6, 对应周一至周日
	 */
	private static int kimLarson(int y, int m, int d) {
		if (m == 1 || m == 2) {
			m += 12;
			--y;
		}
		return (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
	}
	
	/**
	 * 计算一个月的完整月历
	 * @param y 年
	 * @param m 月
	 * @return 某年某月的月历 
	 */
	private static StringBuffer[] getMonthCalendar(int y, int m) {
		// 初始化代表一周的字符串,用于输出
		StringBuffer outWeek = new StringBuffer();
		// 初始化代表一个月的数组,用于输出
		// 参照系统的日历程序,每月输出时显示六周,加上头部为7行
		int weekIdx = 1;
		StringBuffer[] outMonth = new StringBuffer[7];
		outMonth[0] = new StringBuffer(" Mon Tue Wed Thu Fri Sat Sun");
		
		// 这个月有几天
		int dayOfMonth = getMonthDay(y, m);
		// 这个月第一天是星期几
		int target = kimLarson(y, m, 1);
		// 用空格符来对齐每月1号的星期
		for (int i = 0; i < target; ++i) {
			outWeek.append("    ");
		}
		
		// 将第一周剩下的天加入到第一周的字符串
		int day = 1;
		for (int i = target; i < 7; ++i) {
			outWeek.append(String.format("%4d", day++));
		}
		// 将第一周加入月历中
		outMonth[weekIdx++] = outWeek;
		
		// 将其他周依次加入月历中
		while (day <= dayOfMonth) {
			outWeek = new StringBuffer();
			for (int i = 0; i < 7; ++i) {
				outWeek.append(String.format("%4d", day++)); // 输出到每个月的最后一天
				// 最后一周没有结束,但在下个月,这里"补齐"这一周
				if (day > dayOfMonth) {
					for (; i < 6; ++i) {
						outWeek.append("    ");
					}
					break;
				}
			}
			outMonth[weekIdx++] = outWeek;
		}
		
		// 未满六周需要补足
		while (weekIdx <= 6) {
			outMonth[weekIdx++] = new StringBuffer("                            "); // 4*7=28个空格
		}
		return outMonth;
	}
	
	/**
	 * 计算一年的完整年历
	 * @param y 年
	 * @return 某年的年历
	 */
	private static StringBuffer[][] getYearCalendar(int y) {
		// 初始化代表一年的数组,用于输出
		StringBuffer[][] outYear = new StringBuffer[12][7];
		for (int m = 1; m <= 12; ++m) 
			outYear[m - 1] = getMonthCalendar(y, m);
		return outYear;
	}
	
	/**
	 * @param args 命令行参数
	 */
	public static void main(String[] args) {
		int year, month, day;
		if (args.length == 1) { // 输出某年的年历
			year = Integer.parseInt(args[0]);
			month = 0;
			StringBuffer[][] yearCalendar = getYearCalendar(year);
			int line = 4; // 每一行输出四个月的月历
			for (int seg = 0; seg < 12 / line; ++seg) {
				for (int i = 0; i < line; ++i) // 输出这几个月的表头
					System.out.print(yearCalendar[month + i][0] + "\t");
				System.out.println();
				for (int r = 1; r < 7; ++r) {
					for (int i = 0; i < line; ++i) {
						System.out.print(yearCalendar[month + i][r] + "\t");
					}
					System.out.println();
				}
				month += line;
			}
		} else if (args.length == 2) { // 输出某年某月的月历
			month = Integer.parseInt(args[0]);
			year = Integer.parseInt(args[1]);
			StringBuffer[] monthCalendar = getMonthCalendar(year, month);
			for (StringBuffer m : monthCalendar) {
				System.out.println(m);
			}
		} else if (args.length == 3) { // 输出某年某月某日为星期几
			month = Integer.parseInt(args[0]);
			day = Integer.parseInt(args[1]);
			year = Integer.parseInt(args[2]);
			System.out.println(weekDay[kimLarson(year, month, day)]);
		}
	}
}
