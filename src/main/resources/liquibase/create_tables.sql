
CREATE TABLE "Order"
(
   OrderID int NOT NULL,
    OrderNumber int NOT NULL,
    PersonID BIGINT,
    PRIMARY KEY (OrderID),
    FOREIGN KEY (PersonID) REFERENCES USER (userId)
);

