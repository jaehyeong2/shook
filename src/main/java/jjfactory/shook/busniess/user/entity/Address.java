package jjfactory.shook.busniess.user.entity;

import jjfactory.shook.busniess.user.dto.req.UserCreate;
import jjfactory.shook.busniess.user.repository.AddressRepository;
import jjfactory.shook.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Address extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String city;
    @Comment("도로명 주소")
    private String street;
    @Comment("우편 번호")
    private String zipCode;

    @Comment("주소 별칭")
    private String alias;

    @Comment("기본 배송지 여부")
    private boolean isBaseAddr;

    @Builder
    public Address(User user, String city, String street, String zipCode, String alias, boolean isBaseAddr) {
        this.user = user;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.alias = alias;
        this.isBaseAddr = isBaseAddr;
    }

    public static Address create(UserCreate dto,User user){
        return Address.builder()
                .user(user)
                .street(dto.getStreet())
                .alias(dto.getAlias())
                .city(dto.getCity())
                .zipCode(dto.getZipCode())
                .isBaseAddr(dto.isBaseAddr())
                .build();
    }
}
