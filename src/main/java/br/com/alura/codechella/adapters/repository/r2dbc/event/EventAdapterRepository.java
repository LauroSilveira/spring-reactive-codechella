package br.com.alura.codechella.adapters.repository.r2dbc.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import br.com.alura.codechella.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class EventAdapterRepository implements EventRepositoryPort {
    private final EventRepository eventRepository;
    private final EventRepositoryMapper eventMapper;

    @Override
    public Mono<Event> save(final Event entity) {
        return eventRepository.save(EventEntity.toEntity(entity))
                .map(eventMapper::toDomain);
    }

    @Override
    public Mono<Void> deleteById(final Long id) {
        return eventRepository.deleteById(id);
    }

    @Override
    public Flux<Event> findAll() {
        return this.eventRepository.findAll()
                .map(eventMapper::toDomain);
    }

    @Override
    public Flux<Event> findByType(final EventType eventType) {
        return this.eventRepository.findByType(eventType)
                .map(eventMapper::toDomain);
    }

    @Override
    public Mono<Event> findById(Long id) {
        return this.eventRepository.findById(id)
                .map(eventMapper::toDomain);
    }
}
