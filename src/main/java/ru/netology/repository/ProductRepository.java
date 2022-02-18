package ru.netology.repository;

import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;

public class ProductRepository {
    private Product[] items = new Product[0];

    public void save(Product item) {
        int length = items.length + 1;
        Product[] tmp = new Product[length];
        System.arraycopy(items, 0, tmp, 0, items.length);
        int LastIndex = tmp.length - 1;
        tmp[LastIndex] = item;
        items = tmp;
    }

    public Product[] findAll() {
        return items;
    }

    public Product findById(int id) {
        for (Product item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeById(int id) throws NotFoundException {
        if (findById(id) == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        int length = items.length - 1;
        Product[] tmp = new Product[length];
        int index = 0;
        for (Product item : items) {
            if (item.getId() != id) {
                tmp[index] = item;
                index++;
            }
        }
        items = tmp;
    }
}