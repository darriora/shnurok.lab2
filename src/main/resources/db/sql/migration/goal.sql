create schema if not exists labs;

create table labs.function(
    id serial primary key,
    c_function_name varchar(255),
    c_count integer check ( c_count >= 2 ),
    c_x_from double,
    c_x_to double
);

create table labs.point(
    id serial primary key ,
    function_id integer,
    c_x_value double,
    c_y_value double
    foreign key (function_id) references labs.function(id) on delete cascade
);