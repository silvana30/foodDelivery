package dao;

import connection.ConnectionFactory;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product(name,price,ingredients) VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM product where idProduct = ?";

    private final static String deleteStatementString = "DELETE FROM product WHERE idProduct = ?";
    private final static String updateStatementString = "UPDATE product SET  price = ? ,ingredients=? WHERE name = ? ";
    private final static String printStatementString = "SELECT * FROM product";
private final static String getPrice="select price from product where idProduct=?";

    public static Product findById(int Id) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setInt(1, Id);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String ingredients=rs.getString("ingredients");

            toReturn = new Product(Id, name, price,ingredients);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static int insert(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, product.getName());
            insertStatement.setDouble(2, product.getPrice());
            insertStatement.setString(3,product.getIngredients());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    public static double getPrice(int product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        double toReturn=0.0;
        PreparedStatement insertStatement = null;
        ResultSet rs = null;
        try {
            insertStatement = dbConnection.prepareStatement(getPrice);
            insertStatement.setInt(1, product);

            rs = insertStatement.executeQuery();
           rs.next();
           toReturn=rs.getDouble("price");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
    public static void update(Product product) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString);
            //
            findStatement.setDouble(1, product.getPrice());
            findStatement.setString(2,product.getIngredients());
            findStatement.setString(3, product.getName());
            findStatement.executeUpdate();

            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> list = new ArrayList<Product>();
        Product temp;
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(printStatementString);
            rs = findStatement.executeQuery(printStatementString);
            while (rs.next()) {
                temp = new Product();
                temp.setIdProduct(rs.getInt("idProduct"));
                temp.setName(rs.getString("name"));
                temp.setPrice(rs.getDouble("price"));
                temp.setIngredients(rs.getString("ingredients"));


                list.add(temp);
            }
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(int id) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        //PreparedStatement statement_delete = null;
        ResultSet rs = null;
        try {
            //statement_delete = (PreparedStatement) dbConnection.prepareStatement(deleteFromOrder);
            findStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString);
            findStatement.setInt(1, id);
            //statement_delete.setInt(1, id);
           // statement_delete.executeUpdate();
            findStatement.executeUpdate();

            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            //ConnectionFactory.close(statement_delete);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
