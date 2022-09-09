package jjfactory.shook.busniess.repository.store.product;

import jjfactory.shook.busniess.domain.store.product.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor,Long> {
    List<ProductColor> findByProductId(Long productId);
}
