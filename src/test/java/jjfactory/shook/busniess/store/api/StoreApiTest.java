package jjfactory.shook.busniess.store.api;

import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.store.entity.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class StoreApiTest {
    @Autowired
    EntityManager em;

    @Autowired
    MockMvc mvc;

    Store store;
    Product product;


    @BeforeEach
    void setUp(){
        createStore();
        createProduct();
    }

    @Test
    @DisplayName("상품 단건 조회 성공")
    void findProduct() throws Exception {
        //expected
        mvc.perform(MockMvcRequestBuilders.get("/store/"+store.getId()+"/products/"+product.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("가게 상품목록 조회 성공")
    void findProducts() throws Exception {
        //expected
        mvc.perform(MockMvcRequestBuilders.get("/store/"+store.getId()+"/products"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void createProduct() {
        product = Product.builder().name("product").price(1000).store(store).build();
        em.persist(product);
    }

    private void createStore() {
        store = Store.builder().name("store").grade("5").build();
        em.persist(store);
    }
}