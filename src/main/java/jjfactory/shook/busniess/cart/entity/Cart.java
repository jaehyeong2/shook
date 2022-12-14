package jjfactory.shook.busniess.cart.entity;

import jjfactory.shook.global.entity.BaseTimeEntity;
import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cart extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate
    private LocalDateTime createDate;

    @Builder
    public Cart(Product product, User user, LocalDateTime createDate) {
        this.product = product;
        this.user = user;
        this.createDate = createDate;
    }

    public static Cart create(Product product,User user){
        return Cart.builder()
                .product(product)
                .user(user)
                .build();
    }
}
