package br.com.alura.codechella.usecase.event;

import br.com.alura.codechella.adapters.repository.event.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteEventUseCase {

    private final EventRepository eventRepository;

    public Mono<Void> delete(final Long id) {
        return eventRepository.deleteById(id);
    }
}
