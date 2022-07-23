CREATE TABLE user_profile (
                              id SERIAL,
                              nickname VARCHAR(128) UNIQUE,
                              email VARCHAR(128) UNIQUE,
                              PRIMARY KEY (id)
);

CREATE TABLE default_promise (
                                 id SERIAL,
                                 title VARCHAR(128),
                                 PRIMARY KEY (id)
);

CREATE TABLE user_challenge (
                                user_id INTEGER REFERENCES  user_profile(id) ON DELETE  CASCADE,
                                default_promise_id INTEGER REFERENCES  default_promise(id) ON DELETE  CASCADE,
                                description VARCHAR(128),
                                start_date DATE NOT NULL,
                                days_number INTEGER NOT NULL,
                                PRIMARY KEY (user_id, default_promise_id)
);

INSERT INTO user_profile (nickname, email) VALUES ('nikky34','some432@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('mortyRick','letsgo763@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('locky89','manhello543@gmail.com');
INSERT INTO user_profile (nickname, email) VALUES ('heyheyhey666','dude345@gmail.com');

INSERT INTO default_promise (title) VALUES ('Stop addiction');
INSERT INTO default_promise (title) VALUES ('Get a new ability');
INSERT INTO default_promise (title) VALUES ('Learn something new');
INSERT INTO default_promise (title) VALUES ('Lose weight');

INSERT INTO user_challenge (user_id, default_promise_id, description, start_date, days_number) VALUES (1,3,'I want to learn java & spring', '2022-07-03', 30);
INSERT INTO user_challenge (user_id, default_promise_id, description, start_date, days_number) VALUES (2,4,'I have to lose 20 kg', '2022-06-03', 60);
INSERT INTO user_challenge (user_id, default_promise_id, description, start_date, days_number) VALUES (3,3,'I want to read a book about front-end 50 pages a day', '2022-07-22', 30);
INSERT INTO user_challenge (user_id, default_promise_id, description, start_date, days_number) VALUES (4,2,'learn how to swim', '2022-07-01', 20);

