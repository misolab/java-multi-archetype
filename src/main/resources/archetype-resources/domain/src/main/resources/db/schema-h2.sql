DROP TABLE TB_MEMBER IF EXISTS;

create table TB_MEMBER (
    ID bigint generated by default as identity,
    USERNAME varchar(255) not null,
    X_TOKEN varchar(255),    
    ROLES varchar(255),
    ALLOW_IP varchar(255),
    ERROR_COUNT integer default 0,
    primary key (ID)
);
create index IDX_USERNAME on TB_MEMBER (USERNAME);