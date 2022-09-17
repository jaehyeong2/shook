package jjfactory.shook.busniess.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
//import jjfactory.shook.busniess.domain.store.QStore;
import jjfactory.shook.busniess.store.dto.res.StoreRes;
import jjfactory.shook.busniess.store.entity.QStore;
import jjfactory.shook.global.entity.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static jjfactory.shook.busniess.store.entity.QStore.*;


@RequiredArgsConstructor
@Repository
public class StoreQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<StoreRes> findStores(Pageable pageable,String query){
        List<StoreRes> results = queryFactory.select(Projections.constructor(StoreRes.class, store))
                .from(store)
                .where(store.deleteStatus.eq(DeleteStatus.N),
                        contains(query))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(store.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(StoreRes.class, store))
                .from(store)
                .where(store.deleteStatus.eq(DeleteStatus.N),
                        contains(query))
                .fetch().size();

        return new PageImpl<>(results,pageable,total);
    }

    private BooleanExpression contains(String query){
        if(!StringUtils.hasText(query)){
            return null;
        }
        return store.name.contains(query);
    }
}
