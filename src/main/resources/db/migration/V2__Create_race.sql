insert into race (id, img_path, sex, type)
  values (0, '../../resources/default/img/race/лучник_орк.jpg','m','орк');

insert into race (id, img_path, sex, type)
  values (1, '../../resources/default/img/race/орк.png','m','орк');

insert into race (id, img_path, sex, type)
  values (2, '../../resources/default/img/race/черный_орк.jpg','m','орк');

insert into race (id, img_path, sex, type)
  values (3, '../../resources/default/img/race/женщина_воин_орк.jpg','f','орк');

insert into race (id, img_path, sex, type)
  values (4, '../../resources/default/img/race/ангрим_железный_кулак_гном.jpg','m','гном');

insert into race (id, img_path, sex, type)
  values (5, '../../resources/default/img/race/воин_гном.jpg','m','гном');

insert into race (id, img_path, sex, type)
  values (6, '../../resources/default/img/race/молот_гном.jpg','m','гном');

insert into race (id, img_path, sex, type)
  values (7, '../../resources/default/img/race/молот_шит_гном.jpg','m','гном');

insert into race (id, img_path, sex, type)
  values (8, '../../resources/default/img/race/женщина_воин_человек.jpg','f','человек');

insert into race (id, img_path, sex, type)
  values (9, '../../resources/default/img/race/маг_огня_человек.jpg','m','человек');

insert into race (id, img_path, sex, type)
  values (10, '../../resources/default/img/race/женщина_воин_эльф.jpg','f','эльф');

insert into race (id, img_path, sex, type)
  values (11, '../../resources/default/img/race/белый_лев_эльф.jpg','m','эльф');

insert into race (id, img_path, sex, type)
  values (12, '../../resources/default/img/race/женщина_маг_темный эльф.jpg','f','эльф');

insert into race (id, img_path, sex, type)
  values (13, '../../resources/default/img/race/маг_хаоса_нежить.jpg','m','нежить');

insert into race (id, img_path, sex, type)
  values (14, '../../resources/default/img/race/маг_энергия_нежить.jpg','m','нежить');



insert into items (id, description, name, price, weight)
  values (1, '0 price and weight', 'test0', 0, 1);

insert into items (id, description, name, price, weight)
  values (2, 'low price and weight', 'test1', 1, 1);

insert into items (id, description, name, price, weight)
  values (3, 'lower price and weight', 'test2', 0.01, 1);

insert into items (id, description, name, price, weight)
  values (4, 'makes poison from tea', 'wizards spoon', 30, 1);

insert into items (id, description, name, price, weight)
  values (5, 'distracting your attention by meow meow', 'several kittens', 10, 5);

insert into items (id, description, name, price, weight)
  values (6, 'your granny made by yourself with love', 'new socks', 999, 1);

insert into items (id, description, name, price, weight)
  values (7, 'making relations disappear, breaks hearts', 'no sql database', 100, 9999);




insert into abilities (id, description, name, perk_ability)
  values (1, 'making laugh everybody', 'cool jokes', 'a');

insert into abilities (id, description, name, perk_ability)
  values (2, 'very delicious', 'make sandwich', 'a');

insert into abilities (id, description, name, perk_ability)
  values (3, 'awful noise, everyone is praying', 'do growl while sleeping', 'p');




insert into effects (id, description, name)
  values (1, 'flower plume chases after you', 'flower perfume');

insert into effects (id, description, name)
  values (2, 'adds 1 to magic ball number', 'lucky guy!');

insert into effects (id, description, name)
  values (3, 'minimum damage from arrows', 'thick skinned');



insert into inventory (item_id, character_id, Time_Getting)
  values (1, 1, '2004-10-19 10:23:54');

insert into characters_abilities (ability_id, character_id, Time_Learning)
  values (1, 1, '2004-10-19 10:23:54');

insert into characters_effects (effect_id, character_id, Time_Overlay)
  values (1, 1, '2004-10-19 10:23:54');