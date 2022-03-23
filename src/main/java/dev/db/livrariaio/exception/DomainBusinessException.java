package dev.db.livrariaio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DomainBusinessException extends RuntimeException {

    public DomainBusinessException(String messagem) {
        super(messagem);
    }
}
