CREATE TABLE "Attached_files" (
	file_ID serial PRIMARY KEY,
	item_id INT NOT NULL REFERENCES "Item"(item_id),
	file TEXT
);