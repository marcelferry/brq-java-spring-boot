DROP TABLE IF EXISTS rating;

CREATE TABLE IF NOT EXISTS rating (
  id              BIGINT     NOT NULL PRIMARY KEY,
  book_id         BIGINT     NOT NULL,
  stars           INT        NOT NULL
);