CREATE TABLE car_issues (
    id BIGSERIAL PRIMARY KEY,
    car_id BIGINT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status SMALLINT NOT NULL DEFAULT 0,
    priority SMALLINT NOT NULL DEFAULT 1,
    occurred_at DATE,
    resolved_at DATE,
    image_url TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_car_issues_car
        FOREIGN KEY (car_id)
        REFERENCES cars(id)
        ON DELETE CASCADE
);

CREATE INDEX idx_car_issues_car_id
    ON car_issues(car_id);

CREATE INDEX idx_car_issues_status
    ON car_issues(status);