create table employees (
    id serial primary key,
    first_name varchar(20),
    last_name varchar(20),
    pather_name varchar(20),
    position varchar(50),
    salary numeric
);

create table departments (
    id serial primary key,
    name varchar(20)
);

create table departments_employees (
  id serial primary key,
  department_id integer references departments(id),
  employee_id integer references employees(id)
);

create table projects (
  id serial primary key,
  name varchar(200),
  cost numeric,
  department_id integer references departments(id),
  date_beg date,
  date_end date,
  date_end_real date
);
