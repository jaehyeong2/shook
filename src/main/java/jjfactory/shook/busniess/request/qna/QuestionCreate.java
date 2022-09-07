package jjfactory.shook.busniess.request.qna;


import jjfactory.shook.busniess.domain.qna.QuestionType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuestionCreate {
    private String title;
    private String content;
    private QuestionType type;

    @Builder
    public QuestionCreate(String title, String content, QuestionType type) {
        this.title = title;
        this.content = content;
        this.type = type;
    }
}
