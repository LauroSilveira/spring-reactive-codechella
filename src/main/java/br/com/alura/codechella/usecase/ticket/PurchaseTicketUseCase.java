package br.com.alura.codechella.usecase.ticket;

import br.com.alura.codechella.adapters.database.postgres.sales.SalesRepository;
import br.com.alura.codechella.adapters.database.postgres.ticket.TicketRepository;
import br.com.alura.codechella.adapters.rest.ticket.TicketDTO;
import br.com.alura.codechella.domain.ticket.Order;
import br.com.alura.codechella.domain.ticket.Sales;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PurchaseTicketUseCase {
    private final TicketRepository ticketRepository;
    private final SalesRepository salesRepository;

    @Transactional
    public Mono<TicketDTO> purchase(final Order order) {
        return ticketRepository.findById(order.ticketId())
                .flatMap(ticket -> {
                    final Sales sales = new Sales(ticket.id(), ticket.total());
                    return salesRepository.save(sales).then(Mono.defer(() -> {
                        ticket.setTotal(ticket.total() - order.total());
                        return ticketRepository.save(ticket);
                    }));
                }).map(TicketDTO::toDTO);

    }
}
