package model;

import java.time.LocalDateTime;

public class OrderP {
    private int idOrder;
    private int idClient;
    private String date;
    private String address;
    private double totalPrice;
    private String paymentType;

    public OrderP(String date, String address, double totalPrice) {
        this.date = date;
        this.address = address;
        this.totalPrice=totalPrice;
    }
    public OrderP() {

    }

    public OrderP(int idClient, String date, String address, double totalPrice, String paymentType) {
        this.idClient = idClient;
        this.date = date;
        this.address = address;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
    }

    public OrderP(int idClient, String paymentType) {
        this.idClient = idClient;
        this.paymentType = paymentType;
    }

    public OrderP(int idClient, String address, double totalPrice, String paymentType) {
        this.idClient = idClient;
        this.address = address;
        this.totalPrice = totalPrice;
        this.paymentType = paymentType;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    @Override
    public String toString() {
        return "OrderP{" +
                "idOrder=" + idOrder +
                ", idClient=" + idClient +
                ", date=" + date +
                ", address='" + address + '\'' +
                ", totalPrice=" + totalPrice +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
