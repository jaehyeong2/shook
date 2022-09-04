package jjfactory.shook.busniess.repository.order;

import jjfactory.shook.busniess.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
