CREATE TABLE maintenances (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    maintenance_type_id BIGINT NOT NULL,
    description TEXT,
    maintenance_date DATE NOT NULL,
    odometer INTEGER,
    cost INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_maintenances_car
        FOREIGN KEY (car_id)
        REFERENCES cars(id),

    CONSTRAINT fk_maintenances_maintenance_type
        FOREIGN KEY (maintenance_type_id)
        REFERENCES maintenance_types(id),

    CONSTRAINT chk_maintenances_odometer
        CHECK (odometer IS NULL OR odometer >= 0),

    CONSTRAINT chk_maintenances_cost
        CHECK (cost >= 0)
);

CREATE INDEX idx_maintenances_car_id
    ON maintenances(car_id);

CREATE INDEX idx_maintenances_maintenance_type_id
    ON maintenances(maintenance_type_id);

CREATE INDEX idx_maintenances_maintenance_date
    ON maintenances(maintenance_date);