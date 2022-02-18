CREATE DATABASE IF NOT EXISTS LittleTask;
CREATE TABLE IF NOT EXISTS LittleTask.Products(
`name` VARCHAR(255),
`model` VARCHAR(255),
`currentPrice` DECIMAL(15,2),
`oldPrice` DECIMAL(15,2),
`url` VARCHAR(255)
)