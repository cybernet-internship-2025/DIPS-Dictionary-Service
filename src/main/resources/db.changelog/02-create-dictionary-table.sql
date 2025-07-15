CREATE TABLE IF NOT EXISTS dictionaries (
                                            id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
                                            value VARCHAR(255) NOT NULL,
                                            description TEXT,
                                            is_active BOOLEAN DEFAULT TRUE,
                                            deleted_at TIMESTAMP,
                                            created_at TIMESTAMP,
                                            updated_at TIMESTAMP,
                                            category_id UUID NOT NULL,
                                            CONSTRAINT fk_dictionary_category FOREIGN KEY (category_id) REFERENCES categories(id)
);
