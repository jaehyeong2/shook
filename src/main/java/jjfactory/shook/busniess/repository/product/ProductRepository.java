package jjfactory.shook.busniess.repository.product;

import jjfactory.shook.busniess.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
