package com.yummythings.getto.repository;

import com.yummythings.getto.domain.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface JwtRepository extends JpaRepository<LoginToken, Long> {
    void deleteLoginTokensByGettoMemberIdx(Long memberIdx);
    Optional<LoginToken> findByRefreshToken(String reissueToken);
}
