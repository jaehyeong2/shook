package jjfactory.shook.busniess.domain.order;


import jjfactory.shook.busniess.domain.store.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderProduct {
    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private int totalPrice;
    private int count;

    @Builder
    public OrderProduct(Order order, Product product, int totalPrice, int count) {
        this.order = order;
        this.product = product;
        this.totalPrice = totalPrice;
        this.count = count;
    }

    public static OrderProduct create(Order order,Product product){
        return OrderProduct.builder()
                .order(order)
                .product(product)
                .build();
    }
}
