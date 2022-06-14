import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuDao { // DAO类是处理数据库相关操作的类
	private static String sqlStr =  "SELECT * FROM STUDENT WHERE Sno = ?;"; // Sno是学号
	public static Student query(Connection conn, Student stu) throws SQLException {
		// 准备SQL语句
		PreparedStatement stmt = conn.prepareStatement(sqlStr);
		stmt.setString(1, stu.getSno()); // 填充?区域
		// 执行并获取结果集
		ResultSet rs = stmt.executeQuery();	// 返回一个二维的结果集
		Student info = null;
		if (rs.next()) { // 查询数据
			info = new Student(rs.getString("Sno"), 
				rs.getString("Sname"),
				rs.getString("Ssex"),
				Integer.valueOf(rs.getString("Sage"))
			);
		}
		if (rs != null) rs.close();
		if (stmt != null) stmt.close();
		return info;
	}
}
