package jjfactory.shook.busniess.request.review;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewUpdate {
    private int score;
    private String title;
    private String content;
}
