CREATE TABLE "category" (
  "id"      BIGSERIAL PRIMARY KEY,
  "title"   VARCHAR(255),
  "created" TIMESTAMP(0) NOT NULL DEFAULT now(),
  "updated" TIMESTAMP(0) NOT NULL DEFAULT now()
);

CREATE TABLE "post" (
  "id"          BIGSERIAL PRIMARY KEY,
  "title"       VARCHAR(255),
  "description" VARCHAR(255),
  "text"        TEXT,
  "created"     TIMESTAMP(0) NOT NULL DEFAULT now(),
  "updated"     TIMESTAMP(0) NOT NULL DEFAULT now()
);

CREATE TABLE "usr" (
  "id"              BIGSERIAL PRIMARY KEY,
  "name"            VARCHAR(255) NOT NULL,
  "password"        VARCHAR(255) NOT NULL,
  "email"           VARCHAR(255),
  "activation_code" VARCHAR (255),
  "created"         TIMESTAMP(0) NOT NULL DEFAULT now(),
  "updated"         TIMESTAMP(0) NOT NULL DEFAULT now()
);

CREATE TABLE "user_role" (
  "roles" VARCHAR(255)
);

ALTER TABLE "post"
  ADD "category_id" BIGINT NOT NULL,
  ADD CONSTRAINT "fk_post_category_id"
FOREIGN KEY ("category_id")
REFERENCES "category" ("category_id");

ALTER TABLE "post"
  ADD "usr_id" BIGINT NOT NULL,
  ADD CONSTRAINT "fk_post_usr_id"
FOREIGN KEY ("usr_id")
REFERENCES "usr" ("usr_id");

ALTER TABLE "user_role"
  ADD "usr_id" BIGINT NOT NULL,
  ADD CONSTRAINT "fk_user_role_usr_id"
FOREIGN KEY ("usr_id")
REFERENCES "usr" ("usr_id");

CREATE FUNCTION update_post_timestamp()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.post_updated = now();
  RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';
CREATE TRIGGER "tr_post_updated"
  BEFORE UPDATE
  ON "post"
  FOR EACH ROW
EXECUTE PROCEDURE update_post_timestamp();

CREATE FUNCTION update_category_timestamp()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.category_updated = now();
  RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';
CREATE TRIGGER "tr_category_updated"
  BEFORE UPDATE
  ON "category"
  FOR EACH ROW
EXECUTE PROCEDURE update_category_timestamp();

CREATE FUNCTION update_usr_timestamp()
  RETURNS TRIGGER AS $$
BEGIN
  NEW.usr_updated = now();
  RETURN NEW;
END;
$$
LANGUAGE 'plpgsql';
CREATE TRIGGER "tr_usr_updated"
  BEFORE UPDATE
  ON "usr"
  FOR EACH ROW
EXECUTE PROCEDURE update_usr_timestamp();
