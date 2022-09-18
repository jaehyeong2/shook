package jjfactory.shook.busniess.user.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import jjfactory.shook.busniess.user.entity.Address;
import jjfactory.shook.busniess.user.entity.QAddress;
import jjfactory.shook.global.entity.DeleteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static jjfactory.shook.busniess.user.entity.QAddress.*;

@Repository
@RequiredArgsConstructor
public class AddressQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Address> findMyAddresses(Long userId){
        return queryFactory.selectFrom(address)
                .where(address.deleteStatus.eq(DeleteStatus.N),
                        address.user.id.eq(userId))
                .orderBy(address.createDate.desc())
                .fetch();
    }
}
