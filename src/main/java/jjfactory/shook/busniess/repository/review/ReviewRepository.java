package jjfactory.shook.busniess.repository.review;

import jjfactory.shook.busniess.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
