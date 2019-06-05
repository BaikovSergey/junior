CREATE TABLE "Item_state" (
	state_ID serial PRIMARY KEY,
	item_id INT NOT NULL REFERENCES "Item"(item_id),
	state VARCHAR (15)
);