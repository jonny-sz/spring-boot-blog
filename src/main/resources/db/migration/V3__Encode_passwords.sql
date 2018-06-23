CREATE extension if NOT EXISTS pgcrypto;

UPDATE "usr"
SET "usr_password" = crypt("usr_password", gen_salt('bf', 8));
