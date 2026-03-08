package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.adapters.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindAllEventsUseCase {
    private final EventRepository eventRepository;

    public Flux<Event> findAll() {
        return this.eventRepository.findAll();
    }
}
