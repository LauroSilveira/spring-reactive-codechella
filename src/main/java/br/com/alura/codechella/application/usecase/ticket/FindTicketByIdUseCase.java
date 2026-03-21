package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindTicketByIdUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    public Mono<Ticket> findById(final Long id) {
        return ticketRepositoryPort.findById(id);
    }
}
