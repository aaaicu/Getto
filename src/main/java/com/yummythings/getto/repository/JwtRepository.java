package com.yummythings.getto.repository;

import com.yummythings.getto.domain.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface JwtRepository extends JpaRepository<LoginToken, Long> {
    void deleteLoginTokensByGettoMemberIdx(Long memberIdx);


    @Query(value = "select l from LoginToken l join fetch l.gettoMember where l.refreshToken = :reissueToken")
    Optional<LoginToken> findByRefreshTokenJoinMember(String reissueToken);
}
