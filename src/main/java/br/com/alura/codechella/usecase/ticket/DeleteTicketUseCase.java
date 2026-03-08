package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.database.postgres.ticket.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteTicketUseCase {
    private final TicketRepository ticketRepository;

    public Mono<Void> delete(final Long id) {
        return ticketRepository.deleteById(id);
    }

}
