package jjfactory.shook.busniess.product.repository;

import jjfactory.shook.busniess.product.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    List<ProductSize> findByProductId(Long productId);
}
