package service.validators;

import model.Product;

public class ProductValidator implements Validator<Product> {
    @Override
    public void validate(Product product) {
        if(product.getPrice()<=0){
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if(product.getName()==null){
            throw new IllegalArgumentException("This product does not have a name");
        }
    }
}
