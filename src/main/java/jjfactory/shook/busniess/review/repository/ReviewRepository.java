package jjfactory.shook.busniess.review.repository;

import jjfactory.shook.busniess.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
