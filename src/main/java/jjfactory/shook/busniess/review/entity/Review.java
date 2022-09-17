package jjfactory.shook.busniess.review.entity;

import jjfactory.shook.busniess.store.entity.product.Product;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.global.entity.BaseTimeEntity;
import jjfactory.shook.busniess.review.dto.req.ReviewCreate;
import jjfactory.shook.busniess.review.dto.req.ReviewUpdate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private String title;
    @Lob
    private String content;

    private int score;

    @Builder
    public Review(User user, Product product, String title, String content, int score) {
        this.user = user;
        this.product = product;
        this.title = title;
        this.content = content;
        this.score = score;
    }

    public static Review create(ReviewCreate dto,User user,Product product){
        return Review.builder()
                .user(user)
                .product(product)
                .title(dto.getTitle())
                .content(dto.getContent())
                .score(dto.getScore())
                .build();
    }

    public void modify(ReviewUpdate dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.score = dto.getScore();
    }


}
