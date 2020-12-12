CREATE TABLE "otp_records" (
    id character varying(60) NOT NULL PRIMARY KEY,
    otp_value character varying(255),
    recipient character varying(255),
    confirmation_code character varying(255),
    is_success bigint,
    create_date timestamp without time zone NOT NULL,
    expire_date timestamp without time zone NOT NULL
);
