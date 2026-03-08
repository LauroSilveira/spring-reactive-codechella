create table Tickets
(
    ID       BIGSERIAL      not null,
    EVENT_ID BIGINT         not null,
    TYPE     VARCHAR(30)    not null,
    AMOUNT    DECIMAL(10, 2) not null,
    TOTAL    INT            not null,

    primary key (id)
);
