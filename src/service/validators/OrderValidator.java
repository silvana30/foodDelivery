package service.validators;

import model.OrderP;

public class OrderValidator implements Validator<OrderP>
{
    @Override
    public void validate(OrderP order) {
        if(order.getPaymentType()==null){
            throw  new IllegalArgumentException("insert a payment type");

        }
        if(order.getTotalPrice()<0.0){
            throw new IllegalArgumentException("total price cannot be negative");

        }
        if(order.getDate()==null){
            throw new IllegalArgumentException("set a date");

        }

    }
}
