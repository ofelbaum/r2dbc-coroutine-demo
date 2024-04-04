CREATE TABLE IF NOT EXISTS addresses
(
    id      SERIAL PRIMARY KEY,
    country VARCHAR NOT NULL,
    city    VARCHAR NOT NULL,
    street  VARCHAR NOT NULL
);

