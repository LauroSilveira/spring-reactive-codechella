package br.com.alura.codechella.adapters.repository.r2dbc.ticket;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TicketRepository extends R2dbcRepository<TicketEntity, Long> {
}
