package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.database.postgres.ticket.TicketRepository;
import br.com.alura.codechella.domain.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketRepository ticketRepository;

    public Mono<Ticket> create(final Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
