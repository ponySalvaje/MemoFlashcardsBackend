package pe.memo.memoflashcardsbe.cards.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepositoryPort cardRepositoryPort;

    @Autowired
    public CardServiceImpl(CardRepositoryPort cardRepositoryPort) {
        this.cardRepositoryPort = cardRepositoryPort;
    }

    @Override
    public PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId) {
        return this.cardRepositoryPort.listCards(pageSize, pageNumber, subjectId);
    }
}
