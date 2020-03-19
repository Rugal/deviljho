SET search_path = fridge;

INSERT INTO item(iid, name) VALUES (1, 'Banana');
INSERT INTO item(iid, name) VALUES (2, 'Mango');
INSERT INTO item(iid, name) VALUES (3, 'Strawberry');
INSERT INTO item(iid, name) VALUES (4, 'Cabbage');
INSERT INTO item(iid, name) VALUES (5, 'Beef');

SELECT pg_catalog.setval('item_iid_seq', 5, true);

INSERT INTO tag(tid, name) VALUES (1, 'Vegetable');
INSERT INTO tag(tid, name) VALUES (2, 'Meat');
INSERT INTO tag(tid, name) VALUES (3, 'Fruit');
INSERT INTO tag(tid, name) VALUES (4, 'Beverage');
INSERT INTO tag(tid, name) VALUES (5, 'Frozen');

SELECT pg_catalog.setval('tag_tid_seq', 5, true);
