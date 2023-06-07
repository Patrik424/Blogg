INSERT INTO users(password, FIRSTNAME, LASTNAME, username)
VALUES (1234,'Kalle ', 'Anka', 'fnatte'),
         (12345,'Musse ','Pigg' , 'knatte'),
         (12345,'stålmannen','Lane' , 'kryptonit'),
         (12345,'Musse ','Slö' , 'tjatte'),
         (12345,'Rosa ','Hulken' , 'hatten');
INSERT INTO author(BLOGGERNAME, FIRSTNAME, LASTNAME, password)
VALUES ('Tuffa Gudrun', 'Sven', 'Nilsson', 4325),
        ('Smala Gatan', 'Klas', 'Bengtsson', 4325),
        ('Fräcka Frida', 'Frida', 'Petterson', 4325);

INSERT INTO categories (name)
VALUES ('Books'),
       ('Electronics'),
       ('Clothing'),
       ('Furniture'),
       ('Toys');

INSERT INTO posts(title, content,postdate, AUTHOR_AUTHOR_ID, CATEGORY_CATEGORY_ID)
VALUES ('First Post', 'This is the content of the first post', '2023-07-13',1,2),
       ('Second Post', 'This is the content of the second post', '2023-08-04', 1,3),
       ('Third Post', 'This is the content of the third post', '2023-09-30', 1,1),
       ('Fourth Post', 'This is the content of the fourth post', '2023-10-25', 1,5),
       ('Fifth Post', 'This is the content of the fifth post', '2023-12-23', 1,3),
       ('Sixth Post', 'This is the content of the Sixth post', '2024-02-23', 1,4);


INSERT INTO comments (content, USER_USER_ID, POST_POST_ID)
VALUES
        ('This is a great post!',3,4),
        ('I totally agree with you!',3,2),
        ('Thanks for sharing!',1,5),
        ('I found this really helpful, thanks!',5,1),
        ('Great job on this post!',2,1);


CREATE TABLE IF NOT EXISTS post_category (
                                             id INT AUTO_INCREMENT PRIMARY KEY,
                                             post_id INT NOT NULL,
                                             category_id INT NOT NULL,
                                             CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts (post_id),
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories (category_id)
    );