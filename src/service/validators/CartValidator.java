package service.validators;

import model.Cart;

public class CartValidator implements Validator<Cart> {

    @Override
    public void validate(Cart cart) {
        if(cart.getCantity()<1){
            throw new IllegalArgumentException("Cantity must be at least");
        }
    }
}
