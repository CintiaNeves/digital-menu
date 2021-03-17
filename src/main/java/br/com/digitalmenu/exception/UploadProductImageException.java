package br.com.digitalmenu.exception;

public class UploadProductImageException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public UploadProductImageException(String message) {
        super(message);
    }
}
