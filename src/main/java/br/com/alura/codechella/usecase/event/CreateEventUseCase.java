package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.database.postgres.event.EventRepository;
import br.com.alura.codechella.domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateEventUseCase {

    private final EventRepository eventRepository;
    public Mono<Event> create(final Event entity) {
        return this.eventRepository.save(entity);
    }
}
