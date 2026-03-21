package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteTicketUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    public Mono<Void> delete(final Long id) {
        return ticketRepositoryPort.deleteById(id);
    }

}
