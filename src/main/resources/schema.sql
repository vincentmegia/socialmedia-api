CREATE TABLE Users(
    id INT IDENTITY PRIMARY KEY,
    lastname VARCHAR (255) NOT NULL,
    middlename VARCHAR (255) NULL,
    firstname VARCHAR (255) NOT NULL,
    username VARCHAR (255) NOT NULL,
    password VARCHAR (255) NOT NULL
);

CREATE TABLE Posts(
    id INT IDENTITY PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);
