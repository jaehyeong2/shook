package jjfactory.shook.busniess.request;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserCreate {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;

    private int userType;   // 회원 유형

    @Builder
    public UserCreate(String username, String password, String name, String email, String phone, int userType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userType = userType;
    }
}
