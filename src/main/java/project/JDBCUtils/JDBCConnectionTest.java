package project.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static project.JDBCUtils.JDBC.getNewConnection;

public class JDBCConnectionTest {
    public static void main(String[] args) {
        try {
            Connection connection  = getNewConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
