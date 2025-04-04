-- Inserir restaurante "Cantina Crasto"
INSERT INTO restaurant (name)
SELECT 'Cantina Crasto'
WHERE NOT EXISTS (SELECT 1 FROM restaurant WHERE name = 'Cantina Crasto');

-- Inserir restaurante "Cantina Santiago"
INSERT INTO restaurant (name)
SELECT 'Cantina Santiago'
WHERE NOT EXISTS (SELECT 1 FROM restaurant WHERE name = 'Cantina Santiago');

-- Inserir refeições com as opções para cada dia
-- Quarta-feira 02/04/2025

INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Grelhada mista com arroz branco e brócolos', '2025-04-02', (SELECT id FROM restaurant WHERE name = 'Cantina Crasto')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Grelhada mista com arroz branco e brócolos' AND date = '2025-04-02');

-- Quinta-feira 03/04/2025

INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Bacalhau com natas', '2025-04-03', (SELECT id FROM restaurant WHERE name = 'Cantina Crasto')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Bacalhau com natas' AND date = '2025-04-03');


-- Sexta-feira 04/04/2025

INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Peru à salsicheiro e esparguete', '2025-04-04', (SELECT id FROM restaurant WHERE name = 'Cantina Crasto')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Peru à salsicheiro e esparguete' AND date = '2025-04-04');


INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Solha frita e arroz de tomate caldoso', '2025-04-02', (SELECT id FROM restaurant WHERE name = 'Cantina Santiago')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Solha frita e arroz de tomate caldoso' AND date = '2025-04-02');
INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Frango assado com mostarda e massa esparguete com molho de tomate', '2025-04-03', (SELECT id FROM restaurant WHERE name = 'Cantina Santiago')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Frango assado com mostarda e massa esparguete com molho de tomate' AND date = '2025-04-03');
INSERT INTO meal (name, date, restaurant_id)
SELECT 'Prato Normal - Meia desfeita de bacalhau e batata cozida e grão', '2025-04-04', (SELECT id FROM restaurant WHERE name = 'Cantina Santiago')
WHERE NOT EXISTS (SELECT 1 FROM meal WHERE name = 'Prato Normal - Meia desfeita de bacalhau e batata cozida e grão' AND date = '2025-04-04');
