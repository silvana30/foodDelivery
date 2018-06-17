package service;

import service.validators.ProductValidator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ProductBLL {

    static ProductValidator p=new ProductValidator();


    public static double getPrice(int product){
        return ProductDAO.getPrice(product);
    }

    public static int insert(Product product) {
        p.validate(product);
        return ProductDAO.insert(product);
    }

    public static void update(Product client) {
        p.validate(client);
        ProductDAO.update(client);
    }

    public static void deleteById(int id) {
        ProductDAO.delete(id);
    }

    public static ArrayList<Product> getProducts() {
        return ProductDAO.getProducts();

    }
}
