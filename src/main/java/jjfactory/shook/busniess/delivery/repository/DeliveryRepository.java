package jjfactory.shook.busniess.delivery.repository;

import jjfactory.shook.busniess.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
