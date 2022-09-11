package com.alperencubuk.taboo.service;

import com.alperencubuk.taboo.model.Card;
import com.alperencubuk.taboo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    final int forbiddenWordsCount = 5;

    public ResponseEntity<Card> getCardById(Long id) {
        return cardRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Card> getCard(String word) {
        return cardRepository.findByWord(word).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Card> getRandomCard() {
        return ResponseEntity.ok(getCardById(cardRepository.getRandomCardId()).getBody());
    }

    public ResponseEntity<List<Card>> getCards() {
        return ResponseEntity.ok(cardRepository.findAll());
    }

    public ResponseEntity<Card> addCard(Card card) {
        if (card.getWord() == null || card.getForbidden().length != forbiddenWordsCount) {
            return ResponseEntity.badRequest().build();
        }
        if (cardRepository.existsByWord(card.getWord())) {
            return updateCardById(Objects.requireNonNull(getCard(card.getWord()).getBody()).getId(), card);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cardRepository.save(card));
    }

    public ResponseEntity<List<Card>> addCards(List<Card> cards) {
        List<Card> updateCards = new ArrayList<>();
        for (Iterator<Card> it = cards.iterator(); it.hasNext(); ) {
            Card card = it.next();
            if (card.getWord() == null || card.getForbidden().length != forbiddenWordsCount) {
                return ResponseEntity.badRequest().build();
            }
            if (cardRepository.existsByWord(card.getWord())) {
                Card updatedCard = updateCardById(Objects.requireNonNull(getCard(card.getWord())
                        .getBody()).getId(), card).getBody();
                updateCards.add(updatedCard);
                it.remove();
            }
        }
        cardRepository.saveAll(cards);
        cards.addAll(updateCards);
        return ResponseEntity.status(HttpStatus.CREATED).body(cards);
    }

    public ResponseEntity<Card> updateCardById(Long id, Card cardDetails) {
        if (cardDetails.getWord() == null || cardDetails.getForbidden().length != forbiddenWordsCount) {
            return ResponseEntity.badRequest().build();
        }
        Card card = getCardById(id).getBody();
        if (card != null) {
            card.setWord(cardDetails.getWord());
            card.setForbidden(cardDetails.getForbidden());
            cardRepository.save(card);
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Card> updateCard(String word, Card cardDetails) {
        if (cardDetails.getWord() == null || cardDetails.getForbidden().length != forbiddenWordsCount) {
            return ResponseEntity.badRequest().build();
        }
        Card card = getCard(word).getBody();
        if (card != null) {
            card.setWord(cardDetails.getWord());
            card.setForbidden(cardDetails.getForbidden());
            cardRepository.save(card);
            return ResponseEntity.ok(card);
        }
        return ResponseEntity.notFound().build();
    }

    public void deleteCardById(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
        }
    }

    @Transactional
    public void deleteCard(String word) {
        if (cardRepository.existsByWord(word)) {
            cardRepository.deleteByWord(word);
        }
    }

    public void deleteCards() {
        cardRepository.deleteAll();
    }

}
