package br.com.alura.codechella.adapters.repository.r2dbc.event;

import br.com.alura.codechella.domain.event.EventType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EventRepository extends R2dbcRepository<EventEntity, Long> {
    Flux<EventEntity> findByType(EventType eventType);
}
