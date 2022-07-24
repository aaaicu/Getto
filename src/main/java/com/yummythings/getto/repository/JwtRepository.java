package com.yummythings.getto.repository;

import com.yummythings.getto.domain.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JwtRepository extends JpaRepository<LoginToken, Long> {
    void deleteLoginTokensByGettoMemberIdx(Long memberIdx);
}
