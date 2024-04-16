CREATE TABLE public.users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(50)  NOT NULL,
    photo    VARCHAR(255) NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(50)  NOT NULL
);

CREATE TABLE public.votes
(
    id           SERIAL PRIMARY KEY,
    id_from_user BIGINT  NOT NULL,
    id_to_user   BIGINT  NOT NULL,
    is_like      BOOLEAN NOT NULL,
    FOREIGN KEY (id_from_user) REFERENCES public.users (id),
    FOREIGN KEY (id_to_user) REFERENCES public.users (id)
);

CREATE TABLE public.messages
(
    id           SERIAL PRIMARY KEY,
    id_from_user BIGINT    NOT NULL,
    id_to_user   BIGINT    NOT NULL,
    text         varchar   NOT NULL,
    time         timestamp NOT NULL DEFAULT now(),
    FOREIGN KEY (id_from_user) REFERENCES public.users (id),
    FOREIGN KEY (id_to_user) REFERENCES public.users (id)
)