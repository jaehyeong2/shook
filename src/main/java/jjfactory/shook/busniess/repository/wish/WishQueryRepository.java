package jjfactory.shook.busniess.repository.wish;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.response.WishDetailRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static jjfactory.shook.busniess.domain.wish.QWish.*;

@RequiredArgsConstructor
@Repository
public class WishQueryRepository {
    private final JPAQueryFactory queryFactory;

    public WishDetailRes findWish(Long wishId){
        return queryFactory.select(Projections.constructor(WishDetailRes.class,wish))
                .from(wish)
                .where(wish.id.eq(wishId))
                .fetchOne();
    }
}
