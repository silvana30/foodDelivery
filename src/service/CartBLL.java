package service;

import service.validators.CartValidator;
import dao.CartDAO;
import model.Cart;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class CartBLL {

    static CartValidator v=new CartValidator();

    public static void insert(int idOrder, int produsId, int cantitate){

        CartDAO.insert(idOrder, produsId, cantitate);
    }


    public static void updateCantity(int cantitate,int id){
        CartDAO.updateCantity(cantitate, id);
    }


    public static void stergeCart(int idCart){
        CartDAO.stergeCart(idCart);
    }

    public static void update(Cart c){
        v.validate(c);
        CartBLL.update(c);
    }

    public static ArrayList<Cart> getMyCarts(int id){
        return CartDAO.getMyCarts(id);
    }

}
