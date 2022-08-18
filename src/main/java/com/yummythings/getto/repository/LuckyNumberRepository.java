package com.yummythings.getto.repository;

import com.yummythings.getto.domain.LuckyNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LuckyNumberRepository extends JpaRepository<LuckyNumber, Long> {

    Optional<LuckyNumber> findFirstByRound(Integer round);

    Optional<LuckyNumber> findFirstByOrderByRoundDesc();

}
