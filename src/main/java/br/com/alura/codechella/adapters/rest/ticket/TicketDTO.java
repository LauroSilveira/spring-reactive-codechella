package br.com.alura.codechella.adapters.rest.ticket;

import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.Type;

public record TicketDTO(Long id, Long eventId, Type type, Double amount, int total) {
    public static TicketDTO toDTO(Ticket ticket) {

        return new TicketDTO(ticket.id(),
                ticket.eventId(),
                ticket.type(),
                ticket.amount(),
                ticket.total());
    }
}
