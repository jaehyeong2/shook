package jjfactory.shook.busniess.service.wish;


import jjfactory.shook.busniess.domain.store.product.Product;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.domain.wish.Wish;
import jjfactory.shook.busniess.repository.store.product.ProductRepository;
import jjfactory.shook.busniess.repository.user.UserRepository;
import jjfactory.shook.busniess.repository.wish.WishRepository;
import jjfactory.shook.busniess.repository.wish.WishQueryRepository;
import jjfactory.shook.busniess.response.wish.WishDetailRes;
import jjfactory.shook.busniess.response.wish.WishRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class WishService {
    private final WishRepository wishRepository;
    private final WishQueryRepository wishQueryRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public WishDetailRes findWishDetail(Long wishId){
        return wishQueryRepository.findWish(wishId);
    }

    public PagingRes<WishRes> findMyWishes(Pageable pageable, Long userid){
        return new PagingRes<>(wishQueryRepository.findWishes(pageable,userid));
    }

    public Long create(Long userId,Long productId){
        User user = getUser(userId);
        Product product = getProduct(productId);
        Wish wish = Wish.create(user, product);
        wishRepository.save(wish);

        user.increaseWishCount();
        return wish.getId();
    }

    public String delete(Long wishId){
        Wish wish = getWish(wishId);
        wish.delete();

        User user = wish.getUser();
        user.decreaseWishCount();
//        if(user.getWishCount() < 0) throw new
        return "y";
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

    private Wish getWish(Long wishId) {
        return wishRepository.findById(wishId).orElseThrow(NoSuchElementException::new);
    }
}
