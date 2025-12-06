-- Borrar base de datos para reinicio (EJECUTAR MANUALMENTE).
-- drop schema api_db;
-- create schema api_db;

-- Tablas --
-- Nombres en minúscula por default de MySQL.

-- Categoria
CREATE TABLE category(
    category_id INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la categoria',
    category VARCHAR(100) NOT NULL COMMENT 'Nombre de la categoria',
    tag VARCHAR(100) NOT NULL COMMENT 'Etiqueta de la categoria',
    status TINYINT NOT NULL COMMENT 'Estado de la categoria',
    PRIMARY KEY (category_id) COMMENT 'Llave primaria de la tabla'
) COMMENT = 'Tabla que contiene las categorias registradas';

-- Constraints --
CREATE UNIQUE INDEX ux_category ON category(category) COMMENT 'Index único';
CREATE UNIQUE INDEX ux_tag ON category(tag) COMMENT 'Index único';

-- Producto
CREATE TABLE product(
    product_id INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único del producto',
    gtin CHAR(13) NOT NULL COMMENT 'Código GTIN del producto',
    product VARCHAR(100) NOT NULL COMMENT 'Nombre del producto',
    description TEXT COMMENT 'Descripción del producto',
    price FLOAT NOT NULL COMMENT 'Precio del producto',
    stock INT NOT NULL COMMENT 'Cantidad en stock',
    category_id INT NOT NULL COMMENT 'Identificador de la categoría',
    status TINYINT NOT NULL COMMENT 'Estado del producto',
    PRIMARY KEY (product_id) COMMENT 'Llave primaria de la tabla',
    CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category(category_id)
) COMMENT = 'Tabla que contiene los productos registrados';

-- Constraints de producto
CREATE UNIQUE INDEX ux_product_gtin ON product(gtin) COMMENT 'Index único para GTIN';
CREATE UNIQUE INDEX ux_product_product ON product(product) COMMENT 'Index único para nombre de producto';

-- Imagen de producto
CREATE TABLE product_image(
    product_image_id INT NOT NULL AUTO_INCREMENT COMMENT 'Identificador único de la imagen del producto',
    product_id INT NOT NULL COMMENT 'Identificador del producto',
    image VARCHAR(255) NOT NULL COMMENT 'Ruta de la imagen',
    status TINYINT NOT NULL COMMENT 'Estado de la imagen',
    PRIMARY KEY (product_image_id) COMMENT 'Llave primaria de la tabla',
    CONSTRAINT fk_product_image_product FOREIGN KEY (product_id) REFERENCES product(product_id)
) COMMENT = 'Tabla que contiene las imágenes de los productos';

-- Simulación DML --
-- INSERT INTO category (category, tag, status)
-- VALUES 
-- ('Mexicana', 'mx', '1'),
-- ('Italiana', 'it', '0'),
-- ('Japonesa', 'jp', '1');

-- INSERT INTO product (gtin, product, description, price, stock, category_id, status)
-- VALUES 
-- ('7501234567890', 'Tacos al Pastor', 'Deliciosos tacos de carne al pastor con piña', 45.00, 100, 1, 1),
-- ('7501234567891', 'Pizza Margarita', 'Pizza italiana con tomate, mozzarella y albahaca', 120.00, 50, 2, 1),
-- ('7501234567892', 'Sushi Roll', 'Rollo de sushi con salmón y aguacate', 85.00, 75, 3, 1);