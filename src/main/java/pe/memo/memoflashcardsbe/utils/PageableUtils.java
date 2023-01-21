package pe.memo.memoflashcardsbe.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageableUtils {

    public static PageRequest buildPageRequest(Integer pageSize, Integer pageNumber) {
        return PageRequest.of(
                Optional.ofNullable(pageNumber).map(integer -> integer - 1).orElse(0),
                Optional.ofNullable(pageSize).orElse(10));
    }

    public static <T> PageableResponse<T> convertPageToPageableResponse(Page<T> page){
        return PageableResponse.<T>builder()
                .metadata(Metadata.builder()
                        .contentLength(page.getContent().size())
                        .pageNumber(page.getNumber() + 1)
                        .totalItems(page.getTotalElements())
                        .pageSize(page.getSize())
                        .totalPages(page.getTotalPages())
                        .build())
                .content(page.getContent())
                .build();
    }
}
