package jjfactory.shook.busniess.controller.user;


import io.swagger.annotations.ApiOperation;
import jjfactory.shook.busniess.request.user.LoginReq;
import jjfactory.shook.busniess.request.user.UserCreate;
import jjfactory.shook.busniess.response.user.LoginRes;
import jjfactory.shook.busniess.service.user.AuthService;
import jjfactory.shook.global.response.ApiRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthApi {
    private final AuthService authService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ApiRes<LoginRes> login(@Valid @RequestBody LoginReq dto){
        return new ApiRes<>(authService.login(dto));
    }

    @ApiOperation("회원가입")
    @PostMapping("/signup")
    public ApiRes<String> signUp(@Valid @RequestBody UserCreate userDto){
        return new ApiRes<>(authService.signUp(userDto));
    }
}
