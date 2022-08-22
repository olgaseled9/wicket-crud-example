CREATE TABLE department
(
    department_id          BIGINT       NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START with 1 INCREMENT by 1),
    name        VARCHAR(200) NOT NULL
);
