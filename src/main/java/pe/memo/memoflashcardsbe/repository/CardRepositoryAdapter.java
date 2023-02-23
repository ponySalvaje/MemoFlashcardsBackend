package pe.memo.memoflashcardsbe.repository;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.memo.memoflashcardsbe.cards.business.input.CardRepositoryPort;
import pe.memo.memoflashcardsbe.repository.entities.Card;
import pe.memo.memoflashcardsbe.repository.entities.Subject;
import pe.memo.memoflashcardsbe.utils.PageableResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static pe.memo.memoflashcardsbe.utils.PageableUtils.buildPageRequest;
import static pe.memo.memoflashcardsbe.utils.PageableUtils.convertPageToPageableResponse;

@Component
public class CardRepositoryAdapter implements CardRepositoryPort {

    private final ICardRepository cardRepository;

    private final ISubjectRepository subjectRepository;

    @Autowired
    public CardRepositoryAdapter(ICardRepository cardRepository, ISubjectRepository iSubjectRepository) {
        this.cardRepository = cardRepository;
        this.subjectRepository = iSubjectRepository;
    }

    @Override
    public PageableResponse<Card> listCards(Integer pageSize, Integer pageNumber, String subjectId, Boolean isFreeTier) {
        PageableResponse<Card> result = convertPageToPageableResponse(cardRepository.findAllBySubjectIdAndDeletedAtIsNullAndIsFree(Long.parseLong(subjectId), isFreeTier, buildPageRequest(pageSize, pageNumber)));
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

    @Override
    public Map<String, Integer> countCardsByLesson(Long lessonId) {
        List<Subject> subjects = this.subjectRepository.findAllByLessonId(lessonId);
        Map<String, Integer> countMap = new HashMap<>();
        subjects.forEach(subject -> {
            Integer free = Optional.ofNullable(countMap.get("free")).orElse(0) + this.cardRepository.countAllBySubjectIdAndIsFree(subject.getId(), true);
            Integer premium = Optional.ofNullable(countMap.get("premium")).orElse(0) + this.cardRepository.countAllBySubjectIdAndIsFree(subject.getId(), false);
            countMap.put("free", free);
            countMap.put("premium", premium);
        });
        return countMap;
    }

    private String removeImageTags(String html) {
        Document doc = Jsoup.parse(html);
        doc.select("img").remove();
        return doc.html();
    }
}
