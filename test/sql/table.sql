CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `foreName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(240) DEFAULT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;
