package nju.quadra.hms.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adn55 on 2016/11/15.
 */
public class MySQLManager {

    private static Connection conn;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed()) {
            // Open a new connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + MySQLConfig.MYSQL_HOST + "/" + MySQLConfig.MYSQL_DBNAME
                            + "?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC",
                    MySQLConfig.MYSQL_USER, MySQLConfig.MYSQL_PASS
            );
        }
        return conn;
    }

    public static void closeConnection() throws SQLException{
        if(!conn.isClosed()) conn.close();
    }

}
