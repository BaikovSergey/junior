 CREATE TABLE vacancy (
	 id SERIAL PRIMARY KEY,
	 name VARCHAR(200) UNIQUE,
	 text TEXT,
	 date VARCHAR(20),
	 link TEXT
	 );