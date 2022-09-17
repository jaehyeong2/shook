package jjfactory.shook.busniess.order.api;


import jjfactory.shook.busniess.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class OrderApi {
    private final OrderService orderService;
}
