CREATE TABLE users
(
  id BINARY(16) PRIMARY KEY,
  username varchar(50) NOT NULL,
  password char(60) NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  failed_password_attempts int NOT NULL,
  account_expired boolean NOT NULL,
  account_locked boolean NOT NULL,
  credential_expired boolean NOT NULL,
  enabled boolean NOT NULL,
  last_login_date timestamp,
  last_password_changed_at timestamp,
  last_locked_out_at timestamp,
  created_at timestamp NOT NULL,
  updated_at timestamp
);
CREATE UNIQUE INDEX users_username_uindex ON users (username);

CREATE TABLE SPRING_SESSION (
                              PRIMARY_ID CHAR(36) NOT NULL,
                              SESSION_ID CHAR(36) NOT NULL,
                              CREATION_TIME BIGINT NOT NULL,
                              LAST_ACCESS_TIME BIGINT NOT NULL,
                              MAX_INACTIVE_INTERVAL INT NOT NULL,
                              EXPIRY_TIME BIGINT NOT NULL,
                              PRINCIPAL_NAME VARCHAR(100),
                              CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
                                         SESSION_PRIMARY_ID CHAR(36) NOT NULL,
                                         ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
                                         ATTRIBUTE_BYTES blob NOT NULL,
                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
                                         CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
);

CREATE INDEX SPRING_SESSION_ATTRIBUTES_IX1 ON SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID);
