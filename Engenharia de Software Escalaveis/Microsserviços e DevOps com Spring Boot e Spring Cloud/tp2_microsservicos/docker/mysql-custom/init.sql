
CREATE TABLE IF NOT EXISTS products (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(120) NOT NULL,
  price       DECIMAL(10,2) NOT NULL DEFAULT 0,
  stock       INT NOT NULL DEFAULT 0,
  created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO products (name, price, stock)
VALUES
  ('Café', 19.90, 10),
  ('Chá Verde', 14.50, 20),
  ('Leite', 7.29, 50)
ON DUPLICATE KEY UPDATE name = VALUES(name);

CREATE INDEX IF NOT EXISTS idx_products_name ON products(name);

