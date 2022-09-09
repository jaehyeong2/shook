package jjfactory.shook.busniess.service.product;

import jjfactory.shook.busniess.domain.store.product.Product;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.service.store.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class ProductServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    ProductService productService;
    Store store;
    Product product;

    @BeforeEach
    void setUp(){
        createStore();
        createProduct();
    }



    private void createStore() {
        store = Store.builder().name("store").grade("5").build();
        em.persist(store);
    }

    private void createProduct() {
        product = Product.builder().name("product").price(1000).store(store).build();
        em.persist(product);
    }
}