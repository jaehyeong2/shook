package jjfactory.shook.busniess.review.service;


import jjfactory.shook.busniess.product.entity.Product;
import jjfactory.shook.busniess.review.entity.Review;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.product.repository.ProductRepository;
import jjfactory.shook.busniess.review.repository.ReviewQueryRepository;
import jjfactory.shook.busniess.review.repository.ReviewRepository;
import jjfactory.shook.busniess.user.repository.UserRepository;
import jjfactory.shook.busniess.review.dto.req.ReviewCreate;
import jjfactory.shook.busniess.review.dto.req.ReviewUpdate;
import jjfactory.shook.busniess.review.dto.res.ReviewRes;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import jjfactory.shook.global.response.PagingRes;
import lombok.RequiredArgsConstructor;
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
        return new PagingRes<>(reviewQueryRepository.findProductsAllReviews(pageable,productId));
    }


    //TODO 이미지 추가
    public Long create(ReviewCreate dto,User user){
        Product product = getProduct(dto.getProductId());
        Review review = Review.create(dto, user, product);

        reviewRepository.save(review);
        return review.getId();
    }

    public String delete(Long reviewId, User user){
        Review review = getReview(reviewId);
        if(!user.getId().equals(review.getUser().getId())) throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
        review.delete();
        return "y";
    }

    public String update(Long reviewId, ReviewUpdate dto, User user){
        Review review = getReview(reviewId);
        if(!user.getId().equals(review.getUser().getId())) throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);
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
