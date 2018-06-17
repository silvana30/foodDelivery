package dao;

import connection.ConnectionFactory;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());

    private final static String printStatementString = "SELECT * FROM user";
    private final static String findStatementString = "SELECT * FROM user where username = ?";
    private static final String insertStatementString = "INSERT INTO user (  username,  password,  phone,  address, card_nr) VALUES (?,?,?,?,?)";
    private final static String deleteStatementString = "DELETE FROM user WHERE username = ?";
    private final static String updateStatementString = "UPDATE user SET password = ?,phone = ?, address=?,card_nr=? WHERE username = ? ";
    private final static String isLogged="select idUser from user where logged=1";
    private final static String isLoggedUser="select username from user where logged=1";
    private final static String loyal="update user set loyal=true where username=?";
    private final static String isLoyal="select loyal from user where idUser=?";
    private final static String logare="update user set logged=true where username=?";
    private final static String delogare="update user set logged=false where logged=true";
    public static ArrayList<User> getusers() {
        ArrayList<User> list = new ArrayList<User>();
        User temp;
        Connection dbConnection = ConnectionFactory.getConnection();///conexiunea
        Statement findStatement = null;//initializare
        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(printStatementString);//codul SQL
            rs = findStatement.executeQuery(printStatementString);//executa comanda sql
            while (rs.next()) {
                temp = new User();
                temp.setIdUser(rs.getInt("idUser"));
                temp.setUsername(rs.getString("username"));
                temp.setPassword(rs.getString("password"));
                temp.setPhone(rs.getString("phone"));
                temp.setAddress(rs.getString("address"));
                temp.setCard_nr(rs.getString("card_nr"));
                temp.setLoyal(rs.getBoolean("loyal"));
                temp.setAdmin(rs.getBoolean("admin"));
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

    public static User findByUsernameAll(String username){
        User toReturn=null;
        Connection Connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs=null;
        try{
            findStatement=Connection.prepareStatement(findStatementString);
            findStatement.setString(1,username);
            rs=findStatement.executeQuery();
            rs.next();

            Integer id=rs.getInt("idUser");
            String password=rs.getString("password");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String card_nr = rs.getString("card_nr");
            Boolean loyal=rs.getBoolean("loyal");
            Boolean admin=rs.getBoolean("admin");
            Boolean logged=rs.getBoolean("logged");

            toReturn=new User(id,username,password,phone,address,card_nr,loyal,admin,logged);



        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:findByUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(Connection);
        }
        return toReturn;

    }

    public static User findByUsername(String username){
        User toReturn=null;
        Connection Connection = ConnectionFactory.getConnection();
        PreparedStatement findStatement=null;
        ResultSet rs=null;
        try{
            findStatement=Connection.prepareStatement(findStatementString);
            findStatement.setString(1,username);
            rs=findStatement.executeQuery();
            rs.next();

            String password=rs.getString("password");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            String card_nr = rs.getString("card_nr");

            toReturn=new User(username,password,phone,address,card_nr);



        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:findByUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(Connection);
        }
        return toReturn;

    }
    public static int insert(User user) {
        Connection Connection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = Connection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, user.getUsername());
            insertStatement.setString(2, user.getPassword());
            insertStatement.setString(3, user.getPhone());
            insertStatement.setString(4,user.getAddress());
            insertStatement.setString(5,user.getCard_nr());


            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(Connection);
        }
        return insertedId;
    }


    public static void delete(String username) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;


        ResultSet rs = null;
        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString);
            findStatement.setString(1, username);
           // statement_delete.setInt(1, id);
           // statement_delete.executeUpdate();
            findStatement.executeUpdate();

            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update( User user) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        //User user=findByUsername(username);

        try {
            findStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString);
            findStatement.setString(1, user.getPassword());
            findStatement.setString(2, user.getPhone());
            findStatement.setString(3, user.getAddress());
            findStatement.setString(4, user.getCard_nr());

            findStatement.setString(5,user.getUsername());
            //findStatement.setBoolean(6,user.isLoyal());
            findStatement.executeUpdate();

            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int isLogged(){
        Connection Connection = ConnectionFactory.getConnection();
        PreparedStatement isLog=null;
        ResultSet rs=null;
        int id=0;
        try{
            isLog=Connection.prepareStatement(isLogged);
            rs=isLog.executeQuery();
            rs.next();


            id = rs.getInt("idUser");

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:findByUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(isLog);
            ConnectionFactory.close(Connection);
        }
        return id;
    }
    public static boolean isLoyal(int idU){
    Connection Connection = ConnectionFactory.getConnection();
    PreparedStatement isLog=null;
    ResultSet rs=null;
    boolean id=false;
    try{
        isLog=Connection.prepareStatement(isLoyal);
        isLog.setInt(1,idU);
        rs=isLog.executeQuery();
        rs.next();


        id = rs.getBoolean("loyal");

    }catch (SQLException e) {
        LOGGER.log(Level.WARNING, "UserDAO:findByUsername " + e.getMessage());
    } finally {
        ConnectionFactory.close(rs);
        ConnectionFactory.close(isLog);
        ConnectionFactory.close(Connection);
    }
    return id;
}
    public static String isLoggedUser(){
        Connection Connection = ConnectionFactory.getConnection();
        PreparedStatement isLog=null;
        ResultSet rs=null;
        String username=null;
        try{
            isLog=Connection.prepareStatement(isLoggedUser);
            rs=isLog.executeQuery();
            rs.next();


            username = rs.getString("username");

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, "UserDAO:findByUsername " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(isLog);
            ConnectionFactory.close(Connection);
        }
        return username;
    }

    public static void delogare(){

        Connection dbConnection= ConnectionFactory.getConnection();

        PreparedStatement delogareStatement=null;
        try{

            delogareStatement=dbConnection.prepareStatement(delogare);


            delogareStatement.executeUpdate();


        }catch(SQLException e)
        {e.printStackTrace();
        }finally {
            ConnectionFactory.close(delogareStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    public static void logare(String username){

        Connection dbConnection= ConnectionFactory.getConnection();
        PreparedStatement logareStatement=null;
        try{
            logareStatement=dbConnection.prepareStatement(logare);

            logareStatement.setString(1,username);

            logareStatement.executeUpdate();


        }catch(SQLException e)
        {e.printStackTrace();
        }finally {
            ConnectionFactory.close(logareStatement);
            ConnectionFactory.close(dbConnection);
        }

    }
    public static void makeLoyal(String username){
        Connection dbConnection= ConnectionFactory.getConnection();
        PreparedStatement logareStatement=null;
        try{
            logareStatement=dbConnection.prepareStatement(loyal);

            logareStatement.setString(1,username);

            logareStatement.executeUpdate();


        }catch(SQLException e)
        {e.printStackTrace();
        }finally {
            ConnectionFactory.close(logareStatement);
            ConnectionFactory.close(dbConnection);
        }
    }
}
