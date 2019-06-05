CREATE TABLE "Car" (
	Car_ID SERIAL PRIMARY KEY,
	Car_name VARCHAR (50) NOT NULL,
	Car_body INT NOT NULL REFERENCES "Car_body"(body_ID),
	Car_engine INT NOT NULL REFERENCES "Car_engine"(engine_ID),
	Car_transmission INT NOT NULL REFERENCES "Car_transmission"(transmission_ID)
)
