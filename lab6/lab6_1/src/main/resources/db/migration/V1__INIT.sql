CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO customer (name, email) VALUES
('Alicia', 'keys@example.com'),
('cristiano', 'ronaldo@example.com'),
('jota', 'jota@example.com');
