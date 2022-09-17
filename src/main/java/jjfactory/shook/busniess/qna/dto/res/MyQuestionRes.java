package jjfactory.shook.busniess.qna.dto.res;


import jjfactory.shook.busniess.qna.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@Getter
public class MyQuestionRes {
    private String questionDate;
    private String title;

    public MyQuestionRes(Question question) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.questionDate = question.getCreateDate().format(formatter);
        this.title = question.getTitle();
    }
}
