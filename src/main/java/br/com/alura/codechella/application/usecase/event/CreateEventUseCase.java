package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;

    public Mono<Event> create(final Event entity) {
        return eventRepositoryPort.save(entity);
    }
}
