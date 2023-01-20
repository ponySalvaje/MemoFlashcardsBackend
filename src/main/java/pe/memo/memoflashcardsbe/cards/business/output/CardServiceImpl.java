package pe.memo.memoflashcardsbe.cards.business.output;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.cards.business.input.CardService;
import pe.memo.memoflashcardsbe.repository.ICardRepository;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.Metadata;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final ICardRepository cardRepository;

    @Autowired
    public CardServiceImpl(ICardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId) {
        Page<Card> resultPage = cardRepository.findAllBySubjectId(subjectId, PageRequest.of(
                Optional.ofNullable(pageNumber).map(integer -> integer - 1).orElse(0),
                Optional.ofNullable(pageSize).orElse(10)));
        return PageableResponse.<Card>builder()
                .metadata(Metadata.builder()
                        .contentLength(resultPage.getContent().size())
                        .pageNumber(resultPage.getNumber() + 1)
                        .totalItems(resultPage.getTotalElements())
                        .pageSize(resultPage.getSize())
                        .totalPages(resultPage.getTotalPages())
                        .build())
                .content(resultPage.getContent())
                .build();
    }
}
