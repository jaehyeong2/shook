package jjfactory.shook.busniess.repository.product;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.domain.product.QProduct;
import jjfactory.shook.busniess.domain.store.QStore;
import jjfactory.shook.busniess.domain.store.Store;
import jjfactory.shook.busniess.response.product.ProductRes;
import jjfactory.shook.global.entity.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.domain.product.QProduct.*;
import static jjfactory.shook.busniess.domain.store.QStore.*;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ProductRes> findProductsByStoreId(Pageable pageable,Long storeId){
        List<ProductRes> results = queryFactory.select(Projections.constructor(ProductRes.class, product))
                .from(product)
                .where(product.deleteStatus.eq(DeleteStatus.N),
                        product.store.id.eq(storeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(product.createDate.desc())
                .fetch();

        int total = queryFactory.select(Projections.constructor(ProductRes.class, product))
                .from(product)
                .where(product.deleteStatus.eq(DeleteStatus.N))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch().size();

        return new PageImpl<>(results,pageable,total);
    }

    //TODO 이름 포함하는 물건 검색, 가격대로 검색

}
