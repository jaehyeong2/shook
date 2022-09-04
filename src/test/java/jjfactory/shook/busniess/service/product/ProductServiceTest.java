package jjfactory.shook.busniess.service.product;

import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.request.product.ProductCreate;
import jjfactory.shook.busniess.request.product.ProductUpdate;
import jjfactory.shook.busniess.domain.DeleteStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

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

    @Test
    @DisplayName("상품 등록")
    void create() {
        // given
        ProductCreate dto = ProductCreate.builder()
                .name("product")
                .price(12000)
                .stockQuantity(50)
                .build();

        // when
        Long productId = productService.create(dto, store.getId());

        // then
        assertThat(productId).isNotNull();
    }

    @Test
    @DisplayName("상품 삭제")
    void delete() {
        // when
        product.delete();
        // then
        assertThat(product.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    @Test
    @DisplayName("상품 수정")
    void update() {
        // given
        ProductUpdate dto = ProductUpdate.builder()
                .name("sss")
                .price(12000)
                .stockQuantity(50)
                .build();

        // when
        product.modify(dto);

        // then
        assertThat(product.getName()).isEqualTo("sss");
        assertThat(product.getPrice()).isEqualTo(12000);
        assertThat(product.getStockQuantity()).isEqualTo(50);
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