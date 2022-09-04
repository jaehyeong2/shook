package jjfactory.shook.busniess.repository.product;

import jjfactory.shook.busniess.domain.product.ProductImage;
import jjfactory.shook.busniess.domain.product.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
}