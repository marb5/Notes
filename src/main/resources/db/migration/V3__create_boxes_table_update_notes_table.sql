/**
 * Author:  marcin
 * Created: May 30, 2021
 */
drop table if exists notes;
create table notes (
    id int primary key auto_increment,
    name varchar(40) not null,
    content varchar(4000) not null,
    box_id int not null
);
drop table if exists boxes;
create table boxes (
    id int primary key auto_increment,
    name varchar(40) not null
);
alter table notes
    add foreign key (box_id) references boxes (id);
