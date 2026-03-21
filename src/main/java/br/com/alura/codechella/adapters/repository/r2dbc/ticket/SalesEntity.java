package br.com.alura.codechella.adapters.repository.r2dbc.ticket;

import br.com.alura.codechella.domain.ticket.Sales;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalesEntity {

    @Id
    private Long id;
    private Long ticketId;
    private int total;

    public static SalesEntity toEntity(Sales sales) {
        return new SalesEntity(sales.id(),  sales.ticketId(), sales.total());
    }
}
