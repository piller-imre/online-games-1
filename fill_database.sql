
-- user

INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('1', 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test@test.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('2', 'ed', '9C1623F0D38E28E9594F2EF31A7EC909291C4FDB05A777DCCD2E936A7F406011', 'ed@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('3', 'edd', 'C2B449CB522A1A684CC5F446CACE6944E2CD30D5CDC0D96C2E1C03443A81B432', 'edd@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('4', 'eddy', '10344FBB33CF720FE810D0735A7761A89C044B1E828504AE15D8750122EDC2C5', 'eddy@google.com');


-- game_type

INSERT INTO `online_games_db`.`game_type` (`id`, `name`) VALUES ('1', 'Amőba');
INSERT INTO `online_games_db`.`game_type` (`id`, `name`) VALUES ('2', 'Dáma');
INSERT INTO `online_games_db`.`game_type` (`id`, `name`) VALUES ('3', 'Malom');


-- game_type_options

INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('1', 'Véletlenszerű csapdák', 'A pályán véletleszerűen elhelyez 50 csapdát. Ha valaki rá akar tenni egy karaktert, a csapda aktiválódik, a karaktert nem helyezi el, és a következő játékos jön.', '1');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('2', 'Tiltott mezők', 'A pályán véletlenszerűen elhelyez 50 tiltott mezőt, amelyre nem lehet karaktert tenni.', '1');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('3', 'Kirakható csapdák', 'A játék kezdetekor mindkét játékos kap 10-10 csapdát, amelyet játék közben a saját körében kitehet a karaktere helyett. A karaktert utána egyik játékos sem látja.', '1');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('4', 'Eltűnő karakterek', 'A játék minden körben véletlenszerűen üresre állít egy mezőt, akár csapda, akár fal, akár karakter van rajta.', '1');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('5', 'Szabály1', 'Leírás1', '2');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('6', 'Szabály2', 'Leírás2', '2');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('7', 'Szabály3', 'Leírás3', '2');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('8', 'Szabály4', 'Leírás4', '3');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('9', 'Szabály5', 'Leírás5', '3');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('10', 'Szabály6', 'Leírás6', '3');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('11', 'Szabály7', 'Leírás7', '3');
INSERT INTO `online_games_db`.`game_type_option` (`id`, `name`, `description`, `game_type_fk`) VALUES ('12', 'Szabály8', 'Leírás8', '3');
