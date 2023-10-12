package kr.co.tripct.tripctserver.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String email;

    @Builder
    public UserEntity(Long id, String nickname, String email, String oauthId) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }

    public UserEntity(String email) {
        this.email = email;
    }
}
