CREATE TABLE ITEM (
    ID NUMBER,
    CONSTRAINT ITEM_PK PRIMARY KEY(ID),
    NAME NVARCHAR2(100),
    DATE_CREATED TIMESTAMP,
    LAST_UPDATED_DATE TIMESTAMP,
    DESCRIPTION NVARCHAR2(200)
);