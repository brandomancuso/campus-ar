create table LOCATION_INFO (
	id serial primary key,
  	location_id integer not null references LOCATION(id) ON DELETE CASCADE,
  	title varchar(100) not null,
  	content varchar(3000)
	);