package jjfactory.shook.busniess.product.repository;

import jjfactory.shook.busniess.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
