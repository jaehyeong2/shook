package jjfactory.shook.busniess.cart.repository;

import jjfactory.shook.busniess.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByProductId(Long productId);
}
