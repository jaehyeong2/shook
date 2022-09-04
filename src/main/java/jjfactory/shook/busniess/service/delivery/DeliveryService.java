package jjfactory.shook.busniess.service.delivery;


import jjfactory.shook.busniess.repository.delivery.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
}
