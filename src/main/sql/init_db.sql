DROP TABLE IF EXISTS public.product_categories;
DROP TABLE IF EXISTS public.products_categories;
CREATE TABLE public.product_categories (
                                           id serial NOT NULL PRIMARY KEY,
                                           name text NOT NULL,
                                           description text NOT NULL,
                                           department text NOT NULL
);


DROP TABLE IF EXISTS public.products;
CREATE TABLE public.products (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 description text NOT NULL,
                                 price FLOAT NOT NULL ,
                                 currency text not null,
                                 image text not null,
                                 product_category_id integer,
                                 supplier_id integer

);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE public.supplier (
                                 id serial NOT NULL PRIMARY KEY,
                                 name text NOT NULL,
                                 description text NOT NULL

);


DROP TABLE IF EXISTS public.user_table;
CREATE TABLE public.user_table (
                                 id serial NOT NULL PRIMARY KEY,
                                 username text NOT NULL,
                                 password text NOT NULL
);

DROP TABLE IF EXISTS public.user_table;
CREATE TABLE public.user_table (
                                   id serial NOT NULL PRIMARY KEY,
                                   username text NOT NULL,
                                   password text NOT NULL
);

DROP TABLE IF EXISTS public.cart;
CREATE TABLE public.cart (
                                   user_id int NOT NULL,
                                   product_id int[]
);
