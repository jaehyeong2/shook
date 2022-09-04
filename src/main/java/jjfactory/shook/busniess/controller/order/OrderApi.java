package jjfactory.shook.busniess.controller.order;


import jjfactory.shook.busniess.service.cart.CartService;
import jjfactory.shook.busniess.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;
}
