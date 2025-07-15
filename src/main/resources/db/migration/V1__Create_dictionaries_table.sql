CREATE TABLE dictionaries (
                              id UUID PRIMARY KEY,
                              value VARCHAR(255) NOT NULL UNIQUE,
                              description TEXT,
                              is_active BOOLEAN NOT NULL DEFAULT TRUE,
                              created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                              updated_at TIMESTAMP WITH TIME ZONE,
                              deleted_at TIMESTAMP WITH TIME ZONE
);