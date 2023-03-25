package cc.tianbin.demo.java.spi.javaSPI;

import cc.tianbin.common.util.LogUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by nibnait on 2023/03/25
 */
public class JdbcDemo {

    /**
     * @see DriverManager#loadInitialDrivers()
     */
    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "root");
        LogUtil.printf("MySQL Connected.");
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String username = resultSet.getString("name");
            String password = resultSet.getString("age");
            LogUtil.printf("name: %s, age: %s", username, password);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
}