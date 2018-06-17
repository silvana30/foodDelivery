package dao;

import connection.ConnectionFactory;
import model.Cart;
import model.OrderP;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO {

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());

    final static String createOrder = "insert into orderp(idClient,date,address,totalPrice,paymentType)" + " VALUES ((Select idUser from user where logged=true),?,(Select address from user where logged=true),?,?)";
    private final static String findStatementString = "SELECT * FROM orderp where idOrder = ?";
    private final static String printStatementString = "SELECT * FROM orderp";
    private final static String getOrders="select * from orderp where idClient=?";
    private static final String maxIDStatementString = "select max(idOrder) from orderp";
    private final static String updateStatementString = "UPDATE orderp SET paymentType=?, totalPrice=? where idOrder=?";
    public static int getMax(){
        Connection dbConnection = ConnectionFactory.getConnection();
        Statement st2;
        int id=0;
        try {
            st2 = dbConnection.createStatement();
            ResultSet idMax = st2.executeQuery(maxIDStatementString);
            if (idMax.next()) {
                int insertedId = idMax.getInt(1);
                id+=insertedId;
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
        return id;

    }


    public static int insertOrder(OrderP comanda) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(createOrder, Statement.RETURN_GENERATED_KEYS);
            LocalDateTime ldt = LocalDateTime.now();
            String date=ldt.toString();

            insertStatement.setString(1, date);

            insertStatement.setString(2, comanda.getPaymentType());

            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderPDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static int createOrder(Double totalPrice, String paymentType) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        int insertedId = -1;
        try {



            LocalDateTime ldt = LocalDateTime.now();
            String date=ldt.toString();


            findStatement = (PreparedStatement) dbConnection.prepareStatement(createOrder, Statement.RETURN_GENERATED_KEYS);

            findStatement.setString(1,date);
            findStatement.setDouble(2,totalPrice);

            findStatement.setString(3,paymentType);


            findStatement.executeUpdate();

             rs = findStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }

            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  insertedId;


    }
    public static OrderP findById(int Id) {
        OrderP toReturn = null;

        Connection Connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = Connection.prepareStatement(findStatementString);
            findStatement.setLong(1, Id);
            rs = findStatement.executeQuery();
            rs.next();

            Integer idClient=rs.getInt("idClient");
            String date = rs.getString("date");
            String address = rs.getString("address");
            Double totalPrice=rs.getDouble("totalPrice");
            String paymentType = rs.getString("paymentType");
            toReturn = new OrderP(idClient,date,address,totalPrice,paymentType);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(Connection);
        }
        return toReturn;
    }

    public static ArrayList<OrderP> getorders() {
        ArrayList<OrderP> list = new ArrayList<>();
        OrderP temp;
        Connection dbConnection = ConnectionFactory.getConnection();///conexiunea
        Statement findStatement = null;//initializare
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(printStatementString);//codul SQL
            rs = findStatement.executeQuery(printStatementString);//executa comanda sql
            while (rs.next()) {
                temp = new OrderP();
                temp.setIdOrder(rs.getInt("idOrder"));
                temp.setIdClient(rs.getInt("idClient"));
                temp.setDate(rs.getString("date"));
                temp.setAddress(rs.getString("address"));
                temp.setTotalPrice(rs.getDouble("totalPrice"));
                temp.setPaymentType(rs.getString("paymentType"));

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

    public static ArrayList<OrderP> getMYorders(int idClient) {
        ArrayList<OrderP> list = new ArrayList<>();
        OrderP temp;
        Connection dbConnection = ConnectionFactory.getConnection();///conexiunea
        PreparedStatement findStatement = null;//initializare
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(getOrders);//codul SQL
            findStatement.setInt(1,idClient);
            rs = findStatement.executeQuery();//executa comanda sql
            while (rs.next()) {
                temp = new OrderP();
                temp.setIdOrder(rs.getInt("idOrder"));
                temp.setIdClient(rs.getInt("idClient"));
                temp.setDate(rs.getString("date"));
                temp.setAddress(rs.getString("address"));
                temp.setTotalPrice(rs.getDouble("totalPrice"));
                temp.setPaymentType(rs.getString("paymentType"));

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
    public static int update(OrderP comanda) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;
        int updateId = -1;

        try {

            updateStatement = dbConnection.prepareStatement(updateStatementString, Statement.RETURN_GENERATED_KEYS);

            updateStatement.setString(1, comanda.getPaymentType());
            updateStatement.setDouble(2, comanda.getTotalPrice());
            updateStatement.setInt(3, comanda.getIdOrder());
            updateStatement.executeUpdate();

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updateId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updateId;
    }


}
