CREATE TABLE strip_nails(
    id INTEGER PRIMARY KEY NOT NULL,
    number_in_strip INTEGER NOT NULL,
    FOREIGN KEY(id) REFERENCES inventory_item(id)
);
