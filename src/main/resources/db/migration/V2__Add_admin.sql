DO $$
DECLARE UserId bigint;
BEGIN
  INSERT INTO "usr" ("username", "password", "email", "created", "updated") VALUES
    ('admin', '123', 'a@a.com', current_timestamp, current_timestamp)
  RETURNING "id"
    INTO UserId;

  INSERT INTO "user_role" ("usr_id", "roles") VALUES
    (UserId, 'USER'),
    (UserId, 'ADMIN');
END $$
