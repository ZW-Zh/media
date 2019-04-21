DROP database if EXISTS media;
CREATE DATABASE media DEFAULT CHARSET = UTF8;
USE media;

CREATE TABLE user (
    id int primary key auto_increment,
    name VARCHAR (20) NOT NULL UNIQUE ,
    pwd VARCHAR (20) NOT NULL
)engine=innodb DEFAULT charset=utf8;
CREATE TABLE musicType(
  id int primary key auto_increment,
  name VARCHAR(10) NOT NULL
)engine=innodb DEFAULT charset=utf8;

CREATE TABLE music(
    id int primary key auto_increment,
    path VARCHAR (100) NOT NULL UNIQUE ,
    lrcpath VARCHAR (100),
    title VARCHAR(100) NOT NULL,
    artist VARCHAR(20) NOT NULL ,
    album VARCHAR(100) NOT NULL ,
    length INT NOT NULL ,
    albumBip VARCHAR(100),
    type int,
    FOREIGN KEY(type) REFERENCES musicType(id)
)engine=innodb DEFAULT charset=utf8;

CREATE TABLE video(
    id int primary key auto_increment,
    path VARCHAR (100) NOT NULL UNIQUE ,
    title VARCHAR(100) NOT NULL,
    time BIGINT NOT NULL
)engine=innodb DEFAULT charset=utf8;


CREATE TABLE LIKEMUSIC(
  id int primary key auto_increment,
  userid int NOT NULL ,
  musicid int NOT NULL ,
  FOREIGN KEY(userid) REFERENCES user(id),
  FOREIGN KEY (musicid) REFERENCES music(id)
)engine=innodb DEFAULT charset=utf8;

insert into musicType(name) values ('摇滚');
insert into musicType(name) values ('古典');
insert into musicType(name) values ('流行');
insert into musicType(name) values ('迷幻');


