package pe.memo.memoflashcardsbe.cards.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.cards.business.output.CardService;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Map;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    private final CardRepositoryPort cardRepositoryPort;

    @Autowired
    public CardServiceImpl(CardRepositoryPort cardRepositoryPort) {
        this.cardRepositoryPort = cardRepositoryPort;
    }

    @Override
    public PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId, String memberTier) {
        boolean isFreeTier;
        if (memberTier.equals("free student")) {
            isFreeTier = true;
        } else if (memberTier.equals("pro student")) {
            isFreeTier = false;
        } else {
            log.warn("Attempting to get cards for a non free nor premium user, defaulting to free user!");
            isFreeTier = true;
        }
        return this.cardRepositoryPort.listCards(pageSize, pageNumber, subjectId, isFreeTier);
    }

    @Override
    public Map<String, Integer> countCardsBySubjectId(Long subjectId) {
        return this.cardRepositoryPort.countCardsBySubjectAndIsFree(subjectId);
    }
}
