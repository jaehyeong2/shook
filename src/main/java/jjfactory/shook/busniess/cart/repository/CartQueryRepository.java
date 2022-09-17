package jjfactory.shook.busniess.cart.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.cart.dto.res.CartResponse;
import jjfactory.shook.busniess.cart.entity.QCart;
import jjfactory.shook.busniess.store.entity.QStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.cart.entity.QCart.*;
import static jjfactory.shook.busniess.store.entity.QStore.store;


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
                .fetch().size();

        return new PageImpl<>(results,pageable,total);
    }
}
