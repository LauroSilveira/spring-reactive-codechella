package br.com.alura.codechella.domain.ticket;

public record Sales(Long id, Long ticketId, int total) {

    public Sales(Long ticketId, int total) {
        this(null, ticketId, total);
    }
}
