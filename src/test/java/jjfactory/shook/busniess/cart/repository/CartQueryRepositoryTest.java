package jjfactory.shook.busniess.cart.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.cart.repository.CartQueryRepository;
import jjfactory.shook.busniess.cart.entity.Cart;
import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.store.entity.Store;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.cart.dto.res.CartResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class CartQueryRepositoryTest {
    @Autowired
    JPAQueryFactory queryFactory;
    @Autowired
    EntityManager em;
    @Autowired
    CartQueryRepository repository;

    Store store;
    Product product;

    @Test
    @DisplayName("장바구니 리스트 조회 성공")
    void findCarts() {
        //given
        User wogud = User.builder().name("wogud").build();
        em.persist(wogud);

        createStore();
        createProduct("productA");
        Cart cart = Cart.builder().product(product).user(wogud).build();
        em.persist(cart);

        createProduct("productB");
        Cart cart2 = Cart.builder().product(product).user(wogud).build();
        em.persist(cart2);

        //when
        List<CartResponse> list = repository.findCartsNoPaging(wogud.getId());

        //then
        list.forEach(c -> System.out.println(c.toString()));
        assertThat(list.size()).isEqualTo(2);
    }

    private void createProduct(String productName) {
        product = Product.builder().name(productName).price(1000).store(store).build();
        em.persist(product);
    }

    private void createStore() {
        store = Store.builder().name("store").grade("5").build();
        em.persist(store);
    }
}