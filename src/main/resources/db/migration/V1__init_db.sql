CREATE TABLE IF NOT EXISTS agent (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL
) DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS real_estate (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(100) NOT NULL,
    price DECIMAL NOT NULL,
    address varchar(200) NOT NULL,
    status varchar(15) NOT NULL,
    agent_id int,
    views int DEFAULT 0,
    FOREIGN KEY (agent_id) REFERENCES agent(id)
) DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS deal (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    real_estate_id int NOT NULL,
    agent_id int NOT NULL,
    price DECIMAL NOT NULL,
    FOREIGN KEY (real_estate_id) REFERENCES real_estate(id),
    FOREIGN KEY (agent_id) REFERENCES agent(id)
) DEFAULT CHARSET=UTF8;