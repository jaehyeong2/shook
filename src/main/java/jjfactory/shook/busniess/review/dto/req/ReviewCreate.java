package jjfactory.shook.busniess.review.dto.req;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewCreate {
    private Long productId;
    private int score;
    private String title;
    private String content;
}
