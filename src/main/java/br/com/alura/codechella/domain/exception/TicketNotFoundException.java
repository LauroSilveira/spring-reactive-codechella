package br.com.alura.codechella.domain.exception;

public final class TicketNotFoundException extends DomainException {
    public TicketNotFoundException(final Long id) {
        super("Ticket not found with id: " + id, "TICKET_NOT_FOUND");
    }
}
