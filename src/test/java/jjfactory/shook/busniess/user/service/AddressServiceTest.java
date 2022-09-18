package jjfactory.shook.busniess.user.service;

import jjfactory.shook.busniess.user.dto.req.AddressUpdate;
import jjfactory.shook.busniess.user.dto.res.AddressDetailRes;
import jjfactory.shook.busniess.user.entity.Address;
import jjfactory.shook.busniess.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class AddressServiceTest {
    @Autowired
    private EntityManager em;

    @Autowired
    AddressService addressService;

    Address address;
    User user;

    @BeforeEach
    void before(){
        user = User.builder().name("wogud").build();
        em.persist(user);

        address = Address.builder().user(user)
                .city("부산").street("614-222").zipCode("05008")
                .alias("부산집").isBaseAddr(true)
                .build();
        em.persist(address);
    }

    //TODO 삭제한 값은 조회못하게 해야함
    @Test
    @DisplayName("주소 상세 조회 성공")
    void findDetailAddress() {
        //given

        //when
        AddressDetailRes res = addressService.findDetailAddress(address.getId());

        //then
        assertThat(res.getAlias()).isEqualTo("부산집");
        assertThat(res.getCity()).isEqualTo("부산");
        assertThat(res.getStreet()).isEqualTo("614-222");
        assertThat(res.getZipCode()).isEqualTo("05008");
        assertThat(res.isBaseAddr()).isEqualTo(true);
    }

    @Test
    @DisplayName("주소 리스트 조회")
    void findMyAddresses() {
        //given
        for (int i = 1; i < 4; i++) {
            Address add = Address.builder().user(user)
                    .city("부산"+i).street("614-22").zipCode("05008")
                    .alias("부산집").isBaseAddr(false)
                    .build();
            em.persist(add);
        }

        //when
        List<AddressDetailRes> result = addressService.findMyAddresses(user);

        //then
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.get(0).getCity()).isEqualTo("부산3");
    }


    //TODO 업데이트 하면 다른놈 기본배송지 빠져야함

    @Test
    @DisplayName("주소 수정 성공")
    void modifyAddress() {
        //given
        AddressUpdate res = AddressUpdate.builder().alias("이름 바뀌냐").city("서울").zipCode("05111")
                .isBaseAddr(false).street("650-123").build();

        //when
        addressService.modifyAddress(user,address.getId(),res);

        //then
        assertThat(res.getAlias()).isEqualTo("이름 바뀌냐");
        assertThat(res.getCity()).isEqualTo("서울");
        assertThat(res.getStreet()).isEqualTo("650-123");
        assertThat(res.getZipCode()).isEqualTo("05111");
        assertThat(res.isBaseAddr()).isEqualTo(false);
    }
}