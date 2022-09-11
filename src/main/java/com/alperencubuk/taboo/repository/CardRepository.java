package com.alperencubuk.taboo.repository;

import com.alperencubuk.taboo.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByWord(String word);

    void deleteByWord(String word);

    boolean existsByWord(String word);

    @Query(value = "SELECT id FROM card ORDER BY random() LIMIT 1", nativeQuery = true)
    long getRandomCardId();

}
