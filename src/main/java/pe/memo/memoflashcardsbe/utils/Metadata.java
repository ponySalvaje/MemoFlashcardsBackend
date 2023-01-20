package pe.memo.memoflashcardsbe.utils;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Metadata {
    Integer contentLength;
    Integer pageNumber;
    Long totalItems;
    Integer totalPages;
    Integer pageSize;
}
