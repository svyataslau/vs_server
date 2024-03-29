CREATE TABLE user_profile
(
    id       SERIAL,
    nickname VARCHAR(128) UNIQUE,
    email    VARCHAR(128) UNIQUE,
    password VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE promise
(
    id    SERIAL,
    title VARCHAR(128),
    PRIMARY KEY (id)
);

CREATE TABLE user_challenge
(
    id          SERIAL,
    user_id     INTEGER REFERENCES user_profile (id) ON DELETE CASCADE,
    promise_id  INTEGER REFERENCES promise (id) ON DELETE CASCADE,
    start_date  TIMESTAMP NOT NULL,
    days_number INTEGER   NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE reason
(
    id                SERIAL,
    user_challenge_id INTEGER REFERENCES user_challenge (id) ON DELETE CASCADE,
    description       VARCHAR(2048),
    PRIMARY KEY (id)
);

INSERT INTO user_profile (nickname, email, password)
VALUES ('nikky34', 'some432@gmail.com', 'noteToday23');
INSERT INTO user_profile (nickname, email, password)
VALUES ('mortyRick', 'letsgo763@gmail.com', 'somePass32');
INSERT INTO user_profile (nickname, email, password)
VALUES ('locky89', 'manhello543@gmail.com', 'helpMe43');
INSERT INTO user_profile (nickname, email, password)
VALUES ('heyheyhey666', 'dude345@gmail.com', 'frontEnd666');

INSERT INTO promise (title)
VALUES ('Stop addiction');
INSERT INTO promise (title)
VALUES ('Get a new ability');
INSERT INTO promise (title)
VALUES ('Learn new technology');
INSERT INTO promise (title)
VALUES ('Lose weight');

INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (2, 3, '2022-08-25T21:08:38.661Z', 1);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (2, 4, '2022-08-25T21:08:57.406Z', 3);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (2, 3, '2022-08-25T21:09:15.610Z', 4);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (2, 2, '2022-08-25T21:09:30.855Z', 3);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (1, 3, '2022-08-25T21:09:48.235Z', 3);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (3, 4, '2022-08-25T21:10:03.993Z', 4);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (3, 3, '2022-08-25T21:10:20.802Z', 7);
INSERT INTO user_challenge (user_id, promise_id, start_date, days_number)
VALUES (4, 2, '2022-08-25T21:10:35.677Z', 5);

INSERT INTO reason (user_challenge_id, description)
VALUES (1,
        'I want to learn java & spring. I want to become a full stack programmer, so I need to put in a lot of effort.');
INSERT INTO reason (user_challenge_id, description)
VALUES (2,
        'I have to lose 20 kg. I will run 10km every day, do 100 push-ups and eat 5 small meals a day. I want to become healthy, I''m tired of lying on the couch.');
INSERT INTO reason (user_challenge_id, description)
VALUES (3, 'I want to read a book about front-end 50 pages a day.
I have an interview soon and I want to show myself well.');
INSERT INTO reason (user_challenge_id, description)
VALUES (4, '
Learning to swim has always been my dream. Therefore, I will go to the pool 4 times a week.');
INSERT INTO reason (user_challenge_id, description)
VALUES (5, 'I want to learn js. This may come in handy in my professional life.');
INSERT INTO reason (user_challenge_id, description)
VALUES (6,
        'I have to lose 99 kg. The doctor said that with my obesity, people do not live long, so I refuse burgers and fatty foods.');
INSERT INTO reason (user_challenge_id, description)
VALUES (7, 'I want to paint something.
Mom has a birthday soon, I want to give her a nice gift.');
INSERT INTO reason (user_challenge_id, description)
VALUES (8,
        'Learn how to run. I spent a long time in the hospital and lost my athletic form. I promise myself to run every day');
