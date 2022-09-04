package jjfactory.shook.busniess.controller.deliviery;


import jjfactory.shook.busniess.service.cart.CartService;
import jjfactory.shook.busniess.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class DeliveryApi {
    private final DeliveryService deliveryService;
}
