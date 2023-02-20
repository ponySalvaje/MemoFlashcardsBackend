package pe.memo.memoflashcardsbe.repository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.HashMap;
import java.util.Map;

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
        PageableResponse<Card> result = convertPageToPageableResponse(cardRepository.findAllBySubjectIdAndDeletedAtIsNull(Long.parseLong(subjectId), buildPageRequest(pageSize, pageNumber)));
        result.getContent().forEach(card -> {
            card.setQuestion(this.removeImageTags(card.getQuestion()));
            card.setAnswer(this.removeImageTags(card.getAnswer()));
            card.setHelp(this.removeImageTags(card.getHelp()));
        });
        return result;
    }

    @Override
    public Map<String, Integer> countCardsBySubjectAndIsFree(Long subjectId) {
        Map<String, Integer> countMap = new HashMap<>();
        countMap.put("free", this.cardRepository.countAllBySubjectIdAndIsFree(subjectId, true));
        countMap.put("premium", this.cardRepository.countAllBySubjectIdAndIsFree(subjectId, false));
        return countMap;
    }

    private String removeImageTags(String html) {
        Document doc = Jsoup.parse(html);
        doc.select("img").remove();
        return doc.html();
    }
}
