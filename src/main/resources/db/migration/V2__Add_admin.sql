DO $$
DECLARE UserId bigint;
BEGIN
  INSERT INTO "usr" ("usr_name", "usr_password", "usr_email") VALUES
    ('admin', '123', 'a@a.com')
  RETURNING "usr_id"
    INTO UserId;

  INSERT INTO "user_role" ("usr_id", "roles") VALUES
    (UserId, 'USER'),
    (UserId, 'ADMIN');
END $$
