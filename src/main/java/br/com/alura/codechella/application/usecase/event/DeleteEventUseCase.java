package br.com.alura.codechella.application.usecase.event;

import br.com.alura.codechella.domain.event.EventRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteEventUseCase {

    private final EventRepositoryPort eventRepositoryPort;

    public Mono<Void> delete(final Long id) {
        return eventRepositoryPort.deleteById(id);
    }
}
