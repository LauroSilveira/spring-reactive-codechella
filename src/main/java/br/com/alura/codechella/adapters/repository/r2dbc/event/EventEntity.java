package br.com.alura.codechella.adapters.repository.r2dbc.event;

import br.com.alura.codechella.domain.event.Event;
import br.com.alura.codechella.domain.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {
    @Id
    private Long id;
    private String name;
    private LocalDate date;
    private String description;
    private EventType type;

    public static EventEntity toEntity(final Event entity) {
        return new EventEntity(entity.id(), entity.name(), entity.date(), entity.description(), entity.type());
    }
}
