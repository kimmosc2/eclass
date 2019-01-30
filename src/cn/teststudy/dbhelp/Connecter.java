package cn.teststudy.dbhelp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 这个类可以用来快速帮你连接数据库
 * @author <a href="http://www.teststudy.cn">BuTn</a>
 *
 */
public class Connecter {
	
	/*
	 * Microsoft SQL Server Connect
	 * 入参:port address accountid password databaseName
	 * 返回值 Connection
	 */
	/**
	 * 这个标准方法用于连接MicrosoftSQLServer数据库
	 * @param address 数据库ip地址
	 * @param port 数据库对应端口
	 * @param dbname 数据库名称
	 * @param msid 数据库登陆账号
	 * @param mspwd 数据库登陆密码
	 * @return conn 返回值为一个Connection类型
	 */
	public static Connection getMSSQLConnect(String address,String port,String dbname,String msid,String mspwd){
		
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//jdbc:sqlserver://127.0.0.1:1433;databaseName=mydb
			//jdbc:sqlserver://<server_name>:<port>[;databaseName=<dbname>]
			conn = DriverManager.getConnection("jdbc:sqlserver://"+address+":"+port+";databaseName="+dbname,msid,mspwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 这个标准方法用于连接MySQL数据库(这个类正在开发中)
	 * @param address 数据库ip地址
	 * @param port 数据库对应端口
	 * @param dbname 数据库名称
	 * @param msid 数据库登陆账号
	 * @param mspwd 数据库登陆密码
	 * @return conn 返回值为一个Connection类型
	 */
	public static Connection getMySQLConnect(String address,String port,String dbname,String msid,String mspwd){
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:sqlserver://"+address+":"+port+";databaseName="+dbname,msid,mspwd);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 这个方法用来关闭数据库的连接，该方法无返回值
	 * @param conn 传入一个Connection
	 * @param pst 传入一个PreparedStatement
	 * @param rs 传入一个ResultSet
	 * 
	 */
	public static void closeMSSLQConnect(Connection conn,PreparedStatement pst,ResultSet rs){
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	/*
	 * Microsoft SQL Server Connect For Class
	 * 入参:  databaseName
	 * 返回值 Connection
	 * 课堂懒人专用
	 */
	/**
	 * 这个方法是提供给那些懒人的(包括作者在内),使用这个方法仅仅需要提供一个数据库名<br>
	 * 注:内置参数如下 <br>
	 * address:127.0.0.1<br>
	 * port:1433<br>
	 * msid:sa<br>
	 * mspassword:123<br>
	 * 通常情况下,如果你需要在上课的时候迅速构建一套代码,推荐使用本方法或直接使用{@link cn.teststudy.dbdata.DataManage cn.teststudy.dbdata.DataManage}方法
	 * 有关这些参数的内容请参考{@link getMSSQLConnect getMSSQLConnect} 
	 * @param dbname 数据库名
	 * @return <b>conn</b> 返回一个Connection
	 */
	public static Connection getClassConnection(String dbname){
		
		Connection conn = null;
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//jdbc:sqlserver://127.0.0.1:1433;databaseName=mydb
			//jdbc:sqlserver://<server_name>:<port>[;databaseName=<dbname>]
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName="+dbname,"sa","123");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	

}
