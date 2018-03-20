-- ----------------------------------------------------------------
--  DATABASE beitechdb
-- ----------------------------------------------------------------

CREATE DATABASE beitechdb
   CHARACTER SET `latin1`
   COLLATE `latin1_swedish_ci`;
   
INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (100,
             'Bravia',
             'a SONY TV',
             178.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (101,
             'S8',
             'a Samsung',
             570.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (102,
             'iPhone X',
             'an Apple product',
             690.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (103,
             'iPod Nano',
             'another Apple product',
             87.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (104,
             'Surface',
             'a Windows tablet',
             255.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (105,
             'Moto G5',
             'a Lenovo phone',
             189.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (106,
             'Moto G5 S Plus',
             'another Lenovo phone',
             388.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (107,
             'Moto Z Play',
             'Motorola - Lenovo cool phone',
             987.0);

INSERT INTO beitechdb.`product`(product_id,
                                name,
                                product_description,
                                price)
     VALUES (108,
             'Satellite1',
             'a TOSHIBA laptop',
             1500.0);

INSERT INTO beitechdb.`order_detail`(order_id, product_id)
     VALUES (1000, 100);

INSERT INTO beitechdb.`order_detail`(order_id, product_id)
     VALUES (1000, 101);

INSERT INTO beitechdb.`order_detail`(order_id, product_id)
     VALUES (1001, 105);

INSERT INTO beitechdb.`customer`(customer_id, name, email)
     VALUES (1, 'Gabriel Solorzano', 'g.solorzanos@gmail.com');

INSERT INTO beitechdb.`customer`(customer_id, name, email)
     VALUES (2, 'Dario Sanchez', 'dario@sanchez.com');

INSERT INTO beitechdb.`customer`(customer_id, name, email)
     VALUES (3, 'Tatiana Salazar', 'tatiana.salazar@beitech.co');

INSERT INTO beitechdb.`customer`(customer_id, name, email)
     VALUES (4, 'Fredy Romero', 'fredys@beitech.co');

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 101);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 100);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (3, 102);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (3, 103);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 104);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 105);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 106);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 101);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 100);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 105);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (2, 106);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 107);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (3, 102);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (3, 103);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 101);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 104);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 100);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 108);

INSERT INTO beitechdb.`available_products`(customer_id, product_id)
     VALUES (4, 107);

INSERT INTO beitechdb.`order`(order_id,
                              customer_id,
                              delivery_address,
                              creation_date,
                              last_modified)
     VALUES (1000,
             2,
             'Calle 83 #96-51',
             '2018-03-18 08:06:47.0',
             '2018-03-19 08:08:17.0');

INSERT INTO beitechdb.`order`(order_id,
                              customer_id,
                              delivery_address,
                              creation_date,
                              last_modified)
     VALUES (1001,
             2,
             'Calle 100 Kr.15',
             '2018-03-19 08:07:21.0',
             '2018-03-19 08:08:22.0');

COMMIT;