CREATE TABLE public.user (
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    username text NOT NULL,
    password text NOT NULL,
    PRIMARY KEY (id)
);

