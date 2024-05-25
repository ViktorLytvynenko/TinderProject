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