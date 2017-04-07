CREATE TABLE nail(
    id INTEGER PRIMARY KEY NOT NULL,
    number_in_box INTEGER NOT NULL,
    FOREIGN KEY(id) REFERENCES inventory_item(id)
);
