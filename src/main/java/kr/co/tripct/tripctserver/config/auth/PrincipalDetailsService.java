package kr.co.tripct.tripctserver.config.auth;

import kr.co.tripct.tripctserver.config.oauth.OAuth2DetailsService;
import kr.co.tripct.tripctserver.exception.user.UserNotFoundException;
import kr.co.tripct.tripctserver.user.domain.UserEntity;
import kr.co.tripct.tripctserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        return new PrincipalDetails(userEntity);

    }
}
