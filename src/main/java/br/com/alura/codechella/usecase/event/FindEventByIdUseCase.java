package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.database.postgres.event.EventRepository;
import br.com.alura.codechella.domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class FindEventByIdUseCase {

    private final EventRepository eventRepository;

    public Mono<Event> findById(final Long id) {
        return eventRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Event not found")));
    }
}
