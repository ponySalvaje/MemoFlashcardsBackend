package pe.memo.memoflashcardsbe.exceptions.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponseWrapper {

    private String title;
    private String code;
    private String message;
}
