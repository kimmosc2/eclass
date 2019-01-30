package cn.teststudy.dbhelp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ���������������ٰ����������ݿ�
 * @author <a href="http://www.teststudy.cn">BuTn</a>
 *
 */
public class Connecter {
	
	/*
	 * Microsoft SQL Server Connect
	 * ���:port address accountid password databaseName
	 * ����ֵ Connection
	 */
	/**
	 * �����׼������������MicrosoftSQLServer���ݿ�
	 * @param address ���ݿ�ip��ַ
	 * @param port ���ݿ��Ӧ�˿�
	 * @param dbname ���ݿ�����
	 * @param msid ���ݿ��½�˺�
	 * @param mspwd ���ݿ��½����
	 * @return conn ����ֵΪһ��Connection����
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
	 * �����׼������������MySQL���ݿ�(��������ڿ�����)
	 * @param address ���ݿ�ip��ַ
	 * @param port ���ݿ��Ӧ�˿�
	 * @param dbname ���ݿ�����
	 * @param msid ���ݿ��½�˺�
	 * @param mspwd ���ݿ��½����
	 * @return conn ����ֵΪһ��Connection����
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
	 * ������������ر����ݿ�����ӣ��÷����޷���ֵ
	 * @param conn ����һ��Connection
	 * @param pst ����һ��PreparedStatement
	 * @param rs ����һ��ResultSet
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
	 * ���:  databaseName
	 * ����ֵ Connection
	 * ��������ר��
	 */
	/**
	 * ����������ṩ����Щ���˵�(������������),ʹ���������������Ҫ�ṩһ�����ݿ���<br>
	 * ע:���ò������� <br>
	 * address:127.0.0.1<br>
	 * port:1433<br>
	 * msid:sa<br>
	 * mspassword:123<br>
	 * ͨ�������,�������Ҫ���Ͽε�ʱ��Ѹ�ٹ���һ�״���,�Ƽ�ʹ�ñ�������ֱ��ʹ��{@link cn.teststudy.dbdata.DataManage cn.teststudy.dbdata.DataManage}����
	 * �й���Щ������������ο�{@link getMSSQLConnect getMSSQLConnect} 
	 * @param dbname ���ݿ���
	 * @return <b>conn</b> ����һ��Connection
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
