package org.scaler.firstspringapi.services;

import org.scaler.firstspringapi.models.Product;
import java.util.List;

public interface ProductService {
    public Product getProduct(Long id);

    public List<Product> getAllProducts();

}
