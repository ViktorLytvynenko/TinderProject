INSERT INTO public.users (name, photo, email, password)
VALUES ('Vadym', 'https://cdn.pixabay.com/photo/2024/03/05/19/26/duck-8615153_960_720.jpg', 'vadym@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Viktor', 'https://cdn.pixabay.com/photo/2024/02/17/15/02/ostrich-8579501_960_720.jpg', 'lytvynenkobooks@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Karina',
        'https://static.nv.ua/shared/system/MediaPhotoBig/images/000/003/982/original/e352349a0d6bcfdfcf03e9c7c43d5293.png?q=85&stamp=20211118114651&f=webp', 'karinka2323@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Eugene', 'https://cdn.nur.kz/images/1120x630/497786ccb8fa7bf2.jpeg', 'ownereugene@gmail.com', '1111');
INSERT INTO public.users (name, photo, email, password)
VALUES ('Nazar', 'https://doctor-set.by/upload/iblock/76f/76ff52e38244e3354511c6731d20f97b.jpg', 'xwx2007@gmail.com', '1111');

INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (2, 1, '1111', '2024-04-17 16:30:50.928367');
INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (3, 2, 'Привет', '2024-04-18 13:07:23.913484');
INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (3, 2, 'Как дела?', '2024-04-18 13:07:30.006429');
INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (2, 3, 'Привет', '2024-04-18 13:08:07.213994');
INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (7, 8, 'Как дела?', '2024-04-18 13:07:30.006429');
INSERT INTO public.messages (id_from_user, id_to_user, text, time) VALUES (8, 7, 'Привет', '2024-04-18 13:08:07.213994');
