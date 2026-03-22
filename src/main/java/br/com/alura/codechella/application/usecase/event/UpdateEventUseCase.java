package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import br.com.alura.codechella.domain.exception.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateEventUseCase {
    private final EventRepositoryPort eventRepositoryPort;

    public Mono<Event> update(final Event entity, final Long id) {
        return eventRepositoryPort.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new EventNotFoundException(id))))
                .map(event -> event.updateFields(entity))
                .flatMap(this.eventRepositoryPort::save);

    }
}
