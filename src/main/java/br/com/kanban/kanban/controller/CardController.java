package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.dto.cardDto;
import br.com.kanban.kanban.model.Card;
import br.com.kanban.kanban.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Long id) {
        return cardService.getCardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/quadro/{quadroId}")
    public List<Card> getCardsByQuadro(@PathVariable Long quadroId) {
        return cardService.getCardsByQuadro(quadroId);
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody cardDto cardDTO) {
        Card card = cardService.createCard(cardDTO);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Long id, @RequestBody cardDto cardDTO) {
        try {
            Card updatedCard = cardService.updateCard(id, cardDTO);
            return ResponseEntity.ok(updatedCard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
