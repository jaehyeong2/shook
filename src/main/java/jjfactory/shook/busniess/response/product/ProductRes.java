package jjfactory.shook.busniess.response.product;

import jjfactory.shook.busniess.domain.product.Product;
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