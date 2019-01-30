package cn.teststudy.dbdata;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.teststudy.dbhelp.Connecter;

public class UpdateSQL {
	
	static Connection conn;
	static PreparedStatement pst;
	static ResultSet rs;
	
	// 入参:对应字段名 SQL语句 数据库名
		/**
		 * 这个类用于那些增删改语句,返回一个int类型的整数,如果大于0则说明语句执行成功
		 * 
		 * @param params 你需要向SQL语句中?里填入的值
		 * @param SQL    T-SQL语句
		 * @param dbname 数据库名称
		 * @return j 用来判断T-SQL语句是否执行成功
		 */
		public int updateDataForMSSQL(Object[] params, String SQL, String dbname) {

			int j = 0;
			try {
				// 链接数据库预编译
				conn = Connecter.getClassConnection(dbname);
				pst = conn.prepareStatement(SQL);

				if (params.length > 0) {

					// 判断参数类型
					for (int i = 0; i < params.length; i++) {
						Object param = params[i];
						if (param instanceof Integer) {
							int value = ((Integer) param).intValue();
							pst.setInt(i + 1, value);
						} else if (param instanceof String) {
							String s = (String) param;
							pst.setString(i + 1, s);
						} else if (param instanceof Double) {
							double d = ((Double) param).doubleValue();
							pst.setDouble(i + 1, d);
						} else if (param instanceof Float) {
							float f = ((Float) param).floatValue();
							pst.setFloat(i + 1, f);
						} else if (param instanceof Long) {
							long l = ((Long) param).longValue();
							pst.setLong(i + 1, l);
						} else if (param instanceof Boolean) {
							boolean b = ((Boolean) param).booleanValue();
							pst.setBoolean(i + 1, b);
						} else if (param instanceof Date) {
							Date d = (Date) param;
							pst.setDate(i + 1, (Date) param);
						} else {
							System.err.println("找不到类型为" + params[i] + "的类型");
							return 0;
						}

					}

					// 如果参数长度=0 基本就是查询全部了 直接get
				} else {

				}

				j = pst.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return j;
		}

}
