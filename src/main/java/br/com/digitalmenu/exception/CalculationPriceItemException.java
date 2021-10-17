package br.com.digitalmenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CalculationPriceItemException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CalculationPriceItemException(String message) {
        super(message);
    }
}
