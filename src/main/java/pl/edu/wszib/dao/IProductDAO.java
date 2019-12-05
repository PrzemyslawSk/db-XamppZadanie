package pl.edu.wszib.dao;

import pl.edu.wszib.model.Product;

public interface IProductDAO {
    void persistProduct(Product product);
    Product getProductById(int productID);
    void updateProduct(Product product);
}
