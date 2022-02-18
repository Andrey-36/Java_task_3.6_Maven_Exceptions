package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.ProductRepository;


public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save(product);
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[1];
        for (Product product : repository.findAll()) {
            if (matches(product, text)) {
                int lastIndex = result.length - 1;
                result[lastIndex] = product;
            }
        }
        return result;
    }

    public boolean matches(Product product, String search) {
        if (product.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}