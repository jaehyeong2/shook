package jjfactory.shook.busniess.order.service;


import jjfactory.shook.busniess.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;

//    public Long createOrder(){
//        Order.create()
//    }
}
