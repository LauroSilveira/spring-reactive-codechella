package br.com.alura.codechella.domain.event;

import java.time.LocalDate;
import java.util.Objects;


public record Event(Long id, String name, LocalDate date, String description, EventType type) {
    public Event updateFields(Event eventToUpdate) {
        return new Event(
                eventToUpdate.id(),
                Objects.requireNonNullElse(eventToUpdate.name(), this.name),
                Objects.requireNonNullElse(eventToUpdate.date(), this.date),
                Objects.requireNonNullElse(eventToUpdate.description(), this.description),
                Objects.requireNonNullElse(eventToUpdate.type(), this.type)
        );
    }
}
