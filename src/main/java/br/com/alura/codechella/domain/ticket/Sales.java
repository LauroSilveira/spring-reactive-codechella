package br.com.alura.codechella.domain.ticket;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "sales")
public class Sales {
    @Id
    private Long id;
    private Long ticketId;
    private int total;

    public Sales() {
    }

    public Sales(Long id, Long ticketId, int total) {
        this.id = id;
        this.ticketId = ticketId;
        this.total = total;
    }

    public Sales(final Long id, final int total) {
        this.id = id;
        this.total = total;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long ticketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public int total() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
