CREATE TABLE customs (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    custom_date DATE NOT NULL,
    cost INTEGER DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_customs_car
        FOREIGN KEY (car_id)
        REFERENCES cars(id)
);

CREATE INDEX idx_customs_car_id
    ON customs(car_id);

CREATE INDEX idx_customs_custom_date
    ON customs(custom_date);