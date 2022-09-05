package jjfactory.shook.busniess.service.review;


import jjfactory.shook.busniess.domain.product.Product;
import jjfactory.shook.busniess.domain.review.Review;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.repository.product.ProductRepository;
import jjfactory.shook.busniess.repository.review.ReviewQueryRepository;
import jjfactory.shook.busniess.repository.review.ReviewRepository;
import jjfactory.shook.busniess.repository.user.UserRepository;
import jjfactory.shook.busniess.request.review.ReviewCreate;
import jjfactory.shook.busniess.request.review.ReviewUpdate;
import jjfactory.shook.busniess.response.product.ProductRes;
import jjfactory.shook.busniess.response.review.ReviewRes;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewQueryRepository reviewQueryRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public PagingRes<ReviewRes> findReviewsByProductId(Pageable pageable, Long productId){
        return new PagingRes<>(reviewQueryRepository.findReviews(pageable,productId));
    }


    //TODO 이미지 추가
    public Long create(ReviewCreate dto,User user){
        Product product = getProduct(dto.getProductId());
        Review review = Review.create(dto, user, product);

        reviewRepository.save(review);
        return review.getId();
    }

    public String delete(Long reviewId){
        Review review = getReview(reviewId);
        review.delete();
        return "y";
    }

    public String update(Long reviewId, ReviewUpdate dto){
        Review review = getReview(reviewId);
        review.modify(dto);
        return "y";
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
    }

    private Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
    }
}
