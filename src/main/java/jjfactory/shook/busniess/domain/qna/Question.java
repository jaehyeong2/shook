package jjfactory.shook.busniess.domain.qna;

import jjfactory.shook.busniess.domain.store.product.Product;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.domain.BaseTimeEntity;
import jjfactory.shook.busniess.request.qna.QuestionCreate;
import jjfactory.shook.busniess.request.qna.QuestionUpdate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Question extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String title;
    @Lob
    private String content;

    @Builder
    public Question(User user, Product product, QuestionType questionType, String title, String content) {
        this.user = user;
        this.product = product;
        this.questionType = questionType;
        this.title = title;
        this.content = content;
    }

    public static Question create(QuestionCreate dto, User user, Product product){
        return Question.builder()
                .product(product)
                .user(user)
                .content(dto.getContent())
                .title(dto.getTitle())
                .questionType(dto.getType())
                .build();
    }

    public void modify(QuestionUpdate dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

}
