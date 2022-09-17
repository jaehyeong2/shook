package jjfactory.shook.busniess.store.entity.product;

import jjfactory.shook.busniess.store.entity.Store;
import jjfactory.shook.busniess.store.dto.req.product.ProductCreate;
import jjfactory.shook.busniess.store.dto.req.product.ProductUpdate;
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
    public Product(Store store, String name, int price, int stockQuantity,String description,int wishCount) {
        this.store = store;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
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
