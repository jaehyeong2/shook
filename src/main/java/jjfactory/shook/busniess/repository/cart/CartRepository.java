package jjfactory.shook.busniess.repository.cart;

import jjfactory.shook.busniess.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByProductId(Long productId);
}
