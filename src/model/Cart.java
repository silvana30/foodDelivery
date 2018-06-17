package model;

public class Cart {
    private int idCart;
    private int idOrder;
    private int idProduct;
    private int cantity;


    public Cart() {
    }

    public Cart(int idOrder, int idProduct, int cantity) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.cantity = cantity;
    }


    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getCantity() {
        return cantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", cantity=" + cantity +
                '}';
    }

    public void setCantity(int cantity) {
        this.cantity = cantity;
    }
}
