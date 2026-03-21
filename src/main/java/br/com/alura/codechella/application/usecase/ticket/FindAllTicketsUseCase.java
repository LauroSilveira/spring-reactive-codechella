package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindAllTicketsUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    public Flux<Ticket> findAll() {
        return ticketRepositoryPort.findAll();
    }
}
