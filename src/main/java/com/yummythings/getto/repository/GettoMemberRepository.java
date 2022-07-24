package com.yummythings.getto.repository;

import com.yummythings.getto.domain.GettoMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GettoMemberRepository extends JpaRepository<GettoMember, Long> {

    GettoMember findAllByOauthMemberIdx(Long oauthMemberIdx);


}
