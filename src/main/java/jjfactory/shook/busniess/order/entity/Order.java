package jjfactory.shook.busniess.order.entity;


import jjfactory.shook.global.entity.BaseTimeEntity;
import jjfactory.shook.busniess.delivery.entity.Delivery;
import jjfactory.shook.busniess.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Order(Delivery delivery, User user) {
        this.delivery = delivery;
        this.user = user;
    }

    public static Order create(Delivery delivery,User user){
        return Order.builder()
                .delivery(delivery)
                .user(user)
                .build();
    }

    //CANCEL은 deleteStatus로
}
