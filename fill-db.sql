
-- user

INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('1', 'test', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test@test.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('2', 'ed', '9C1623F0D38E28E9594F2EF31A7EC909291C4FDB05A777DCCD2E936A7F406011', 'ed@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('3', 'edd', 'C2B449CB522A1A684CC5F446CACE6944E2CD30D5CDC0D96C2E1C03443A81B432', 'edd@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('4', 'eddy', '10344FBB33CF720FE810D0735A7761A89C044B1E828504AE15D8750122EDC2C5', 'eddy@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('5', 'álmos', '42084CD9EEDE48CA0CD7D5699589B11AF07BBB3199F2B039CBD3C2A789EC0217', 'almos@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('6', 'előd', '1FC79F5B2112A1D9431655AE23238DAE35DD024F2ADF19E8785BC67E37A9F426', 'elod@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('7', 'ond', '022063E225AEAB453825AD60BB5164A3E42870514131023F0FEF671EC6DDA474', 'ond@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('8', 'kond', 'C38BB47B171341079430DAD07FDC5795140ADEBC54ABEA86587FDDB621E4F06E', 'kond@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('9', 'tas', 'AE22FC6EA93F9534EDF63E0D24BF1CCB6DDD875E9903DBDC0F29BC0D53B516F7', 'tas@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('10', 'huba', '529C0528F4BE79C126A7FBE23F36837DE6F9AA1160E1483A48BB1B41FF11CF06', 'huba@google.com');
INSERT INTO `online_games_db`.`user` (`id`, `username`, `password`, `email`) VALUES ('11', 'töhötöm', '1A2ACF48105E7ED5575391F9A2FDECAF1C56464DB591BDC88F0B982B1F6C894A', 'tohotom@google.com');


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


-- match_waiting

INSERT INTO `online_games_db`.`match_waiting` (`id`, `player_fk`, `game_type_fk`, `options`) VALUES ('1', '5', '2', '[5]');
INSERT INTO `online_games_db`.`match_waiting` (`id`, `player_fk`, `game_type_fk`, `options`) VALUES ('2', '6', '2', '[6,7]');
INSERT INTO `online_games_db`.`match_waiting` (`id`, `player_fk`, `game_type_fk`, `options`) VALUES ('3', '7', '3', '[8]');
INSERT INTO `online_games_db`.`match_waiting` (`id`, `player_fk`, `game_type_fk`, `options`) VALUES ('4', '8', '3', '[9,10]');
INSERT INTO `online_games_db`.`match_waiting` (`id`, `player_fk`, `game_type_fk`, `options`) VALUES ('5', '9', '1', '[4]');


-- match_active

INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('1', '1', '', '1', '[1,2]');
INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('2', '1', '', '1', '[2,1]');
-- INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('3', '1', '', '1', '[3,4]');
-- INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('4', '2', '', '1', '[1]');
-- INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('5', '2', '', '1', '[]');
-- INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('6', '3', '', '1', '[1,2]');
-- INSERT INTO `online_games_db`.`match_active` (`id`, `game_type_fk`, `board_state`, `turn`, `options`) VALUES ('7', '1', '', '1', '[1,2]');
-- more user needed to insert these lines, don' extend match_players tables if applied!


-- match_players

INSERT INTO `online_games_db`.`match_players` (`player1_fk`, `player2_fk`, `active_player`, `match_fk`) VALUES ('2', '1', '1', '1');
INSERT INTO `online_games_db`.`match_players` (`player1_fk`, `player2_fk`, `active_player`, `match_fk`) VALUES ('3', '4', '1', '2');



