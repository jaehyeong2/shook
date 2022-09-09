package jjfactory.shook.busniess.repository.store.product;

import jjfactory.shook.busniess.domain.store.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
