INSERT INTO Type
    (name, id)
VALUES
    ('Programming Courses', 1),
    ('Community Forums', 2),
    ('Computer science', 3);

INSERT INTO Bookmark
(bookmark_name, description, url, id, type_id)
VALUES
('Udemy', 'Place to buy online courses for all things related to software', 'http://wwww.udemy.com', 4, 1 ),
('Stack OverFlow', 'Place to get help on coding problems', 'http://wwww.stackoverflow.com', 4, 2 ),
('MIT Opencourseware', 'Place to learn computer science modules from MIT', 'http://wwww.ocw.mit.edu', 4, 3 );