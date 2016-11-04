CREATE DATABASE mediaCop;
use mediaCop;
create  table HashTag(id BIGINT UNSIGNED AUTO_INCREMENT ,name VARCHAR(30) NOT NULL , rank INTEGER , count BIGINT UNSIGNED DEFAULT 1 ,
priority FLOAT , country VARCHAR(100) , created BIGINT UNSIGNED NOT NULL  , lastUpdated BIGINT UNSIGNED NOT NULL  , PRIMARY KEY(id));
create table CountryHash(country VARCHAR(30) UNIQUE NOT NULL , hashtags BLOB , PRIMARY KEY (country));