package br.com.alura.codechella.adapters.database.postgres.ticket;

import br.com.alura.codechella.domain.ticket.Ticket;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TicketRepository extends R2dbcRepository<Ticket, Long> {
}
