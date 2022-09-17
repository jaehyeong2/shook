package jjfactory.shook.busniess.delivery.api;


import jjfactory.shook.busniess.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/carts")
@RequiredArgsConstructor
@RestController
public class DeliveryApi {
    private final DeliveryService deliveryService;
}
