CREATE TABLE "Item_comment" (
	comment_ID serial PRIMARY KEY,
	item_id INT NOT NULL REFERENCES "Item"(item_id),
	comment_text TEXT
);