DROP DATABASE  IF EXISTS `spring_security_lms`;

CREATE DATABASE  IF NOT EXISTS `spring_security_lms`;
USE `spring_security_lms`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `username` varchar(50) NOT NULL,
                         `password` varchar(50) NOT NULL,
                         `enabled` tinyint(1) NOT NULL,
                         PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `users`
--

INSERT INTO `users`
VALUES
    ('Morrigan Aensland','{noop}secret',1),
    ('Kula Diamond','{noop}secret',1),
    ('Ahri','{noop}secret',1);

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
                               `username` varchar(50) NOT NULL,
                               `authority` varchar(50) NOT NULL,
                               UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
                               CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `authorities`
--

INSERT INTO `authorities`
VALUES
    ('Ahri','ROLE_USER'),
    ('Kula Diamond','ROLE_USER'),
    ('Kula Diamond','ROLE_STAFF'),
    ('Morrigan Aensland','ROLE_USER'),
    ('Morrigan Aensland','ROLE_STAFF'),
    ('Morrigan Aensland','ROLE_ADMIN');

