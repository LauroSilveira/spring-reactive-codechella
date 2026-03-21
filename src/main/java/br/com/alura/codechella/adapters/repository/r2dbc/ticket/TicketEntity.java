package br.com.alura.codechella.adapters.repository.r2dbc.ticket;

import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tickets")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    private Long id;
    private Long eventId;
    private Type type;
    private Double amount;
    private int total;

    public static TicketEntity toEntity(final Ticket ticket) {
        return new TicketEntity(null, ticket.eventId(), ticket.type(), ticket.amount(), ticket.total());
    }
}
