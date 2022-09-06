package jjfactory.shook.busniess.request.qna;


import jjfactory.shook.busniess.domain.qna.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuestionCreate {
    private String title;
    private String content;
    private QuestionType type;
}
