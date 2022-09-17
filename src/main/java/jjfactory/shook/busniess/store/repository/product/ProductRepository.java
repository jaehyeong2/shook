package jjfactory.shook.busniess.store.repository.product;

import jjfactory.shook.busniess.store.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
