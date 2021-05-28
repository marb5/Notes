/**
 * Author:  marcin
 * Created: May 28, 2021
 */
drop table if exists notes;
create table notes (
    id int primary key auto_increment,
    name varchar(40) not null,
    content varchar(4000) not null,
    box_id int not null,
    check (box_id >= 1)
);
