CREATE TABLE IF NOT EXISTS ev_cars
(
    id                    INT PRIMARY KEY AUTO_INCREMENT,
    ev_car_type              VARCHAR(50),
    charge_connector_type VARCHAR(50),
    created_at            DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP,
    is_deleted            BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS cards
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    card_number VARCHAR(30),
    cvc         VARCHAR(10),
    yy          VARCHAR(2),
    mm          VARCHAR(2),
    created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,
    is_deleted  BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS users
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(20)  NOT NULL,
    password   VARCHAR(255) NOT NULL,
    phone      VARCHAR(20)  NOT NULL,
    email      VARCHAR(50)  NOT NULL,
    ev_car_id     int,
    foreign key (ev_car_id) references ev_cars (id),
    card_id    int,
    foreign key (card_id) references cards (id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE
);

