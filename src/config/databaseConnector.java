package config;

import java.sql.*;

public class databaseConnector {

    public Connection connection;
    public PreparedStatement pst;

    public databaseConnector() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
        } catch (SQLException e) {
            System.out.println("Cannot connect to database: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getData(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rst = stmt.executeQuery(sql);

        return rst;
    }

    public void deleteProduct(int id) {
        try {
            PreparedStatement stmt = (PreparedStatement) connection.prepareStatement("DELETE FROM products WHERE p_id = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();

            stmt.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Error deleting data: " + e.getMessage());
        }
    }
}
