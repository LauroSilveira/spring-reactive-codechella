package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindAllEventsUseCase {
    private final EventRepositoryPort eventRepositoryPort;

    public Flux<Event> findAll() {
        return eventRepositoryPort.findAll();

    }
}
