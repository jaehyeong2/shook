package jjfactory.shook.busniess.wish.entity;

import jjfactory.shook.busniess.store.entity.product.Product;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Wish extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Wish(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public static Wish create(User user, Product product){
        return Wish.builder()
                .user(user)
                .product(product)
                .build();
    }
}
