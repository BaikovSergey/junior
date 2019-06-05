CREATE TABLE "Item_category" (
	category_ID serial PRIMARY KEY,
	item_id INT NOT NULL REFERENCES "Item"(item_id),
	category VARCHAR (20)
);