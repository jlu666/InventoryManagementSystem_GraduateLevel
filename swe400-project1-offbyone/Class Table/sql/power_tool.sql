CREATE TABLE power_tool(
    id INTEGER PRIMARY KEY NOT NULL,
    battery_powered BOOLEAN NOT NULL,
    FOREIGN KEY(id) REFERENCES inventory_item(id)
);
