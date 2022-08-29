package jjfactory.shook.busniess.domain.product;

import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.request.ProductCreate;
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
    private int price;

    @Builder
    public Product(Store store, String name, int price) {
        this.store = store;
        this.name = name;
        this.price = price;
    }

    public static Product create(ProductCreate dto,Store store){
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .store(store)
                .build();
    }
}
