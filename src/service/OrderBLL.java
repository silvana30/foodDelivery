package service;

import model.User;
import service.validators.OrderValidator;
import dao.OrderDAO;
import model.OrderP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class OrderBLL {
    static OrderValidator v=new OrderValidator();
    public static int insertOrder(OrderP order){
        v.validate(order);
        return OrderDAO.insertOrder(order);
    }
    public static void createOrder( double price, String paymentType) {


       OrderDAO.createOrder(price,paymentType);

    }

    public static void generateReport(String username){
        User user=UserBLL.findByUsernameAll(username);
        int id=user.getIdUser();
        ArrayList<OrderP> lista=getMYorders(id);
        File myFile = new File("report"+username+".txt");
        try(PrintWriter writer = new PrintWriter(myFile))
        {
            for (OrderP file : lista)
            {
                writer.println(file.toString());
            }
            writer.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println(ex.toString());
        }

    }


    public static int update(OrderP comanda){
        v.validate(comanda);
        return OrderDAO.update(comanda);
    }
    public static int getMax(){
        return OrderDAO.getMax();
    }


    public static ArrayList<OrderP> getMYorders(int idClient){
        return OrderDAO.getMYorders(idClient);
    }
}
