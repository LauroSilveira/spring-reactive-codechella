package br.com.alura.codechella.application.usecase.ticket;

import br.com.alura.codechella.domain.ticket.Order;
import br.com.alura.codechella.domain.ticket.Sales;
import br.com.alura.codechella.domain.ticket.Ticket;
import br.com.alura.codechella.domain.ticket.TicketRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PurchaseTicketUseCase {
    private final TicketRepositoryPort ticketRepositoryPort;

    @Transactional
    public Mono<Ticket> purchase(final Order order) {
        return ticketRepositoryPort.findById(order.ticketId())
                .flatMap(ticket -> {
                    final var sales = new Sales(ticket.id(), ticket.total());
                    return ticketRepositoryPort.save(sales).then(Mono.defer(() -> {
                        final var ticketWithPurchase = ticket.createTicketWithPurchase(order);
                        return ticketRepositoryPort.save(ticketWithPurchase);
                    }));
                });

    }
}
