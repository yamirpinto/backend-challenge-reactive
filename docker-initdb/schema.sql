CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS history (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  timestamp TIMESTAMPTZ NOT NULL,
  endpoint VARCHAR(255) NOT NULL,
  request_body TEXT,
  response_body TEXT,
  error_message TEXT
);