package pl.edu.wszib.db;

import pl.edu.wszib.model.Product;

import java.sql.*;

public class DBConnector {
    public static Connection connection = null;
    public static void connect() {
        // Setup the connection with the DB
        try {
            Class.forName("com.mysql.jdbc.Driver");
            DBConnector.connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/wszib?user=root&password=");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addProduct(Product product) {
            String sql = "INSERT INTO product (productID, productName, productCategory, productPrice) VALUES (?, ?, ?, ?)";

            try {
                PreparedStatement preparedStatement = DBConnector.connection.prepareStatement(sql);
                preparedStatement.setInt(1, product.getProductID());
                preparedStatement.setString(2, product.getProductName());
                preparedStatement.setString(3, product.getProductCategory());
                preparedStatement.setDouble(4, product.getProductPrice());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteProduct(Product product) {
        String sql = "DELETE FROM product WHERE productID = ?;";

        try {
            PreparedStatement preparedStatement = DBConnector.connection.prepareStatement(sql);
            preparedStatement.setInt(1, product.getProductID());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Product getProduct(int productID) {
        String sqlSelect = "SELECT * FROM product WHERE productID = ?";
        try {
            PreparedStatement preparedStatement = DBConnector.connection.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, productID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product productFromDB = new Product();
                productFromDB.setProductID(resultSet.getInt("productID"));
                productFromDB.setProductName(resultSet.getString("productName"));
                productFromDB.setProductCategory(resultSet.getString("productCategory"));
                productFromDB.setProductPrice(resultSet.getDouble("productPrice"));

                    return productFromDB;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
