CREATE TABLE tool(
    id INTEGER PRIMARY KEY NOT NULL,
    description VARCHAR(100) NOT NULL,
    FOREIGN KEY(id) REFERENCES inventory_item(id)
);
