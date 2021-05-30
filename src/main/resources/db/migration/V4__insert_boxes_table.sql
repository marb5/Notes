/**
 * Author:  marcin
 * Created: May 30, 2021
 */
insert into boxes (name)
values
('Box1'),
('Box2');
insert into notes (name, content, box_id)
values 
('Note1', 'First note with some content. Just to see do everything work properly.', 1),
('Note2', 'Second note with different content. Some more text so there will be more data.', 1);
