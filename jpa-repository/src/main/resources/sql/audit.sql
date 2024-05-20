ALTER TABLE users
ADD COLUMN created_at TIMESTAMP;

ALTER TABLE users
ADD COLUMN modified_at TIMESTAMP null;

ALTER TABLE users
    ADD COLUMN created_by varchar(32) null;

ALTER TABLE users
    ADD COLUMN modified_by varchar(32) null;