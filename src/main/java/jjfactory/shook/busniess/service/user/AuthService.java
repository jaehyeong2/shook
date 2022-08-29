package jjfactory.shook.busniess.service.user;

import jjfactory.shook.busniess.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

}
