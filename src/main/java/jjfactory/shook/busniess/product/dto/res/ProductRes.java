package jjfactory.shook.busniess.product.dto.res;

import jjfactory.shook.busniess.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRes {
    private Long productId;
    private String name;
    private int price;

    public ProductRes(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
