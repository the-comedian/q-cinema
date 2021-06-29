drop database if exists testdb;
create database testdb;

\connect testdb

create table cinema
(
	id bigint not null
		constraint cinema_pk
			primary key,
	name varchar(255),
	brief varchar(50)
);

alter table cinema owner to postgres;

create unique index cinema_id_uindex
	on cinema (id);

create table hall
(
	id bigint not null
		constraint hall_pk
			primary key,
	name varchar(255),
	cinema_id bigint not null
		constraint cinema_id_pk
			references cinema
);

alter table hall owner to postgres;

create unique index hall_id_uindex
	on hall (id);

create table place
(
	id bigint not null
		constraint place_pk
			primary key,
	row integer,
	place integer,
	reserved integer default 0,
	state integer default 0,
	hall_id bigint
		constraint halll_fk
			references hall
);

alter table place owner to postgres;

create unique index place_id_uindex
	on place (id);

create unique index place_id_uindex_2
	on place (id);

INSERT INTO public.cinema (id, name, brief) VALUES (1, 'Первый', 'first');
INSERT INTO public.cinema (id, name, brief) VALUES (2, 'Второй', 'second');
INSERT INTO public.cinema (id, name, brief) VALUES (3, 'Третий', 'third');

INSERT INTO public.hall (id, name, cinema_id) VALUES (1, 'Один один', 1);
INSERT INTO public.hall (id, name, cinema_id) VALUES (2, 'Один два', 1);
INSERT INTO public.hall (id, name, cinema_id) VALUES (3, 'Один три', 1);
INSERT INTO public.hall (id, name, cinema_id) VALUES (4, 'Два один', 2);
INSERT INTO public.hall (id, name, cinema_id) VALUES (5, 'Два два', 2);
INSERT INTO public.hall (id, name, cinema_id) VALUES (6, 'Два три', 2);
INSERT INTO public.hall (id, name, cinema_id) VALUES (7, 'Три один', 3);
INSERT INTO public.hall (id, name, cinema_id) VALUES (8, 'Три два', 3);
INSERT INTO public.hall (id, name, cinema_id) VALUES (9, 'Три три', 3);

INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (1, 1, 1, 1, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (2, 1, 2, 1, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (3, 1, 3, 1, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (4, 1, 4, 1, 1, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (5, 1, 5, 1, 1, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (6, 1, 6, 1, 1, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (7, 1, 7, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (8, 1, 8, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (9, 1, 9, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (10, 1, 10, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (11, 2, 1, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (12, 2, 2, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (13, 2, 3, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (14, 2, 4, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (15, 2, 5, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (16, 2, 6, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (17, 2, 7, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (18, 2, 8, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (19, 2, 9, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (20, 2, 10, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (21, 3, 1, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (22, 3, 2, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (23, 3, 3, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (24, 3, 4, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (25, 3, 5, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (26, 3, 6, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (27, 3, 7, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (28, 3, 8, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (29, 3, 9, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (30, 3, 10, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (31, 4, 1, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (32, 4, 2, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (33, 4, 3, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (34, 4, 4, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (35, 4, 5, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (36, 4, 6, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (37, 4, 7, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (38, 4, 8, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (39, 4, 9, 0, 0, 1);
INSERT INTO public.place (id, row, place, reserved, state, hall_id) VALUES (40, 4, 10, 0, 0, 1);
