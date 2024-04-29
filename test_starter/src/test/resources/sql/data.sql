INSERT INTO company (id,name)
value (1,'Google'),(2,'Meta'),(3,'Amazon');

# SELECT SETVAL('company_id_seq',(SELECT MAX(id) FROM company)); for postgresql
ALTER TABLE company AUTO_INCREMENT = 4;

INSERT INTO users (id,last_name, first_name, role, company_id)
    VALUE (1,'Naumenko','Vadim','ADMIN',1),
    (3,'Fishchuk','Roma','USER',2),
    (2,'Rabeshko','Tetiana','ADMIN',3);

ALTER TABLE users AUTO_INCREMENT = 4;