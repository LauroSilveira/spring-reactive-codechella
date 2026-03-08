package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.repository.ticket.TicketRepository;
import br.com.alura.codechella.domain.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateTicketUseCase {

    private final TicketRepository ticketRepository;

    public Mono<Ticket> update(final Long id, final Ticket entity) {
        return ticketRepository.findById(id)
                .map(ticket -> ticket.updateFields(entity));
    }
}
