CREATE TABLE "User" (
	user_ID serial PRIMARY KEY REFERENCES "Item"(item_id),
	firs_name VARCHAR (15),
	last_name VARCHAR (20)
);