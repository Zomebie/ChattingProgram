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
			// 다른 PC의 오라클에 접속하고 싶다면 localhost 대신 해당 PC IP 적기
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";

			// JDBC 드라이버 (ojdbc6.jar)로딩 , 실패 시 ClassNotFoundException
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 오라클에 접속을 한다. 접속 실패시 SQLEXception이 발생한다.
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println(" >>> Database에 연결되었습니다.\n");

		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			// e.printStackTrace();
		}

		// getConnection( ) 메서드는 Connection 반환
		return conn;
	}
}
