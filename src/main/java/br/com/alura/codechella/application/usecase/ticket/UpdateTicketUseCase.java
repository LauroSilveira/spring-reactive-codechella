package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.exception.TicketNotFoundException;
import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateTicketUseCase {

    private final TicketRepositoryPort ticketRepositoryPort;

    public Mono<Ticket> update(final Long id, final Ticket entity) {
        return ticketRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new TicketNotFoundException(id)))
                .map(ticket -> ticket.updateFields(entity))
                .flatMap(ticketRepositoryPort::save);
    }
}
