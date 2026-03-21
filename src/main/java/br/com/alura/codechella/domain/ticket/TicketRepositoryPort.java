package br.com.alura.codechella.domain.ticket;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TicketRepositoryPort {
    Mono<Ticket> save(Ticket ticket);

    Mono<Void> deleteById(Long id);

    Flux<Ticket> findAll();

    Mono<Ticket> findById(Long id);

    Mono<Sales> save(Sales sales);
}
