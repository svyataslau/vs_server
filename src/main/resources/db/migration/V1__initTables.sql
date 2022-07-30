CREATE TABLE user_profile (
                              id SERIAL,
                              nickname VARCHAR(128) UNIQUE,
                              email VARCHAR(128) UNIQUE,
                              PRIMARY KEY (id)
);

CREATE TABLE promise (
                         id SERIAL,
                         title VARCHAR(128),
                         PRIMARY KEY (id)
);

CREATE TABLE user_challenge (
                                id SERIAL,
                                user_id INTEGER REFERENCES  user_profile(id) ON DELETE  CASCADE,
                                promise_id INTEGER REFERENCES  promise(id) ON DELETE  CASCADE,
                                start_date TIMESTAMP NOT NULL,
                                days_number INTEGER NOT NULL,
                                PRIMARY KEY (id)
);

CREATE TABLE reason (
                        id SERIAL,
                        user_challenge_id INTEGER REFERENCES  user_challenge(id) ON DELETE  CASCADE,
                        description VARCHAR(128),
                        PRIMARY KEY (id)
);

INSERT INTO user_profile (nickname, email) VALUES ('nikky34','some432@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('mortyRick','letsgo763@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('locky89','manhello543@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('heyheyhey666','dude345@gmail.com');

INSERT INTO promise (title) VALUES ('Stop addiction');
INSERT INTO promise (title) VALUES ('Get a new ability');
INSERT INTO promise (title) VALUES ('Learn something new');
INSERT INTO promise (title) VALUES ('Lose weight');

INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES (1,3,'2019-02-15 10:32:02', 30);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES (2,4,'2019-02-15 10:54:02', 60);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES (3,3,'2019-02-15 10:43:02', 30);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number) VALUES (4,2,'2019-02-15 10:55:02', 20);

INSERT INTO reason (user_challenge_id, description) VALUES (1,'I want to learn java & spring');
INSERT INTO reason (user_challenge_id, description) VALUES (2,'I have to lose 20 kg');
INSERT INTO reason (user_challenge_id, description) VALUES (3,'I want to read a book about front-end 50 pages a day');
INSERT INTO reason (user_challenge_id, description) VALUES (4,'learn how to swim');
