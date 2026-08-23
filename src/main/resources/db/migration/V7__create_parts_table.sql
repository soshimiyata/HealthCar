CREATE TABLE parts (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    category SMALLINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    maker VARCHAR(100),
    installed_at DATE,
    price INTEGER DEFAULT 0,
    description TEXT,
    image_url TEXT,
    status SMALLINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_parts_car
        FOREIGN KEY (car_id)
        REFERENCES cars(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_parts_car_id
    ON parts(car_id);

CREATE INDEX idx_parts_category
    ON parts(category);