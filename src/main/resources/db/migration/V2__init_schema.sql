CREATE TABLE otp_records (
    id UUID  NOT NULL PRIMARY KEY,
    recipient character varying(255),
    otp_value character varying(255),
    confirmation_code character varying(255),
    is_success bigint,
    create_date timestamp without time zone NOT NULL,
    expire_date timestamp without time zone NOT NULL
);

create table access_group (
    id UUID  NOT NULL PRIMARY KEY,
    group_name character varying(45)
);

create table access_user (
    id UUID NOT NULL PRIMARY KEY,
    login character varying(255),
    note character varying(255),
    password character varying(255),
    user_name character varying(255),
    group_id UUID,
    session_id UUID,
    constraint group_id_fk1 foreign key (group_id) references access_group (id)
);

create table session (
     id UUID NOT NULL PRIMARY KEY,
     start_time timestamp without time zone,
     expire_time timestamp without time zone,
     update_time timestamp without time zone,
     user_id UUID,
     constraint user_id_fk1 foreign key (user_id) references access_user (id)
);
