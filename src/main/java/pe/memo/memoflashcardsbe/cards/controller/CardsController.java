package pe.memo.memoflashcardsbe.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

@RestController
@RequestMapping(value = "/cards")
public class CardsController {
    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public PageableResponse<Card> getAllBySubjectPageable(@RequestParam("subjectId") String subjectId, Integer pageNumber, Integer pageSize) {
        return cardService.listCards(pageSize, pageNumber, subjectId);
    }
}
