package pe.memo.memoflashcardsbe.utils;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PageableResponse<T> {

    Metadata metadata;
    List<T> content;
}

