package br.com.luis.financial.infra.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException(String message) {
        super(message);
    }
}
