package pe.memo.memoflashcardsbe.cards.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.config.jwt.TokenManager;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Optional;

@RestController
@RequestMapping(value = "/cards")
public class CardsController {
    private final CardService cardService;

    @Autowired
    public CardsController(CardService cardService, TokenManager tokenManager) {
        this.cardService = cardService;
        this.tokenManager = tokenManager;
    }

    private final TokenManager tokenManager;

    @GetMapping
    public PageableResponse<Card> getAllBySubjectPageable(@RequestHeader("Authorization") String token,
                                                          @RequestParam("subjectId") String subjectId,
                                                          Integer pageNumber, Integer pageSize) {
        Claims claims = tokenManager.getClaimsFromToken(token.split("Bearer ")[1]);
        return cardService.listCards(pageSize, pageNumber, subjectId, Optional.ofNullable(claims.get("type", String.class)).orElse("free student"));
    }
}
