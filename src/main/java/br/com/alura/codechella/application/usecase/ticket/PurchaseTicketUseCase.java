package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.exception.TicketNotFoundException;
import br.com.alura.codechella.domain.sales.SalesRepositoryPort;
import br.com.alura.codechella.domain.ticket.Order;
import br.com.alura.codechella.domain.ticket.Sales;
import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseTicketUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;
    private final SalesRepositoryPort salesRepositoryPort;

    @Transactional
    public Mono<Ticket> purchase(final Order order) {
        log.info("[PurchaseTicketUseCase] purchase order: {}", order);
        return ticketRepositoryPort.findById(order.ticketId())
                .switchIfEmpty(Mono.error(new TicketNotFoundException(order.ticketId())))
                .flatMap(ticket -> {
                    final var sales = new Sales(ticket.id(), ticket.total());
                    return salesRepositoryPort.save(sales).then(Mono.defer(() -> {
                        final var ticketWithPurchase = ticket.createTicketWithPurchase(order);
                        return ticketRepositoryPort.save(ticketWithPurchase);
                    }));
                });

    }
}
