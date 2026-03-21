package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.adapters.repository.r2dbc.ticket.TicketEntity;
import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketRepositoryPort ticketRepositoryPort;

    public Mono<Ticket> create(final Ticket ticket) {
        return ticketRepositoryPort.save(ticket);
    }
}
