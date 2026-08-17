CREATE TABLE cars (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    maker VARCHAR(50) NOT NULL,
    car_model VARCHAR(100) NOT NULL,
    model_year INTEGER,
    description TEXT,
    odometer INTEGER,
    status SMALLINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_cars_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE INDEX idx_cars_user_id
    ON cars(user_id);