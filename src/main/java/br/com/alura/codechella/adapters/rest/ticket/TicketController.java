package br.com.alura.codechella.adapters.rest.ticket;

import br.com.alura.codechella.application.usecase.ticket.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final FindAllTicketsUseCase findAllTicketsUseCase;
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final CreateTicketUseCase createTicketUseCase;
    private final UpdateTicketUseCase updateTicketUseCase;
    private final DeleteTicketUseCase deleteTicketUseCase;
    private final PurchaseTicketUseCase purchaseTicketUsecase;
    private final GetTicketsAvailableUseCase getTicketsAvailableUseCase;
    private final Sinks.Many<TicketDTO> sinksTicketDTO = Sinks.many().multicast().onBackpressureBuffer();
    private final TicketMapper ticketMapper;

    @GetMapping
    public Flux<TicketDTO> findAll() {
        return findAllTicketsUseCase.findAll().map(TicketDTO::toDTO);
    }

    @GetMapping("/{id}")
    public Mono<TicketDTO> findById(@PathVariable final Long id) {
        return findTicketByIdUseCase.findById(id).map(TicketDTO::toDTO);
    }

    @PostMapping
    public Mono<TicketDTO> create(@RequestBody final TicketDTO ticketDTO) {
        return createTicketUseCase.create(ticketMapper.toEntity(ticketDTO))
                .map(TicketDTO::toDTO);
    }

    @PutMapping("/{id}")
    public Mono<TicketDTO> update(@PathVariable final Long id, @RequestBody final TicketDTO ticketDTO) {
        return updateTicketUseCase.update(id, this.ticketMapper.toEntity(ticketDTO))
                .map(TicketDTO::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable final Long id) {
        return deleteTicketUseCase.delete(id);
    }

    @PostMapping("/purchase")
    public Mono<TicketDTO> purchaseTicket(@RequestBody final OrderTicketDTO orderTicketDTO) {
        return purchaseTicketUsecase.purchase(this.ticketMapper.toEntity(orderTicketDTO))
                .map(TicketDTO::toDTO)
                .doOnSuccess(sinksTicketDTO::tryEmitNext);
    }

    @GetMapping(value = "/{id}/available", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TicketDTO> getTicketsAvailable(@PathVariable final Long id) {
        final var ticketDTO = getTicketsAvailableUseCase.getTicketsAvailable(id)
                .map(TicketDTO::toDTO);
        return Flux.merge(ticketDTO, sinksTicketDTO.asFlux())
                .delayElements(Duration.ofSeconds(3));
    }
}
