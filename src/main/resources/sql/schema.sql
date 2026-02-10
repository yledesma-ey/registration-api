-- Tabla users
CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT RANDOM_UUID(),
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created TIMESTAMP,
                       modified TIMESTAMP,
                       last_login TIMESTAMP,
                       token VARCHAR(255),
                       is_active BOOLEAN
);

-- Tabla phones
CREATE TABLE phones (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        number VARCHAR(50) NOT NULL,
                        citycode VARCHAR(10),
                        countrycode VARCHAR(10),
                        user_id UUID,
                        CONSTRAINT fk_user
                            FOREIGN KEY(user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE
);