package pe.memo.memoflashcardsbe.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.memo.memoflashcardsbe.exceptions.domain.ExceptionEntityHandler;
import pe.memo.memoflashcardsbe.exceptions.domain.ExceptionResponseWrapper;

@RestControllerAdvice
public class WebRestAdviceController {

    private final ExceptionEntityHandler exceptionEntityHandler;

    @Autowired
    public WebRestAdviceController(ExceptionEntityHandler exceptionEntityHandler) {
        this.exceptionEntityHandler = exceptionEntityHandler;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseWrapper> handleException(Exception e) {
        return this.exceptionEntityHandler.convertExceptionToResponseEntityWrapper(e);
    }
}
