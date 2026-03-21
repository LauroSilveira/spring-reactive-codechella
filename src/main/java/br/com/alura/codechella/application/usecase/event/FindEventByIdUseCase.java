package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class FindEventByIdUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public Mono<Event> findById(final Long id) {
        return eventRepositoryPort.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Event not found")));
    }
}
