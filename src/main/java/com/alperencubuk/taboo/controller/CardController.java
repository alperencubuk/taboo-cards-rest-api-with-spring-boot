package com.alperencubuk.taboo.controller;

import com.alperencubuk.taboo.model.Card;
import com.alperencubuk.taboo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping("/getCardById/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable long id) {
        return cardService.getCardById(id);
    }

    @GetMapping("/getCard/{word}")
    public ResponseEntity<Card> getCard(@PathVariable String word) {
        return cardService.getCard(word);
    }

    @GetMapping("/getRandomCard")
    public ResponseEntity<Card> getRandomCard() {
        return cardService.getRandomCard();
    }

    @GetMapping("/getCards")
    public ResponseEntity<List<Card>> getCards() {
        return cardService.getCards();
    }

    @PostMapping("/addCard")
    public ResponseEntity<Card> addCard(@RequestBody Card card) {
        return cardService.addCard(card);
    }

    @PostMapping("/addCards")
    public ResponseEntity<List<Card>> addCards(@RequestBody List<Card> cards) {
        return cardService.addCards(cards);
    }

    @PutMapping("/updateCardById/{id}")
    public ResponseEntity<Card> updateCardById(@PathVariable long id, @RequestBody Card card) {
        return cardService.updateCardById(id, card);
    }

    @PutMapping("/updateCard/{word}")
    public ResponseEntity<Card> updateCard(@PathVariable String word, @RequestBody Card card) {
        return cardService.updateCard(word, card);
    }

    @DeleteMapping("/deleteCardById/{id}")
    public void deleteCardById(@PathVariable long id) {
        cardService.deleteCardById(id);
    }

    @DeleteMapping("/deleteCard/{word}")
    public void deleteCard(@PathVariable String word) {
        cardService.deleteCard(word);
    }

    @DeleteMapping("/deleteCards")
    public void deleteCards() {
        cardService.deleteCards();
    }

}
