package com.testman.services;

import com.testman.entities.ProductEntity;
import com.testman.exceptions.TestmanErrorCodes;
import com.testman.exceptions.TestmanException;
import com.testman.models.ProductDao;
import com.testman.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Author: kunitin
 * Created: 19/03/22
 * Info: Implementation logic for product
 **/

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Collection<ProductDao> getProducts() {
        List<ProductEntity> productList = productRepository.findAll();
        Collection<ProductDao> productDaos = new ArrayList<ProductDao>();
        for (ProductEntity productEntity : productList) {
            productDaos.add(modelMapper.map(productEntity, ProductDao.class));
        }

        return productDaos;
    }

    @Override
    public ProductDao addProduct(ProductDao product) {

        try {
            ProductEntity savedProduct = productRepository.save(modelMapper.map(product, ProductEntity.class));
            return modelMapper.map(savedProduct, ProductDao.class);
        } catch (DataIntegrityViolationException e) {
            if (e.getLocalizedMessage().contains("ConstraintViolationException"))
                throw new TestmanException("ConstraintViolationException: Failed to add product due to user error. Check if product name is unique", TestmanErrorCodes.TESTMAN_CLIENT_ERROR);
        } catch (Exception e) {
            throw new TestmanException("Failed to add product", TestmanErrorCodes.TESTMAN_INTERNAL_ERROR, e);
        }
        throw new TestmanException("Failed to add product", TestmanErrorCodes.TESTMAN_INTERNAL_ERROR);
    }

    @Override
    public void deleteProduct(long id) throws Exception {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new TestmanException("Failed to find product to delete", TestmanErrorCodes.PROD_NOT_FOUND);
        }
        productRepository.delete(modelMapper.map(getProduct(id), ProductEntity.class));
        if (containsProduct(id)) {
            throw new TestmanException("Failed to delete product", TestmanErrorCodes.FAILED_TO_DELETE_PRODUCT);
        }
    }

    @Override
    public ProductDao getProduct(long id) throws Exception {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new TestmanException("Failed to find product", TestmanErrorCodes.PROD_NOT_FOUND);
        }
        return modelMapper.map(product, ProductDao.class);
    }

    @Override
    public boolean containsProduct(long id) {
        return productRepository.existsById(id);
    }

    @Override
    public void editProduct(long id, ProductDao productDao) throws TestmanException {
        if (!containsProduct(id)) {
            throw new TestmanException("Failed to find product to edit", TestmanErrorCodes.PROD_NOT_FOUND);
        }
        productRepository.updateProductNameAndDescription(id, productDao.getName(), productDao.getDesc());
    }

}
