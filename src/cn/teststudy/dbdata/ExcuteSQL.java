package cn.teststudy.dbdata;

import java.lang.reflect.Field;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.teststudy.dbhelp.Connecter;

/**
 * 这个类可以帮助你快速连接数据库并从数据库获取相应字段
 * 
 * @author <a href="http://www.teststudy.cn">BuTn</a>
 * 
 * 
 */
public class ExcuteSQL<T> {

	static Connection conn;
	static PreparedStatement pst;
	static ResultSet rs;

	/**
	 * 这个方法能帮助你快速获取实体对应数据库的相应字段 你只需要提供相应的实体类,SQL语句参数,以及数据库名即可
	 * 
	 * @param t      你的实体类
	 * @param params 你SQL语句中?里的值
	 * @param SQL    你的SQL语句
	 * @param dbname 你的数据库名称
	 * @return 返回值是一个List 用你的实体类来接收它
	 */
	// 入参:类别及对应字段名 SQL语句 数据库名

	
	public List<T> selectDataForMSSQL(T t, Object[] params, String SQL, String dbname) {

		// 创建一个对应的空的泛型集合
		List<T> list = new ArrayList<T>();
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
						pst.setDate(i + 1, d);
					} else {
						System.err.println("找不到类型为" + params[i] + "的类型,建议你看看是不是写错name了");
						return null;
					}

				}

				// 如果参数长度=0 基本就是查询全部了 直接get
			} else {

			}

			rs = pst.executeQuery();
			

			// 结果非空
			if (rs != null) {
				
				//获得列集
				ResultSetMetaData rsmd = rs.getMetaData();
				//获得列集总数
				int count=rsmd.getColumnCount();
				String name1 = rsmd.getColumnName(1);
				System.out.println("第一列是"+name1);
				String name2 = rsmd.getColumnName(count);
				System.out.println("最后一列是"+name2);
				
				System.out.println("ColumnCount:"+count);
//				
				//用来储存rs的所有列名
				String [] rsnames= new String[count];
				//便利出rs所有字段名 放进rsnames
				for(int i=0;i<count;i++) {
					rsnames[i] = rsmd.getColumnName(i+1);
					System.out.println("列名:"+rsnames[i]);
				}
				
				while (rs.next()) {
					
					// 反射出类型
					Class c = t.getClass();
					
					

					// 获得该类所有自己声明的字段 无视访问权限
					Field[] fs = c.getDeclaredFields();

					// 创建实例
					t = (T) c.newInstance();
					
					//fs.length
					for (int i = 1; i < fs.length; i++) {
						/*
						 * fs[i].getName()：获得字段名
						 * 
						 * f:获得的字段信息
						 */
						for(int j =0;j<rsnames.length;j++) {
							//把数据库rs里的列名和实体类里的字段名比较 如果有则set 没有则跳过
							if(fs[i].getName().equals(rsnames[j])) {
								//TODO 无论数据库查询出来字段有多少 要遍历出正确的实体字段数量
								Field f = t.getClass().getDeclaredField(fs[i].getName());
								// 参数true 可跨越访问权限进行操作
								f.setAccessible(true);
								//System.out.println(f.toString());
								/*
								 * f.getType().getName()：获得字段类型的名字
								 * rsmd.getColumnName(i+1)数据库指定列的名称
								 */
								// 判断其类型进行赋值操作
								if (f.getType().getName().equals(String.class.getName())) {
									f.set(t, rs.getString(fs[i].getName()));
									System.out.println("向实体里set了"+fs[i].getName());
								} else if (f.getType().getName().equals(int.class.getName())) {
									f.set(t, rs.getInt(fs[i].getName()));
									System.out.println("向实体里set了"+fs[i].getName());
								}
							}
						}
						
					}
					list.add(t);
				}

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
