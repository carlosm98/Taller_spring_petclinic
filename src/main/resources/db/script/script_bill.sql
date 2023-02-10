CREATE TABLE IF NOT EXISTS bill (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  money double(7,2) unsigned NOT NULL,
  payment_date date NOT NULL,
  visit_id INT unsigned NOT NULL,
  UNIQUE KEY visit_id (visit_id),
  CONSTRAINT bill_ibfk_2 FOREIGN KEY (visit_id) REFERENCES visits (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT IGNORE INTO bill (id, money, payment_date, visit_id) VALUES (1, 150.50, '2023-02-09', 1);
INSERT IGNORE INTO bill (id, money, payment_date, visit_id) VALUES (2, 50.00, '2023-01-09', 3);
INSERT IGNORE INTO bill (id, money, payment_date, visit_id) VALUES (3, 50.00, '2023-01-02', 2);