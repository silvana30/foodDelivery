package model;

public class Product {

    private int idProduct;
    private String name;
    private double price;
    private String ingredients;

    public Product(String name, double price, String ingredients) {

        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public Product() {
    }

    public Product(int idProduct, String name, double price, String ingredients) {
        this.idProduct = idProduct;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
