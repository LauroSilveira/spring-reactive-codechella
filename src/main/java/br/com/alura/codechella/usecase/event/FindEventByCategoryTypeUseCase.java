package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.database.postgres.event.EventRepository;
import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindEventByCategoryTypeUseCase {

    private final EventRepository eventRepository;

    public Flux<Event> findByCategoryType(String type) {
        EventType eventType = EventType.valueOf(type.toUpperCase());
        return eventRepository.findByType(eventType);
    }
}
