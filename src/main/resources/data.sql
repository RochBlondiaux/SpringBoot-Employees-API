DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS jobs;

CREATE TABLE employees (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  mail VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  hire_date DATE NOT NULL,
  job VARCHAR(250) NOT NULL
);

CREATE TABLE jobs (
  name VARCHAR(250) NOT NULL PRIMARY KEY,
  salary DOUBLE NOT NULL
);

INSERT INTO jobs (name, salary) VALUES
  ('Collaborator', 10000),
  ('CTO', 20000),
  ('CEO', 30000);

INSERT INTO employees (first_name, last_name, mail, password) VALUES
  ('Laurent', 'GINA', 'laurentgina@mail.com', 'laurent'),
  ('Sophie', 'FONCEK', 'sophiefoncek@mail.com', 'sophie'),
  ('Agathe', 'FEELING', 'agathefeeling@mail.com', 'agathe');