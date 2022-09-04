package jjfactory.shook.busniess.repository.cart;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.response.cart.CartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.domain.cart.QCart.*;
import static jjfactory.shook.busniess.domain.store.QStore.*;

@RequiredArgsConstructor
@Repository
public class CartQueryRepository {
    private final JPAQueryFactory queryFactory;
    public List<CartResponse> findCartsNoPaging(Long userId){
        return queryFactory.select(Projections.constructor(CartResponse.class, cart, store))
                .from(cart)
                .innerJoin(store)
                .on(cart.product.store.id.eq(store.id))
                .where(cart.user.id.eq(userId))
                .orderBy(cart.createDate.desc())
                .fetch();
    }

    public Page<CartResponse> findCartsByUserId(Pageable pageable,Long userId){
        List<CartResponse> results = queryFactory.select(Projections.constructor(CartResponse.class, cart, store))
                .from(cart)
                .innerJoin(store)
                .on(cart.product.store.id.eq(store.id))
                .where(cart.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(cart.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(CartResponse.class, cart, store))
                .from(cart)
                .innerJoin(store)
                .on(cart.product.store.id.eq(store.id))
                .where(cart.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch().size();

        return new PageImpl<>(results,pageable,total);
    }
}
