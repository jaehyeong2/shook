package jjfactory.shook.busniess.service.wish;

import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.domain.wish.Wish;
import jjfactory.shook.busniess.repository.wish.WishRepository;
import jjfactory.shook.busniess.response.WishDetailRes;
import jjfactory.shook.busniess.response.WishRes;
import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.global.response.PagingRes;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class WishServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    WishService wishService;
    @Autowired
    WishRepository wishRepository;

    static User wogud;
    static Store store;
    static Product productA;

    @BeforeEach
    void init() {
        wogud = User.builder().name("wogud").build();
        em.persist(wogud);
        store = Store.builder().name("storeA").grade("1").build();
        em.persist(store);
        productA = Product.builder().name("productA").price(1000).store(store).build();
        em.persist(productA);
    }

    @Test
    @DisplayName("찜 상세 조회")
    void findOne() {
        // given
        Long wishId = wishService.create(wogud.getId(), productA.getId());

        // when
        WishDetailRes res = wishService.findWishDetail(wishId);

        // then
        assertThat(res.getPrice()).isEqualTo(1000);
        assertThat(res.getProductName()).isEqualTo("productA");
        assertThat(res.getStoreName()).isEqualTo("storeA");
    }

    @Test
    @DisplayName("개인 찜 페이지 성공")
    void WishServiceTest() {
        // given
        for (int i = 0; i < 25; i++) {
            wishService.create(wogud.getId(), productA.getId());
        }

        // when
        PagingRes<WishRes> myWishes = wishService.findMyWishes(PageRequest.of(2, 10), wogud.getId());

        // then
        assertThat(myWishes.getTotalPage()).isEqualTo(3);
        assertThat(myWishes.getTotalCount()).isEqualTo(25);
        assertThat(myWishes.getCurrentPage()).isEqualTo(3);

        //TODO 수정 필요
    }

    @Test
    @DisplayName("찜 성공")
    void create() {
        //when
        wishService.create(wogud.getId(), productA.getId());

        //expected
        assertThat(wogud.getWishCount()).isEqualTo(1);
        assertThat(wishRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("찜 삭제 성공")
    void delete() {
        //given
        Long wishId = wishService.create(wogud.getId(), productA.getId());

        //when
        wishService.delete(wishId);
        Wish wish = getWish(wishId);

        //then
        assertThat(wish.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    private Wish getWish(Long wishId) {
        return wishRepository.findById(wishId).orElseThrow(NoSuchElementException::new);
    }
}