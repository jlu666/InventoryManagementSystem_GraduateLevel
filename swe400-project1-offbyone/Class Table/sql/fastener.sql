CREATE TABLE fastener(
    id INTEGER PRIMARY KEY NOT NULL,
    length DOUBLE NOT NULL,
    FOREIGN KEY(id) REFERENCES inventory_item(id)
);
