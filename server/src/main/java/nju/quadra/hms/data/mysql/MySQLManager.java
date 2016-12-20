package nju.quadra.hms.data.mysql;

import nju.quadra.hms.util.ServerConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by adn55 on 2016/11/15.
 */
public class MySQLManager {

    private static Connection conn;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null || conn.isClosed() || !conn.isValid(5)) {
            // Open a new connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            ServerConfig config = ServerConfig.getConfig();
            conn = DriverManager.getConnection(
                    "jdbc:mysql://" + config.getDbHost() + "/" + config.getDbName()
                            + "?useUnicode=true&characterEncoding=UTF8&serverTimezone=UTC",
                    config.getDbUser(), config.getDbPass()
            );
        }
        return conn;
    }

}
