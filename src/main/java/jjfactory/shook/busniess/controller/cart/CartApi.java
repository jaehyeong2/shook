package jjfactory.shook.busniess.controller.cart;


import jjfactory.shook.busniess.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class CartApi {
    private final CartService cartService;
}
