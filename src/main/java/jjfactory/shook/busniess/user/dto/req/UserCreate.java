package jjfactory.shook.busniess.user.dto.req;


import jjfactory.shook.busniess.user.entity.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class UserCreate {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotNull
    private Gender gender;

    private String city;
    private String street;
    private String zipCode;
    private boolean isBaseAddr;
    private String alias;

    private int userType;   // 회원 유형

    @Builder
    public UserCreate(String username, String password, String name, String email, String phone, Gender gender, String city, String street, String zipCode, boolean isBaseAddr, String alias, int userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.isBaseAddr = isBaseAddr;
        this.alias = alias;
        this.userType = userType;
    }
}
