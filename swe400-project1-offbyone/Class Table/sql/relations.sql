INSERT INTO powertool_stripnail(powertoolId, stripnailId) VALUES
    (
        (SELECT id FROM inventory_item WHERE upc='1231231234'),
        (SELECT id FROM inventory_item WHERE upc='5453432345')
    ),
    (
        (SELECT id FROM inventory_item WHERE upc='4445553333'),
        (SELECT id FROM inventory_item WHERE upc='5453432345')
    ),
    (
        (SELECT id FROM inventory_item WHERE upc='1231231234'),
        (SELECT id FROM inventory_item WHERE upc='4343434543')
    ),
    (
        (SELECT id FROM inventory_item WHERE upc='4445553333'),
        (SELECT id FROM inventory_item WHERE upc='4343434543')
    ),
    (
        (SELECT id FROM inventory_item WHERE upc='7654564848'),
        (SELECT id FROM inventory_item WHERE upc='6565459876')
    ),
    (
        (SELECT id FROM inventory_item WHERE upc='7784452828'),
        (SELECT id FROM inventory_item WHERE upc='6565459876')
    );
