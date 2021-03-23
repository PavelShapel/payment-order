create TABLE public.country
(
id bigserial NOT NULL,
name character varying COLLATE pg_catalog."default" NOT NULL,
version bigint NOT NULL,
inserted timestamp NOT NULL,
updated timestamp NOT NULL,
CONSTRAINT storage_pkey PRIMARY KEY (id),
CONSTRAINT storage_name_key UNIQUE (name)
);
