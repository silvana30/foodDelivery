package dao;


import connection.ConnectionFactory;
import model.Cart;
import model.OrderP;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO {

    protected static final Logger LOGGER = Logger.getLogger(CartDAO.class.getName());

    private static final String insertSt="insert into cart(idOrder,idProduct,cantity)" + " VALUES (?,?,?)";
    private static final String  cautaComandaString="select * from cart where idCart=?";
    private static final String delete="delete from cart where idCart=?";
    private final static String updateStatementString = "UPDATE cart SET cantity=? where idCart=?";
    private final static String getmycart="select * from cart where idOrder=?";

    public static void insert(int idOrder,int produsId,int cantitate)

    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;

        try {
            insertStatement = dbConnection.prepareStatement(insertSt);



            insertStatement.setInt(1, idOrder);
            insertStatement.setInt(2, produsId);
            insertStatement.setInt(3, cantitate);


            insertStatement.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



        public static Cart findById(int cartId){
        Cart toReturn=null;
        Connection dbConnection= ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs=null;


        try {
            findStatement=dbConnection.prepareStatement(cautaComandaString);
            findStatement.setInt(1,cartId);
            rs=findStatement.executeQuery();
            rs.next();
            int orderId=rs.getInt("idOrder");
            int produsId=rs.getInt("idProduct");
            int cantitate=rs.getInt("cantity");
           // float pret=rs.getFloat("pret_bucata");

            toReturn=new Cart(orderId,produsId,cantitate);


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }

        return toReturn;

    }

    public static void stergeCart(int idCart)
    {
        Connection dbConnection= ConnectionFactory.getConnection();
        PreparedStatement stergeComandaStatement=null;
        Cart cart=findById(idCart);
        Product produs;
        OrderP order;


        try{
            stergeComandaStatement=dbConnection.prepareStatement(delete);



            stergeComandaStatement.setInt(1,idCart);
            stergeComandaStatement.executeUpdate();
            produs=ProductDAO.findById(cart.getIdProduct());

            order=OrderDAO.findById(cart.getIdOrder());
            order.setTotalPrice(order.getTotalPrice()-(double)cart.getCantity()*produs.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateCantity(int cantitate,int id) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement updateStatement = null;


        try {

            updateStatement = dbConnection.prepareStatement(updateStatementString);
            updateStatement.setInt(1,cantitate);
            updateStatement.setInt(2,id);
            updateStatement.executeUpdate();


        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "CartDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

    }



    public static ArrayList<Cart> getMyCarts(int id){
        ArrayList<Cart> list = new ArrayList<Cart>();
        Cart temp;
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(getmycart);
            findStatement.setInt(1,id);
            rs = findStatement.executeQuery();
            while (rs.next()) {
                temp = new Cart();
                temp.setIdCart(rs.getInt("idCart"));
                temp.setIdOrder(rs.getInt("idOrder"));
                temp.setIdProduct(rs.getInt("idProduct"));
                temp.setCantity(rs.getInt("cantity"));


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
}
