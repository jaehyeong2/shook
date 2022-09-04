package jjfactory.shook.busniess.domain.user;

import jjfactory.shook.busniess.request.user.UserCreate;
import jjfactory.shook.busniess.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int wishCount;

    @Enumerated(EnumType.STRING)
    private UserGrade grade;  // 회원 등급
    @Embedded
    private Address address;

    @Comment("0: 통합회원, 1: 카카오 간편가입, 2: 네이버 간편가입")
    private int userType;   // 회원 유형

    private LocalDateTime lastLoginDate;

    @Comment("0: 활동 중인 사용자(일반 상태), 1: N개월 이상 미접속 사용자, 2: 정지된 사용자")
    private int status; // 회원 상태

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> userRole;

    @Builder
    public User(String username, String password, String name, String email, String phone, Gender gender, int wishCount, UserGrade grade, int userType, LocalDateTime lastLoginDate, int status, List<Role> userRole,Address address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.wishCount = wishCount;
        this.grade = grade;
        this.userType = userType;
        this.lastLoginDate = lastLoginDate;
        this.status = status;
        this.userRole = userRole;
        this.address = address;
    }

    public static User create(UserCreate dto, String encPassword){
        return User.builder()
                .username(dto.getUsername())
                .password(encPassword)
                .userType(dto.getUserType())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .grade(UserGrade.FRIEND)
                .userRole(Collections.singletonList(Role.USER))
                .status(0)
                .wishCount(0)
                .address(dto.getAddress())
                .build();
    }

    public void increaseWishCount() {
        this.wishCount += 1;
    }

    public void decreaseWishCount() {
        this.wishCount += 1;
    }

    public void updateLoginTime() {
        this.lastLoginDate = LocalDateTime.now();
    }
}
