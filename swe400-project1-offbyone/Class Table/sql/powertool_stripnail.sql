CREATE TABLE powertool_stripnail
(powertoolId int,
 stripnailId int,
 PRIMARY KEY(powertoolId, stripnailId),
 FOREIGN KEY(powertoolId) REFERENCES power_tool(id),
 FOREIGN KEY (stripnailId) REFERENCES strip_nails(id)
);