package br.com.alura.codechella.adapters.repository.r2dbc.event;

import br.com.alura.codechella.adapters.rest.event.EventDTO;
import br.com.alura.codechella.domain.event.Event;
import org.springframework.stereotype.Component;

@Component
public class EventRepositoryMapper {
    public Event toDomain(final EventEntity entity) {
        return new Event(entity.getId(), entity.getName(), entity.getDate(), entity.getDescription(),
                entity.getType());
    }
}
