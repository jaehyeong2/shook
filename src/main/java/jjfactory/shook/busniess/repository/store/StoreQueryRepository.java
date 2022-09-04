package jjfactory.shook.busniess.repository.store;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
//import jjfactory.shook.busniess.domain.store.QStore;
import jjfactory.shook.busniess.response.store.StoreRes;
import jjfactory.shook.busniess.domain.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.domain.store.QStore.*;

@RequiredArgsConstructor
@Repository
public class StoreQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<StoreRes> findStores(Pageable pageable){
        List<StoreRes> results = queryFactory.select(Projections.constructor(StoreRes.class, store))
                .from(store)
                .where(store.deleteStatus.eq(DeleteStatus.N))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(store.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(StoreRes.class, store))
                .from(store)
                .where(store.deleteStatus.eq(DeleteStatus.N))
                .offset(pageable.getOffset()).fetch().size();

        return new PageImpl<>(results,pageable,total);
    }
}
