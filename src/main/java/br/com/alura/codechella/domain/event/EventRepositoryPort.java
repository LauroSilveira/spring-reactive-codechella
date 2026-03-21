package br.com.alura.codechella.domain.event;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventRepositoryPort {
    Mono<Event> save(Event entity);

    Mono<Void> deleteById(Long id);

    Flux<Event> findAll();

    Flux<Event> findByType(EventType eventType);

    Mono<Event> findById(Long id);
}
