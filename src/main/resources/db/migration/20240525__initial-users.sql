CREATE TABLE public.users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    photo    VARCHAR(255) NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(50)  NOT NULL
);