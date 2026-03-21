package br.com.alura.codechella.domain.ticket;

import java.util.Objects;


public record Ticket(Long id, Long eventId, Type type, Double amount, int total) {

    public Ticket createTicketWithPurchase(final Order order) {
        return new Ticket(id, eventId, type, amount, total - order.total());
    }

    public Ticket updateFields(final Ticket newTicket) {
        return new Ticket(this.id(),
                newTicket.eventId(),
                Objects.requireNonNullElseGet(newTicket.type(), this::type),
                Objects.requireNonNullElseGet(newTicket.amount(), this::amount),
                newTicket.total() != 0 ? newTicket.total() : total
        );
    }
}
