package jjfactory.shook.busniess.store.repository.product;

import jjfactory.shook.busniess.store.entity.product.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor,Long> {
    List<ProductColor> findByProductId(Long productId);
}
