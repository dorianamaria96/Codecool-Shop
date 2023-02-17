INSERT INTO product_categories (name, description, department) VALUES ('Arts and Crafts', 'description', 'department');
INSERT INTO product_categories (name, description, department) VALUES ('Self Development', 'description', 'department');
INSERT INTO product_categories (name, description, department) VALUES ('Nutrition', 'description', 'department');

INSERT INTO supplier (name, description) VALUES ('FattyCow Book Publishing', 'description');
INSERT INTO supplier (name, description) VALUES ('Food for thought Publishing', 'description');
INSERT INTO supplier (name, description) VALUES ('Publicious Book Publishing', 'description');

INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('The Fine Art of Paper Flowers', 'The Fine Art of Paper Flowers is an elevated art and craft guide that features complete step-by-step instructions for over 30 of Tiffanie Turner’s widely admired, unique, lifelike paper flowers and their foliage, from bougainvillea to English roses to zinnias. ', 12.9, 'EUR', 'flowers' , 1, 3);
INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('Knitting with Dog Fur', 'Better a Sweater from a Dog You Know and Love Than from a Sheep You will Never Meet' , 14.9, 'EUR', 'dog-fur', 1, 2);
INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('The Classic Art of Origami Kit', 'Folding paper,what a fascinating art! But forget about clunky origami books with complicated instructions.' , 27.9, 'EUR', 'origami', 1, 2);

INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('Ikigai','It is the Japanese word for -a reason to live- or -a reason to jump out of bed in the morning. It is the place where your needs, desires, ambitions, and satisfaction meet. A place of balance. Small wonder that finding your ikigai is closely linked to living longer.' , 19.9, 'EUR', 'ikigai', 2, 1);

INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('The Science of Nutrition', 'Debunk the Diet Myths and Learn How to Eat Well for Health and Happiness.' , 12.9, 'EUR', 'nutrition', 3, 1);
INSERT INTO products (name, description, price, currency, image, product_category_id, supplier_id) VALUES ('Brain Food: The Surprising Science of Eating for Cognitive Power', 'How to eat for maximum brain power and health from an expert in both neuroscience and nutrition.' , 14.9, 'EUR', 'brain-food', 3, 1);

