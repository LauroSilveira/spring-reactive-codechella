package br.com.alura.codechella.domain.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

@Table("events")
public class Event {

    @Id
    private Long id;
    private String name;
    private LocalDate date;
    private String description;
    private EventType type;

    public Event() {
    }

    public Event(Long id, String name, LocalDate date, String description, EventType type) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Event updateFields(Event eventToUpdate) {
        this.name = Objects.requireNonNullElseGet(eventToUpdate.getName(), () -> this.name);
        this.date = Objects.requireNonNullElseGet(eventToUpdate.getDate(), () -> this.date);
        this.description = Objects.requireNonNullElseGet(eventToUpdate.getDescription(), () -> this.description);
        this.type = Objects.requireNonNullElseGet(eventToUpdate.getType(), () -> this.type);
        return this;
    }
}
