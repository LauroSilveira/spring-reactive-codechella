package br.com.alura.codechella.adapters.repository.r2dbc.ticket;

import br.com.alura.codechella.adapters.repository.r2dbc.sales.SalesRepository;
import br.com.alura.codechella.domain.ticket.Sales;
import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class TicketAdapterRepository implements TicketRepositoryPort {
    private final TicketRepository ticketRepository;
    private final SalesRepository salesRepository;
    private final TicketRepositoryMapper ticketRepositoryMapper;

    @Override
    public Mono<Ticket> save(final Ticket ticket) {
        return ticketRepository.save(TicketEntity.toEntity(ticket))
                .map(ticketRepositoryMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(final Long id) {
        return ticketRepository.deleteById(id);
    }

    @Override
    public Flux<Ticket> findAll() {
        return ticketRepository.findAll()
                .map(ticketRepositoryMapper::toDomain);
    }

    @Override
    public Mono<Ticket> findById(final Long id) {
        return ticketRepository.findById(id)
                .map(ticketRepositoryMapper::toDomain);

    }

    @Override
    public Mono<Sales> save(final Sales sales) {
        return salesRepository.save(SalesEntity.toEntity(sales))
                .map(ticketRepositoryMapper::toDomain);
    }
}
