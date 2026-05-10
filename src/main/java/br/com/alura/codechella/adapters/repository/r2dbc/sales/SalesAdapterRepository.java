package br.com.alura.codechella.adapters.repository.r2dbc.sales;

import br.com.alura.codechella.adapters.repository.r2dbc.ticket.SalesEntity;
import br.com.alura.codechella.domain.sales.SalesRepositoryPort;
import br.com.alura.codechella.domain.ticket.Sales;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
@Slf4j
public class SalesAdapterRepository implements SalesRepositoryPort {
    private final SalesRepository salesRepository;
    private final SalesRepositoryMapper salesRepositoryMapper;

    @Override
    public Mono<Sales> save(final Sales sales) {
        return salesRepository.save(SalesEntity.toEntity(sales))
                .map(salesRepositoryMapper::toDomain);
    }
}
