package jjfactory.shook.busniess.repository.wish;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.domain.wish.QWish;
import jjfactory.shook.busniess.response.wish.WishDetailRes;
import jjfactory.shook.busniess.response.wish.WishRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.domain.wish.QWish.*;

@RequiredArgsConstructor
@Repository
public class WishQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<WishRes> findWishes(Pageable pageable,Long userId){
        List<WishRes> wishList = queryFactory.select(Projections.constructor(WishRes.class, QWish.wish))
                .from(QWish.wish)
                .where(QWish.wish.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        int total = queryFactory.select(Projections.constructor(WishRes.class, wish))
                .from(wish)
                .where(wish.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch().size();

        return new PageImpl<>(wishList,pageable,total);
    }

    public WishDetailRes findWish(Long wishId){
        return queryFactory.select(Projections.constructor(WishDetailRes.class,wish))
                .from(wish)
                .where(wish.id.eq(wishId))
                .fetchOne();
    }
}