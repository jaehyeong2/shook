package jjfactory.shook.busniess.response.review;


import jjfactory.shook.busniess.domain.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewRes {
    private int score;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private String username;

    public ReviewRes(Review review) {
        this.score = review.getScore();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.createDate = review.getCreateDate();
        this.username = review.getUser().getUsername();
    }
}
