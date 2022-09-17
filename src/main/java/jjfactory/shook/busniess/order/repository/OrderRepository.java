package jjfactory.shook.busniess.order.repository;

import jjfactory.shook.busniess.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
