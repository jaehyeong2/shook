package jjfactory.shook.busniess.delivery.service;


import jjfactory.shook.busniess.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
}
