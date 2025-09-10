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

-- Simulación DML --
-- INSERT INTO category (category, tag, status)
-- VALUES 
-- ('Mexicana', 'mx', '1'),
-- ('Italiana', 'it', '0'),
-- ('Japonesa', 'jp', '1');