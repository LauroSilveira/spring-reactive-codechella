package br.com.alura.codechella.domain.exception;

public abstract sealed class DomainException extends RuntimeException permits EventNotFoundException,
        TicketNotFoundException {
    private final String errorCode;

    protected DomainException(final String message, final String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
