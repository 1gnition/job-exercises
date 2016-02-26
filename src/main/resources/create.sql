CREATE TABLE event_stats (
    id integer not null,
    count bigint,
    eventType varchar(255),
    primary key (id)
);

CREATE TABLE word_stats (
    id integer not null,
    count bigint,
    word varchar(255),
    primary key (id)
);
