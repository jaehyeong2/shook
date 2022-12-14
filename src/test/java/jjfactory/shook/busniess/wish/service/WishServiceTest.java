package jjfactory.shook.busniess.wish.service;

import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.store.entity.Store;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.wish.entity.Wish;
import jjfactory.shook.busniess.wish.repository.WishRepository;
import jjfactory.shook.busniess.wish.dto.res.WishRes;
import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.busniess.wish.service.WishService;
import jjfactory.shook.global.response.PagingRes;
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

//    @Test
//    @DisplayName("찜 상세 조회")
//    void findOne() {
//        // given
//        Long wishId = wishService.create(wogud, productA.getId());
//
//        // when
//        WishDetailRes res = wishService.findWishDetail(wishId);
//
//        // then
//        assertThat(res.getPrice()).isEqualTo(1000);
//        assertThat(res.getProductName()).isEqualTo("productA");
//        assertThat(res.getStoreName()).isEqualTo("storeA");
//    }

    @Test
    @DisplayName("개인 찜 페이지 성공")
    void WishServiceTest() {
        // given
        for (int i = 0; i < 25; i++) {
            wishService.create(wogud, productA.getId());
        }

        // when
        PagingRes<WishRes> myWishes = wishService.findMyWishes(PageRequest.of(2, 10), wogud);

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
        wishService.create(wogud, productA.getId());

        //expected
        assertThat(wogud.getWishCount()).isEqualTo(1);
        assertThat(wishRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("찜 삭제 성공")
    void delete() {
        //given
        Long wishId = wishService.create(wogud, productA.getId());

        //when
        wishService.delete(wogud,wishId);
        Wish wish = getWish(wishId);

        //then
        assertThat(wish.getDeleteStatus()).isEqualTo(DeleteStatus.Y);
    }

    private Wish getWish(Long wishId) {
        return wishRepository.findById(wishId).orElseThrow(NoSuchElementException::new);
    }
}