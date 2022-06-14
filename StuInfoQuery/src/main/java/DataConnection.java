import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DataConnection {
	// JDBC驱动名
	private String driver;
	// 数据库路径
	private String url;
	// 数据库用户名
	private String username;
	// 数据库密码
	private String password;

	// 构造函数
	public DataConnection(String driver, String url, String username, String password) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	// 获取数据库的连接
	public Connection getConnection() {
		Connection conn = null;
		try {
			// 反射加载JDBC的Driver类
			Class.forName(driver);
			// 通过DriverManager获取Connection对象
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.print("ClassNotFoundException: " + e);
		} catch (SQLException e) {
			System.out.print("SQLException: " + e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	// 关闭数据库的连接
	public void closeConnection(Connection conn) throws SQLException {
		if (conn != null) conn.close();
	}
	// 测试连接
	public static void main(String[] args) {
		DataConnection dc = new DataConnection("com.mysql.cj.jdbc.Driver", 
				"jdbc:mysql://localhost:3306/stu_info?useSSL=false&useUnicode=true&characterEncoding=utf-8",
				"root", 
				"wdmysqlmm123");
		try {
			if (dc.getConnection() != null) 
				System.out.println("数据库连接成功!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败!");
		}
	}
}
