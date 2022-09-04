package jjfactory.shook.busniess.service.user;

import jjfactory.shook.busniess.domain.DeleteStatus;
import jjfactory.shook.busniess.domain.user.User;
import jjfactory.shook.busniess.repository.user.UserRepository;
import jjfactory.shook.busniess.request.user.LoginReq;
import jjfactory.shook.busniess.request.user.UserCreate;
import jjfactory.shook.busniess.response.user.LoginRes;
import jjfactory.shook.global.config.auth.TokenProvider;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public String signUp(UserCreate userDto){
        usernameDuplicateCheck(userDto);
        String rawPassword = userDto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User.create(userDto,encPassword);
        userRepository.save(user);
        return "Y";
    }

    private void usernameDuplicateCheck(UserCreate userDto) {
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
        }
    }

    public LoginRes login(LoginReq dto){
        User user = userRepository.findByUsername(dto.getUsername());
        if(user.getDeleteStatus().equals(DeleteStatus.Y)){
            throw new BusinessException(ErrorCode.WITH_DRAW_USER);
        }
        matchPassword(dto.getPassword(),user.getPassword());
        String token = createToken(user);

        user.updateLoginTime();
        return new LoginRes(token);
    }



    private void matchPassword(String reqPassword,String userPassword){
        boolean result = passwordEncoder.matches(reqPassword,userPassword);
        if(!result){
            throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
        }
    }

    private String createToken(User user){
        return tokenProvider.createToken(user.getUsername(),user.getRoles());
    }


}
