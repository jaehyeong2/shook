package jjfactory.shook.busniess.product.repository;

import jjfactory.shook.busniess.product.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor,Long> {
    List<ProductColor> findByProductId(Long productId);
}
