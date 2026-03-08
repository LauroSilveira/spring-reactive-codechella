create table Sales
(
    ID        BIGSERIAL not null,
    TICKET_ID BIGINT    not null,
    TOTAL     INT       not null,
    primary key (ID)
);
