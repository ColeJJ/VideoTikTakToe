select PASSWORD from VTTT_USER where USERNAME='tounland';

create table VTTT_GROUP_USER (
    username VARCHAR2(255),
    groupname VARCHAR2(255)
)

Insert into VTTT_USERGROUP(id, groupname) value(1, 'User');
Insert into VTTT_GROUP_USER(Username, Groupname) Values(tounland, User);