CREATE TABLE "Role_permission" (
	permision_ID serial PRIMARY KEY,
	role_id INT NOT NULL REFERENCES "Role"(role_id),
	permission_1 VARCHAR (10)
);