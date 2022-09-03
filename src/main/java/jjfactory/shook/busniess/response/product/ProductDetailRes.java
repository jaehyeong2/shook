package jjfactory.shook.busniess.response.product;

import jjfactory.shook.busniess.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductDetailRes {
    private String name;
    private int price;

    public ProductDetailRes(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
