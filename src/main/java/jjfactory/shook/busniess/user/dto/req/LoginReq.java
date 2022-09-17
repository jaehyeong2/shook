package jjfactory.shook.busniess.user.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginReq {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
