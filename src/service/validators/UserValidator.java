package service.validators;

import model.User;

public class UserValidator implements Validator<User>{


    public void validate(User customer) {
        if(customer.getPhone().length()!=10){
            throw new IllegalArgumentException("Wrong phone number");

        }
        if(customer.getCard_nr().length()!=19){
            throw new IllegalArgumentException("Wrong card number");

        }
        if(customer.getAddress()==null){
            throw new IllegalArgumentException("You need to give us your address");
        }
    }
}
