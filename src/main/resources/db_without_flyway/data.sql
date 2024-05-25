INSERT INTO public.users (name, photo, email, password)
VALUES ('Vadym', 'https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_960_720.jpg', 'vadym@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Viktor', 'https://cdn.pixabay.com/photo/2024/02/17/15/02/ostrich-8579501_960_720.jpg', 'lytvynenkobooks@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Karina',
        'https://static.nv.ua/shared/system/MediaPhotoBig/images/000/003/982/original/e352349a0d6bcfdfcf03e9c7c43d5293.png?q=85&stamp=20211118114651&f=webp', 'karinka2323@gmail.com', '1111');


INSERT INTO public.messages (id, id_from_user, id_to_user, text, time) VALUES (1, 2, 1, '1111', '2024-04-17 16:30:50.928367');
INSERT INTO public.messages (id, id_from_user, id_to_user, text, time) VALUES (2, 3, 2, 'Привет', '2024-04-18 13:07:23.913484');
INSERT INTO public.messages (id, id_from_user, id_to_user, text, time) VALUES (3, 3, 2, 'Как дела?', '2024-04-18 13:07:30.006429');
INSERT INTO public.messages (id, id_from_user, id_to_user, text, time) VALUES (4, 2, 3, 'Привет', '2024-04-18 13:08:07.213994');
