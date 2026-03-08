package br.com.alura.codechella.adapters.database.postgres.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends R2dbcRepository<Event, Long> {
    Flux<Event> findByType(EventType eventType);
}
