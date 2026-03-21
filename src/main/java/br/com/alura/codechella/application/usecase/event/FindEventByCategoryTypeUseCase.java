package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import br.com.alura.codechella.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindEventByCategoryTypeUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public Flux<Event> findByCategoryType(String type) {
        EventType eventType = EventType.valueOf(type.toUpperCase());
        return eventRepositoryPort.findByType(eventType);
    }
}
