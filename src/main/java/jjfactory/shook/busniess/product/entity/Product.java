package jjfactory.shook.busniess.product.entity;

import jjfactory.shook.busniess.product.dto.req.ProductCreate;
import jjfactory.shook.busniess.product.dto.req.ProductUpdate;
import jjfactory.shook.busniess.store.entity.Store;
import jjfactory.shook.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Product extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "store_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Store store;
    private String name;
    private String description;
    private int price;
    private int stockQuantity;
    private int wishCount;

    @Builder
    public Product(Store store, String name, String description, int price, int stockQuantity, int wishCount) {
        this.store = store;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.wishCount = wishCount;
    }

    public static Product addProduct(ProductCreate dto, Store store){
        return Product.builder()
                .name(dto.getName())
                .stockQuantity(dto.getStockQuantity())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .store(store)
                .wishCount(0)
                .build();
    }

    public void modify(ProductUpdate dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.stockQuantity = dto.getStockQuantity();
    }

    public void increaseCount() {
        this.wishCount += 1;
    }

    public void decreaseWishCount() {
        this.wishCount -= 1;
    }
}
