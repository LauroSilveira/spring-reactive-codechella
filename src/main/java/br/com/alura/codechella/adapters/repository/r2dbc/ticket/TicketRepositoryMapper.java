package br.com.alura.codechella.adapters.repository.r2dbc.ticket;

import br.com.alura.codechella.domain.ticket.Sales;
import br.com.alura.codechella.domain.ticket.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketRepositoryMapper {

    public Ticket toDomain(final TicketEntity ticketEntity) {
        return new Ticket(ticketEntity.getId(), ticketEntity.getEventId(),
                ticketEntity.getType(), ticketEntity.getAmount(), ticketEntity.getTotal());
    }

    public Sales toDomain(final SalesEntity salesEntity) {
        return new Sales(salesEntity.getId(), salesEntity.getTicketId(), salesEntity.getTotal());
    }
}
