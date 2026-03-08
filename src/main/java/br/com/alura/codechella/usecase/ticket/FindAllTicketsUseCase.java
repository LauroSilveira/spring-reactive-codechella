package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.repository.ticket.TicketRepository;
import br.com.alura.codechella.domain.ticket.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindAllTicketsUseCase {
    private final TicketRepository ticketRepository;

    public Flux<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
