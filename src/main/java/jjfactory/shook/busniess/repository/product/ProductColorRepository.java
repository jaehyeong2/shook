package jjfactory.shook.busniess.repository.product;

import jjfactory.shook.busniess.domain.product.ProductColor;
import jjfactory.shook.busniess.domain.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor,Long> {
}
