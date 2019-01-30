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
 * �������԰���������������ݿⲢ�����ݿ��ȡ��Ӧ�ֶ�
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
	 * ��������ܰ�������ٻ�ȡʵ���Ӧ���ݿ����Ӧ�ֶ� ��ֻ��Ҫ�ṩ��Ӧ��ʵ����,SQL������,�Լ����ݿ�������
	 * 
	 * @param t      ���ʵ����
	 * @param params ��SQL�����?���ֵ
	 * @param SQL    ���SQL���
	 * @param dbname ������ݿ�����
	 * @return ����ֵ��һ��List �����ʵ������������
	 */
	// ���:��𼰶�Ӧ�ֶ��� SQL��� ���ݿ���

	
	public List<T> selectDataForMSSQL(T t, Object[] params, String SQL, String dbname) {

		// ����һ����Ӧ�Ŀյķ��ͼ���
		List<T> list = new ArrayList<T>();
		try {

			// �������ݿ�Ԥ����
			conn = Connecter.getClassConnection(dbname);
			pst = conn.prepareStatement(SQL);

			if (params.length > 0) {

				// �жϲ�������
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
						System.err.println("�Ҳ�������Ϊ" + params[i] + "������,�����㿴���ǲ���д��name��");
						return null;
					}

				}

				// �����������=0 �������ǲ�ѯȫ���� ֱ��get
			} else {

			}

			rs = pst.executeQuery();
			

			// ����ǿ�
			if (rs != null) {
				
				//����м�
				ResultSetMetaData rsmd = rs.getMetaData();
				//����м�����
				int count=rsmd.getColumnCount();
				String name1 = rsmd.getColumnName(1);
				System.out.println("��һ����"+name1);
				String name2 = rsmd.getColumnName(count);
				System.out.println("���һ����"+name2);
				
				System.out.println("ColumnCount:"+count);
//				
				//��������rs����������
				String [] rsnames= new String[count];
				//������rs�����ֶ��� �Ž�rsnames
				for(int i=0;i<count;i++) {
					rsnames[i] = rsmd.getColumnName(i+1);
					System.out.println("����:"+rsnames[i]);
				}
				
				while (rs.next()) {
					
					// ���������
					Class c = t.getClass();
					
					

					// ��ø��������Լ��������ֶ� ���ӷ���Ȩ��
					Field[] fs = c.getDeclaredFields();

					// ����ʵ��
					t = (T) c.newInstance();
					
					//fs.length
					for (int i = 1; i < fs.length; i++) {
						/*
						 * fs[i].getName()������ֶ���
						 * 
						 * f:��õ��ֶ���Ϣ
						 */
						for(int j =0;j<rsnames.length;j++) {
							//�����ݿ�rs���������ʵ��������ֶ����Ƚ� �������set û��������
							if(fs[i].getName().equals(rsnames[j])) {
								//TODO �������ݿ��ѯ�����ֶ��ж��� Ҫ��������ȷ��ʵ���ֶ�����
								Field f = t.getClass().getDeclaredField(fs[i].getName());
								// ����true �ɿ�Խ����Ȩ�޽��в���
								f.setAccessible(true);
								//System.out.println(f.toString());
								/*
								 * f.getType().getName()������ֶ����͵�����
								 * rsmd.getColumnName(i+1)���ݿ�ָ���е�����
								 */
								// �ж������ͽ��и�ֵ����
								if (f.getType().getName().equals(String.class.getName())) {
									f.set(t, rs.getString(fs[i].getName()));
									System.out.println("��ʵ����set��"+fs[i].getName());
								} else if (f.getType().getName().equals(int.class.getName())) {
									f.set(t, rs.getInt(fs[i].getName()));
									System.out.println("��ʵ����set��"+fs[i].getName());
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
