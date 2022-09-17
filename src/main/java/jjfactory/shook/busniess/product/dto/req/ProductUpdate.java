package jjfactory.shook.busniess.product.dto.req;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class ProductUpdate {
    private int price;
    private int stockQuantity;
    private String name;

    @Builder
    public ProductUpdate(int price, int stockQuantity, String name) {
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.name = name;
    }
}
