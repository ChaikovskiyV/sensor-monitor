Web application Sensor Monitor

This application works with sensors data. It allows to add new sensors with following information:

- name;
- description;
- model;
- type;
- range;
- util;
- location.

The sensors data is available to authorised users only. To add a new sensor or update existing data the user must have
the administrator role.

To run this rest api you need:

- create a database sensor_base in PostgreSQL and run following script:
    - CREATE TABLE users (
      id int8 NOT NULL,
      "password" varchar(255) NOT NULL,
      "role" varchar(255) NOT NULL,
      username varchar(255) NOT NULL,
      CONSTRAINT uk_username UNIQUE (username),
      CONSTRAINT users_pkey PRIMARY KEY (id)
      );
    - INSERT INTO users
      (id, username, "password", "role")
      VALUES(1, '$2a$10$yV.1GkX3fBN2j1yKT/7cG.MGVnM63XFMoQ1RwJ4w.aKlH9/WBfo66', 'ADMINISTRATOR', 'admin');
    - INSERT INTO users
      (id, username, "password", "role")
      VALUES(2, '$2a$10$yV.1GkX3fBN2j1yKT/7cG.MGVnM63XFMoQ1RwJ4w.aKlH9/WBfo66', 'VIEWER', 'user');
    - CREATE TABLE sensors (
      id int8 NOT NULL,
      description varchar(255) NOT NULL,
      "location" varchar(50) NOT NULL,
      model varchar(25) NOT NULL,
      "name" varchar(40) NOT NULL,
      range_id int8 NOT NULL,
      type_id int8 NOT NULL,
      unit_id int8 NOT NULL,
      CONSTRAINT sensors_pkey PRIMARY KEY (id)
      );
    - CREATE TABLE ranges (
      id int8 NOT NULL,
      range_from int4 NOT NULL,
      range_to int4 NOT NULL,
      CONSTRAINT ranges_pkey PRIMARY KEY (id)
      );
    - CREATE TABLE types (
      id int8 NOT NULL,
      "type" varchar(255) NOT NULL,
      CONSTRAINT types_pkey PRIMARY KEY (id),
      CONSTRAINT uk_type UNIQUE (type)
      );
    - INSERT INTO types(id, type) VALUES(3, 'Temperature');
    - INSERT INTO types(id, type) VALUES(4, 'Pressure');
    - INSERT INTO types(id, type) VALUES(5, 'Voltage');
    - INSERT INTO types(id, type) VALUES(6, 'Humidity');
    - CREATE TABLE units (
      id int8 NOT NULL,
      unit varchar(255) NOT NULL,
      CONSTRAINT uk_unit UNIQUE (unit),
      CONSTRAINT units_pkey PRIMARY KEY (id)
      );
    - INSERT INTO units(id, unit) VALUES(7, '°C');
    - INSERT INTO units(id, unit) VALUES(8, 'bar');
    - INSERT INTO units(id, unit) VALUES(9, 'voltage');
    - INSERT INTO units(id, unit) VALUES(10, '%');

- pull this project to your repository;
- change the 'spring.datasource.password' property in the 'src/main/resources/application.properties' file to meaning
  that suits your database;
- open terminal, go to the package with this api and run the command ./gradlew bootRun;
- by default, this api runs on the port 8088;
- logins and passwords to authorize successfully:
  - a viewer: login 'user' and password 'password';
  - an administrator: login 'admin' and password 'password'.