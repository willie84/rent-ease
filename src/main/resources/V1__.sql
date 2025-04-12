CREATE TABLE leases
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    unit_id    BIGINT NULL,
    tenant_id  BIGINT NULL,
    start_date date NULL,
    end_date   date NULL,
    rent_amount DOUBLE NOT NULL,
    CONSTRAINT pk_leases PRIMARY KEY (id)
);

CREATE TABLE maintenance_requests
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    unit_id       BIGINT NULL,
    `description` VARCHAR(255) NULL,
    request_date  date NULL,
    status        VARCHAR(255) NULL,
    CONSTRAINT pk_maintenance_requests PRIMARY KEY (id)
);

CREATE TABLE payments
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    lease_id     BIGINT NULL,
    amount DOUBLE NOT NULL,
    payment_date date NULL,
    CONSTRAINT pk_payments PRIMARY KEY (id)
);

CREATE TABLE properties
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255) NULL,
    address VARCHAR(255) NULL,
    CONSTRAINT pk_properties PRIMARY KEY (id)
);

CREATE TABLE tenants
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    name         VARCHAR(255) NULL,
    contact_info VARCHAR(255) NULL,
    CONSTRAINT pk_tenants PRIMARY KEY (id)
);

CREATE TABLE units
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    property_id BIGINT NULL,
    unit_number VARCHAR(255) NULL,
    type        VARCHAR(255) NULL,
    rent_amount DOUBLE NOT NULL,
    CONSTRAINT pk_units PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    username VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    `role`   VARCHAR(255) NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);