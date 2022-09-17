package jjfactory.shook.busniess.review.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.review.entity.QReview;
import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.busniess.review.dto.res.ReviewRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.review.entity.QReview.*;


@RequiredArgsConstructor
@Repository
public class ReviewQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ReviewRes> findProductsAllReviews(Pageable pageable, Long productId){
        List<ReviewRes> results = queryFactory.select(Projections.constructor(ReviewRes.class, review))
                .from(review)
                .where(review.deleteStatus.eq(DeleteStatus.N),
                        review.product.id.eq(productId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(review.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(ReviewRes.class, review))
                .from(review)
                .where(review.deleteStatus.eq(DeleteStatus.N),
                        review.product.id.eq(productId))
                .fetch().size();

        return new PageImpl<>(results,pageable,total);
    }

    //TODO 내가 작성한 리뷰 리스트
}
