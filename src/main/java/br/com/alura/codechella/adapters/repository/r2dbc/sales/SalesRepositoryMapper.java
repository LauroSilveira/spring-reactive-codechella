package br.com.alura.codechella.adapters.repository.r2dbc.sales;

import br.com.alura.codechella.adapters.repository.r2dbc.ticket.SalesEntity;
import br.com.alura.codechella.domain.ticket.Sales;
import org.springframework.stereotype.Component;

@Component
public class SalesRepositoryMapper {
    public Sales toDomain(SalesEntity salesEntity) {
        return new Sales(salesEntity.getId(), salesEntity.getTicketId(), salesEntity.getTotal());
    }
}
