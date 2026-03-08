package br.com.alura.codechella.adapters.repository.sales;

import br.com.alura.codechella.domain.ticket.Sales;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SalesRepository extends R2dbcRepository<Sales, Long> {
}
