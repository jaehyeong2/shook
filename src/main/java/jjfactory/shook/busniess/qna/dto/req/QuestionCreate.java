package jjfactory.shook.busniess.qna.dto.req;


import jjfactory.shook.busniess.qna.entity.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuestionCreate {
    private Long productId;
    private String title;
    private String content;
    private QuestionType type;
    private boolean secret;

    @Builder
    public QuestionCreate(String title, String content, QuestionType type,Long productId,boolean secret) {
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.type = type;
        this.secret = secret;
    }
}
