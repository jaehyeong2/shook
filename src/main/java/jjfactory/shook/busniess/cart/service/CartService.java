package jjfactory.shook.busniess.cart.service;


import jjfactory.shook.busniess.cart.entity.Cart;
import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.cart.repository.CartQueryRepository;
import jjfactory.shook.busniess.cart.repository.CartRepository;
import jjfactory.shook.busniess.product.repository.ProductRepository;
import jjfactory.shook.busniess.cart.dto.res.CartResponse;
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
public class CartService {
    private final CartRepository cartRepository;
    private final CartQueryRepository cartQueryRepository;
    private final ProductRepository productRepository;

    public PagingRes<CartResponse> findMyCarts(Pageable pageable, User user){
        return new PagingRes<>(cartQueryRepository.findCartsByUserId(pageable,user.getId()));
    }

    public Long addProductToCart(User user,Long productId){
        Product product = getProduct(productId);
        Cart cart = Cart.create(product, user);
        cartRepository.save(cart);
        return cart.getId();
    }

    public String deleteProduct(User user,Long productId){
        Cart cart = cartRepository.findCartByProductId(productId);
        if(!cart.getUser().getId().equals(user.getId())) throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        cart.delete();

        return "Y";
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }
}
