package br.com.alura.codechella.domain.exception;

public final class EventNotFoundException extends DomainException {

    public EventNotFoundException(final Long id) {
        super("Event not found with id: " + id, "EVENT_NOT_FOUND");
    }

}
