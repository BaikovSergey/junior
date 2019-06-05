CREATE TABLE "Role" (
	role_ID serial PRIMARY KEY,
	user_id INT NOT NULL REFERENCES "User"(user_id),
	role_1 VARCHAR (20)
);