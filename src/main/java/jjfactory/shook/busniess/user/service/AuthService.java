package jjfactory.shook.busniess.user.service;

import jjfactory.shook.busniess.user.entity.Address;
import jjfactory.shook.busniess.user.repository.AddressRepository;
import jjfactory.shook.global.entity.DeleteStatus;
import jjfactory.shook.busniess.user.entity.User;
import jjfactory.shook.busniess.user.repository.UserRepository;
import jjfactory.shook.busniess.user.dto.req.LoginReq;
import jjfactory.shook.busniess.user.dto.req.UserCreate;
import jjfactory.shook.busniess.user.dto.res.LoginRes;
import jjfactory.shook.global.config.auth.TokenProvider;
import jjfactory.shook.global.handler.ex.BusinessException;
import jjfactory.shook.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder encoder;
    private final TokenProvider tokenProvider;

    public Long signUp(UserCreate dto){
        usernameDuplicateCheck(dto);
        String rawPassword = dto.getPassword();
        String encPassword = encoder.encode(rawPassword);

        User user = User.create(dto,encPassword);
        userRepository.save(user);

        Address address = Address.create(dto, user);
        addressRepository.save(address);
        return user.getId();
    }

    public String withDraw(User user,Long userId){
        if(!user.getId().equals(userId)) throw new BusinessException(ErrorCode.HANDLE_ACCESS_DENIED);

        user.delete();
        Address address = addressRepository.findByUserId(user.getId());
        address.delete();
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
