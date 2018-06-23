CREATE TABLE "category" (
  "category_id"      BIGSERIAL PRIMARY KEY,
  "category_title"   VARCHAR(255),
  "category_created" TIMESTAMP(0) NOT NULL DEFAULT now(),
  "category_updated" TIMESTAMP(0) NOT NULL DEFAULT now()
);

CREATE TABLE "post" (
  "post_id"          BIGSERIAL PRIMARY KEY,
  "post_title"       VARCHAR(255),
  "post_description" VARCHAR(255),
  "post_text"        TEXT,
  "post_created"     TIMESTAMP(0) NOT NULL DEFAULT now(),
  "post_updated"     TIMESTAMP(0) NOT NULL DEFAULT now()
);

CREATE TABLE "usr" (
  "usr_id"              BIGSERIAL PRIMARY KEY,
  "usr_name"            VARCHAR(255) NOT NULL,
  "usr_password"        VARCHAR(255) NOT NULL,
  "usr_email"           VARCHAR(255),
  "usr_activation_code" VARCHAR (255),
  "usr_created"         TIMESTAMP(0) NOT NULL DEFAULT now(),
  "usr_updated"         TIMESTAMP(0) NOT NULL DEFAULT now()
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
