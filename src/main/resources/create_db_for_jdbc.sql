CREATE USER prospring5 WITH PASSWORD 'prospring5' LOGIN;

CREATE DATABASE MUSICDB OWNER prospring5;

GRANT ALL PRIVILEGES ON DATABASE MUSICDB TO prospring5;


GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO prospring5;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO prospring5;
GRANT ALL PRIVILEGES ON ALL FUNCTIONS IN SCHEMA public TO prospring5;

GRANT CREATE ON SCHEMA public TO prospring5;
