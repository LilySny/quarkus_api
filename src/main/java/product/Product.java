package product;

import category.Category;

/**
 * 
 */
public interface Product {

    public Category getCategory();
    public String getName();
    public double getPrice();

}