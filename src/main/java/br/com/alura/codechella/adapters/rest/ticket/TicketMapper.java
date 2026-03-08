package br.com.alura.codechella.adapters.rest.ticket;

import br.com.alura.codechella.domain.ticket.Order;
import br.com.alura.codechella.domain.ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public Ticket toEntity(final TicketDTO ticketDTO) {
        return new Ticket(ticketDTO.id(), ticketDTO.eventId(), ticketDTO.type(), ticketDTO.amount(),
                ticketDTO.total());
    }

    public Order toEntity(final OrderTicketDTO purchaseTicketDTO) {
        return new Order(purchaseTicketDTO.ticketId(), purchaseTicketDTO.total());
    }
}
