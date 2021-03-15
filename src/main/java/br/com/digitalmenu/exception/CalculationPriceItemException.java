package br.com.digitalmenu.exception;

public class CalculationPriceItemException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CalculationPriceItemException(String message) {
        super(message);
    }
}
