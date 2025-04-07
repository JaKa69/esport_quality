CREATE TABLE EVENT (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    DTE_START DATE NOT NULL,
    DTE_END DATE NOT NULL
);

CREATE TABLE CUSTOMER (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    FIRSTNAME VARCHAR(100) NOT NULL,
    MAIL VARCHAR(150) NOT NULL
);

CREATE TABLE TICKET (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    ID_EVENT INT,
    PRICE DECIMAL(10, 2) NOT NULL,
    IS_MULTIPASS BOOLEAN NOT NULL,
    DTE_RESERVATION DATE NOT NULL,
    FOREIGN KEY (ID_EVENT) REFERENCES EVENT(ID)
);

CREATE TABLE CUSTOMER_TICKET (
    ID_CUSTOMER INT,
    ID_TICKET INT,
    PRIMARY KEY (ID_CUSTOMER, ID_TICKET),
    FOREIGN KEY (ID_CUSTOMER) REFERENCES CUSTOMER(ID),
    FOREIGN KEY (ID_TICKET) REFERENCES TICKET(ID)
);
