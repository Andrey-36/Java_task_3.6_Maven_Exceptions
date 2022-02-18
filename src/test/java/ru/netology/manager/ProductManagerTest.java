package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book = new Book();
    private Book book1 = new Book(1, "book1", 350, "Pushkin");
    private Book book2 = new Book(2, "book2", 400, "Dostoevsky");
    private Smartphone smartphone = new Smartphone();
    private Smartphone smartphone1 = new Smartphone(3, "Smartphone1", 5000, "nokia");
    private Smartphone smartphone2 = new Smartphone(4, "Smartphone2", 5500, "lenovo");

    private Product first = new Product(1, "book1", 350);
    private Product second = new Product(2, "book2", 400);
    private Product third = new Product(3, "Smartphone1", 5000);
    private Product fourth = new Product(4, "Smartphone2", 5500);

    @BeforeEach
    public void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }

    @Test
    void removeByIdDeleteExistingElement() {

        repository.removeById(3);

        Product[] expected = {first, second, fourth};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void removeByIdNotException() {

        Assertions.assertThrows(NotFoundException.class, () -> repository.removeById(5));
    }

    @Test
    void shouldSaveProduct() {
        Product[] expected = {first, second, third, fourth};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    void findById() {
        repository.findById(1);

        Product[] expected = {first};
        Product[] actual = manager.searchBy("book1");

        assertArrayEquals(expected, actual);
    }

    @Test
    void findByIdAboveArray() {

        repository.findById(5);

        Product[] expected = {first, second, third, fourth};
        Product[] actual = repository.findAll();

        assertArrayEquals(expected, actual);
    }


    @Test
    void searchByBookOne() {

        manager.searchBy("book1");

        Product[] expected = {first};
        Product[] actual = manager.searchBy("book1");
        assertArrayEquals(expected, actual);
    }

    @Test
    void searchByBookNotInArray() {

        repository.save(third);

        manager.searchBy("book3");

        Product[] expected = {null};
        Product[] actual = manager.searchBy("book3");
        assertArrayEquals(expected, actual);
    }
}