package br.com.alura.codechella.domain.ticket;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Table(name = "tickets")
public class Ticket {
    @Id
    private Long id;
    private Long eventId;
    private Type type;
    private Double amount;
    private int total;

    public Ticket() {
    }

    public Ticket(Long id, Long eventId, Type type, Double amount, int total) {
        this.id = id;
        this.eventId = eventId;
        this.type = type;
        this.amount = amount;
        this.total = total;
    }

    public Long id() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long eventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Type ticket() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double amount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public int total() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Ticket updateFields(final Ticket newTicket) {
        this.type = Objects.requireNonNullElseGet(newTicket.ticket(), this::ticket);
        this.amount = Objects.requireNonNullElseGet(newTicket.amount(), this::amount);
        this.total = newTicket.total();
        return this;
    }
}
