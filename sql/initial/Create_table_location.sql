create table LOCATION (
	id serial primary key,
  	name varchar(100) not null unique,
  	latitude numeric(9,5),
  	longitude numeric(9,5),
  	image varchar(100)
	);