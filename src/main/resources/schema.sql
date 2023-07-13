DROP TABLE IF EXISTS MEASUREMENTS;

CREATE TABLE MEASUREMENTS
(
    ID      bigint      generated by default as identity,
    "TIME"      DOUBLE PRECISION NOT NULL,
    FREQUENCY   DOUBLE PRECISION NOT NULL,
    NP          DOUBLE PRECISION NOT NULL,
    GP          DOUBLE PRECISION NOT NULL,
    NQ          DOUBLE PRECISION NOT NULL,
    GQ          DOUBLE PRECISION NOT NULL,
    PRIMARY KEY (ID)
);