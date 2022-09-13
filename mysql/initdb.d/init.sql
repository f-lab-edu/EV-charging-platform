use ev_database;

-- sample table
CREATE TABLE users (
                       id INT PRIMARY KEY  AUTO_INCREMENT,
                       name VARCHAR(20) NOT NULL,
                       email VARCHAR(50) NOT NULL
);

-- sample data
INSERT INTO ev_database.users (id, name, email) VALUES (1, 'sanghoon', 'sanghoon@gmail.com');
