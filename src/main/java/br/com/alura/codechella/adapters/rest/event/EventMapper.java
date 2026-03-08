package br.com.alura.codechella.adapters.rest.event;

import br.com.alura.codechella.domain.event.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event toEntity(final EventDTO dto) {
        return new Event(dto.id(), dto.name(), dto.date(), dto.description(), dto.type());
    }
}
