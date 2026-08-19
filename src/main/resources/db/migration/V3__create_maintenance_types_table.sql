CREATE TABLE maintenance_types (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    category SMALLINT NOT NULL,
    recommended_distance_km INTEGER,
    recommended_period_months INTEGER,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_maintenance_types_name
    ON maintenance_types (name);

CREATE INDEX idx_maintenance_types_category
    ON maintenance_types (category);