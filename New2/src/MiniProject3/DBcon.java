package MiniProject3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

	public static Connection dbConn;

	public static Connection getConnection() {

		Connection conn = null;

		try {

			String user = "scott";
			String pw = "1234";
			// �ٸ� PC�� ����Ŭ�� �����ϰ� �ʹٸ� localhost ��� �ش� PC IP ����
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// JDBC ����̹� (ojdbc6.jar)�ε� , ���� �� ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// ����Ŭ�� ������ �Ѵ�. ���� ���н� SQLEXception�� �߻��Ѵ�.
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println(" >>> Database�� ����Ǿ����ϴ�.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB ����̹� �ε� ���� :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB ���ӽ��� : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			// e.printStackTrace();
		}

		// getConnection( ) �޼���� Connection ��ȯ
		return conn;
	}
}
