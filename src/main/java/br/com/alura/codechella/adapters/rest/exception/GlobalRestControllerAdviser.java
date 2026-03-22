package br.com.alura.codechella.adapters.rest.exception;

import br.com.alura.codechella.domain.exception.DomainException;
import br.com.alura.codechella.domain.exception.EventNotFoundException;
import br.com.alura.codechella.domain.exception.TicketNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviser {

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ProblemDetail> handleException(DomainException ex) {
        final HttpStatus status = switch (ex) {
            //Unnamed pattern Java 21
            case TicketNotFoundException _, EventNotFoundException _ -> HttpStatus.NOT_FOUND;
        };
        final var problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());
        problemDetail.setProperty("errorCode", ex.getErrorCode());
        return ResponseEntity.status(status).body(problemDetail);
    }
}
