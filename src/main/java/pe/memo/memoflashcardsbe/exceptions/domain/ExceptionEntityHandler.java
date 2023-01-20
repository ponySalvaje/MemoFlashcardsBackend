package pe.memo.memoflashcardsbe.exceptions.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MissingServletRequestParameterException;

@Component
@Slf4j
public class ExceptionEntityHandler {

    public ResponseEntity<ExceptionResponseWrapper> convertExceptionToResponseEntityWrapper(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof MissingServletRequestParameterException) {
            return ResponseEntity.status(400).body(ExceptionResponseWrapper.builder()
                    .title("BAD_REQUEST")
                    .code("400")
                    .message(String.format("Missing parameter %s with type %s from request!", ((MissingServletRequestParameterException) e).getParameterName(), ((MissingServletRequestParameterException) e).getParameterType()))
                    .build());
        } else {
            return ResponseEntity.status(500).body(ExceptionResponseWrapper.builder().code("500").message(e.getMessage()).build());
        }
    }
}
