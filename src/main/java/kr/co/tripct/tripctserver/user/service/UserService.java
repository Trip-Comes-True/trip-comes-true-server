package kr.co.tripct.tripctserver.user.service;

import kr.co.tripct.tripctserver.user.domain.UserEntity;
import kr.co.tripct.tripctserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void registerOAuthUser(UserEntity user) {
        userRepository.save(user);
    }

}
