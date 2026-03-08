package br.com.alura.codechella.adapters.rest.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventType;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record EventDTO(Long id,
                       String name,
                       LocalDate date,
                       String description,
                       EventType type) {

    public static EventDTO toDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getDescription(),
                event.getType());
    }
}
