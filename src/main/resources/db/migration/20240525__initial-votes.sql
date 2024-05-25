CREATE TABLE public.votes
(
    id           SERIAL PRIMARY KEY,
    id_from_user BIGINT  NOT NULL,
    id_to_user   BIGINT  NOT NULL,
    is_like      BOOLEAN NOT NULL,
    FOREIGN KEY (id_from_user) REFERENCES public.users (id),
    FOREIGN KEY (id_to_user) REFERENCES public.users (id)
);