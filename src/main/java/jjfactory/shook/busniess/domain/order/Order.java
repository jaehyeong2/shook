package jjfactory.shook.busniess.domain.order;


import jjfactory.shook.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
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
}
