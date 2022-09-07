package jjfactory.shook.busniess.request.qna;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class QuestionUpdate {
    private String title;
    private String content;

    @Builder
    public QuestionUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
