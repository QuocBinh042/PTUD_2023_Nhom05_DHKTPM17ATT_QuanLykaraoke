package connectDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection connection = null;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	}
	
//	public static Connection getConnection() {
//		return connection;
//	}
	
	//test connect sql
	public void connect() throws SQLException {

        String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKhachSan;";
        String user = "sa";
        String pass = "sapassword";
        connection = DriverManager.getConnection(URL, user, pass);
        if (connection != null) {
            DatabaseMetaData dm = (DatabaseMetaData) connection.getMetaData();
            System.out.println("Tên Driver: " + dm.getDriverName());
            System.out.println("Phiên bản Driver: " + dm.getDriverVersion());
            System.out.println("Tên Cơ sở dữ liệu: " + dm.getDatabaseProductName());
            System.out.println("Phiên bản Cơ sở dữ liệu: " + dm.getDatabaseProductVersion());
        }               

    }
	public void disconect() {
		if(connection!=null) {
			try {
				connection.close();
				System.out.println("Done");
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static Connection getConnection() {
		return connection;
	}


}
