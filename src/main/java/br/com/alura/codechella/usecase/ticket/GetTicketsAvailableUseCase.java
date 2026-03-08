package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.repository.ticket.TicketRepository;
import br.com.alura.codechella.adapters.rest.ticket.TicketDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetTicketsAvailableUseCase {

    private final TicketRepository ticketRepository;

    public Flux<TicketDTO> getTicketsAvailable(final Long id) {
        return ticketRepository.findById(id)
                .map(TicketDTO::toDTO)
                .flux();
    }
}
