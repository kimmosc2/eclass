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
	
	// ���:��Ӧ�ֶ��� SQL��� ���ݿ���
		/**
		 * �����������Щ��ɾ�����,����һ��int���͵�����,�������0��˵�����ִ�гɹ�
		 * 
		 * @param params ����Ҫ��SQL�����?�������ֵ
		 * @param SQL    T-SQL���
		 * @param dbname ���ݿ�����
		 * @return j �����ж�T-SQL����Ƿ�ִ�гɹ�
		 */
		public int updateDataForMSSQL(Object[] params, String SQL, String dbname) {

			int j = 0;
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
							pst.setDate(i + 1, (Date) param);
						} else {
							System.err.println("�Ҳ�������Ϊ" + params[i] + "������");
							return 0;
						}

					}

					// �����������=0 �������ǲ�ѯȫ���� ֱ��get
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
