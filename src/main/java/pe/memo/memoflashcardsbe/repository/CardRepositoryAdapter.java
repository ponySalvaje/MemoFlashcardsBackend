package pe.memo.memoflashcardsbe.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import static pe.memo.memoflashcardsbe.utils.PageableUtils.buildPageRequest;
import static pe.memo.memoflashcardsbe.utils.PageableUtils.convertPageToPageableResponse;

@Component
public class CardRepositoryAdapter implements CardRepositoryPort {

    private final ICardRepository cardRepository;

    @Autowired
    public CardRepositoryAdapter(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId) {
        return convertPageToPageableResponse(cardRepository.findAllBySubjectId(subjectId, buildPageRequest(pageSize, pageNumber)));
    }
}
