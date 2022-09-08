package jjfactory.shook.busniess.request.qna;


import jjfactory.shook.busniess.domain.qna.QuestionType;
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

    @Builder
    public QuestionCreate(String title, String content, QuestionType type,Long productId) {
        this.productId = productId;
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
