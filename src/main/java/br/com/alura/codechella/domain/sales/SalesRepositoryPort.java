package br.com.alura.codechella.domain.sales;

import br.com.alura.codechella.domain.ticket.Sales;
import reactor.core.publisher.Mono;

public interface SalesRepositoryPort {
    Mono<Sales> save(Sales sales);
}
