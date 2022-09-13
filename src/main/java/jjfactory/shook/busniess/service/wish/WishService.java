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
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
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

//    @Transactional(readOnly = true)
//    public WishDetailRes findWishDetail(Long wishId){
//        return wishQueryRepository.findWish(wishId);
//    }

    @Transactional(readOnly = true)
    public PagingRes<WishRes> findMyWishes(Pageable pageable, User user){
        return new PagingRes<>(wishQueryRepository.findWishes(pageable,user.getId()));
    }

    public Long create(User user,Long productId){
        User findUser = getUser(user.getId());
        Product product = getProduct(productId);

        if(wishQueryRepository.findWishByProductId(user.getId(),productId) != null){
            throw new BusinessException(ErrorCode.DUPLICATE_ENTITY);
        }

        Wish wish = Wish.create(findUser, product);
        wishRepository.save(wish);

        findUser.increaseWishCount();
        product.decreaseWishCount();
        return wish.getId();
    }

    public String delete(User user,Long wishId){
        Wish wish = getWish(wishId);
        if(!wish.getUser().getId().equals(user.getId())){
            throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        }

        wish.delete();

        user.decreaseWishCount();
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
