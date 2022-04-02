package com.testman.services;

import com.testman.exceptions.TestmanException;
import com.testman.models.ProductDao;

import java.util.Collection;

public interface ProductService {

    public Collection<ProductDao> getProducts();

    public ProductDao addProduct(ProductDao product);

    public void deleteProduct(long id) throws Exception;

    public ProductDao getProduct(long id) throws Exception;

    public boolean containsProduct(long id);

    public void editProduct(long id, ProductDao productDao) throws TestmanException;


}
