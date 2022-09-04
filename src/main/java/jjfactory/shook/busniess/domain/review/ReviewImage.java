package jjfactory.shook.busniess.domain.review;

import jjfactory.shook.busniess.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ReviewImage extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "review_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    private String originalName;
    private String path;

    @Builder
    public ReviewImage(Review review, String originalName, String path) {
        this.review = review;
        this.originalName = originalName;
        this.path = path;
    }
}
