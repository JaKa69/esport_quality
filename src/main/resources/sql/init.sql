CREATE TABLE EVENT(
                      ID INT,
                      NAME VARCHAR(255) ,
                      DTE_START DATE NOT NULL,
                      DTE_END DATE NOT NULL,
                      PRIMARY KEY(ID)
);

CREATE TABLE CUSTOMER(
                         ID INT,
                         FIRSTNAME VARCHAR(255) ,
                         NAME VARCHAR(255) ,
                         MAIL VARCHAR(320)  NOT NULL,
                         PRIMARY KEY(ID),
                         UNIQUE(MAIL)
);

CREATE TABLE TICKET(
                       ID INT,
                       ID_EVENT INT NOT NULL,
                       PRICE DECIMAL(15,2)  ,
                       IS_MULTIPASS BOOLEAN NOT NULL,
                       DTE_RESERVATION DATE NOT NULL,
                       NAME_USER VARCHAR(50) ,
                       ID_1 INT NOT NULL,
                       ID_2 INT NOT NULL,
                       PRIMARY KEY(ID),
                       FOREIGN KEY(ID_1) REFERENCES EVENT(ID),
                       FOREIGN KEY(ID_2) REFERENCES CUSTOMER(ID)
);
