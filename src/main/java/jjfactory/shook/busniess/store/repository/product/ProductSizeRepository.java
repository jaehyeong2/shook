package jjfactory.shook.busniess.store.repository.product;

import jjfactory.shook.busniess.store.entity.product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    List<ProductSize> findByProductId(Long productId);
}
