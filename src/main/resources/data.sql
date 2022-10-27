INSERT INTO users (name, email, password, userName, lastName, street, houseN, city, zip, rePassword, passwordsEqual)
VALUES ('Buffy', 'siezeTheDay@blabla.com', 'Ih8vamps', 'Slayer', 'Summers', 'Evergreen Terrace', 6, 'Sunnydale','90210', 'Ih8vamps', true);

INSERT INTO users (name, email, password, userName, lastName, street, houseN, city, zip, rePassword, passwordsEqual)
VALUES ('Homer', 'yayDonuts@blabla.com', 'mmmmmmmm', 'DohMan', 'Simpson', 'Forest lane', 28, 'Springfield','90210', 'Ih8vamps', false);

INSERT INTO users (name, email, password, userName, lastName, street, houseN, city, zip, rePassword, passwordsEqual)
VALUES ('Joe', 'JoeBlow@blabla.com', 'bladibla', 'JoeBlow', 'Smith', 'Main St', 6, 'Detroit','90210', 'Ih8vamps', true);

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (1, 'Why Vampires Are The Worst', 1, 'Bla bla blitty blop bla. Bloo bloo bla bla blitty bloo.', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (2, 'The 10 best donut flavors', 2, 'Any donut is a good donut.', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (3, 'Lorem Ipsum', 3, 'Her name was Lola, She was a showgirl', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (4, 'Why Vampires Are The Worst pt2', 1, 'Bla bla blitty blop bla. Bloo bloo bla bla blitty bloo.', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (5, 'Things that make me go doh!', 2, 'everything.', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (6, 'Schlemiel schlemazel', 3, 'Hazenpepper incorporate.', current_timestamp() );

INSERT INTO blogpost (id, title, user_id, blogBody, timeOfPost)
VALUES (7, 'testing a seventh post', 1, 'Does this show up? Testing....', current_timestamp() );

INSERT INTO comments (id, comment, commentCreatedTime, commentAuthor_id, post_id)
VALUES (1, 'this person is right', current_timestamp(), 3, 1 );

INSERT INTO comments (id, comment, commentCreatedTime, commentAuthor_id, post_id)
VALUES (2, 'this person is wrong', current_timestamp(), 2, 1 );

INSERT INTO comments (id, comment, commentCreatedTime, commentAuthor_id, post_id)
VALUES (3, 'you are stupid', current_timestamp(),  3, 1 );

INSERT INTO comments (id, comment, commentCreatedTime, commentAuthor_id, post_id)
VALUES (4, 'your face is stupid', current_timestamp(),  2, 1 );