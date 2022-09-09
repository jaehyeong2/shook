package jjfactory.shook.busniess.request.store.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProductCreate {
    private int price;
    private int stockQuantity;
    private String name;
    private String description;

    @Builder
    public ProductCreate(int price, int stockQuantity, String name,String description) {
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.description = description;
    }
}
