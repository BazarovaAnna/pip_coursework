alter table if exists characters drop constraint if exists FK27yx743bsnnsqplnjhk5yf224;
alter table if exists characters_abilities drop constraint if exists FK35j9mn5tihv3b9aarka58b3qr;
alter table if exists characters_abilities drop constraint if exists FKn3726ccgjmfsndxdrgkxgjewr;
alter table if exists characters_effects drop constraint if exists FKfuqkijbcvwayg8213v83if3ai;
alter table if exists characters_effects drop constraint if exists FK5ecc5hamsuvqw49fvelg5wyv0;
alter table if exists games drop constraint if exists FKfnb2pp2b4p361k65kaf7kig55;
alter table if exists games drop constraint if exists FKdce8igggv304msoc4n9viv3rs;
alter table if exists games drop constraint if exists FKbw641s5pl9hxqnu62k9o9usig;
alter table if exists games drop constraint if exists FK96mh9a45l0ttt9igu5lxel207;
alter table if exists groups drop constraint if exists FKq5odfls4c242crio7ewsbqyc0;
alter table if exists groups drop constraint if exists FKt7dq611j8xm0hdubflyar97op;
alter table if exists inventory drop constraint if exists FKem4n7umseo46fdpsowncsbwac;
alter table if exists inventory drop constraint if exists FK848kry9cxl63wcmg0kaauyb3u;
alter table if exists members drop constraint if exists FK5fv6ms2l7erm7783ttb8t1n1b;
alter table if exists members drop constraint if exists FKtmx1ugetxqx26fs8n6gscboou;
alter table if exists race drop constraint if exists FKq6ud9vs3e2yei7xsr5e4r9w03;
alter table if exists rules drop constraint if exists FKrfy1hyhwm7sgnhipgr6co6qj4;
alter table if exists user_role drop constraint if exists FKj345gk1bovqvfame88rcx7yyx;
alter table if exists users_genres drop constraint if exists FK6jttrkotguvhmy7p9xmotflqq;
alter table if exists users_genres drop constraint if exists FKqc52o4roeu9ub81u9xj35dfdc;

drop table if exists abilities cascade;
drop table if exists characters cascade;
drop table if exists characters_abilities cascade;
drop table if exists characters_effects cascade;
drop table if exists effects cascade;
drop table if exists games cascade;
drop table if exists genres cascade;
drop table if exists groups cascade;
drop table if exists inventory cascade;
drop table if exists items cascade;
drop table if exists members cascade;
drop table if exists race cascade;
drop table if exists rules cascade;
drop table if exists user_role cascade;
drop table if exists users cascade;
drop table if exists users_genres cascade;

create table abilities (
  id  bigserial not null,
  description varchar(255),
  name varchar(255) not null,
  perk_ability char(1) not null,
  primary key (id)
  );


create table characters (
  id  bigserial not null,
  condition varchar(40) not null,
  level int8 not null,
  max_weight float8 not null,
  name varchar(255) not null,
  pers_money float8 not null,
  sex char(1),
  story varchar(255),
  class varchar(255) not null,
  race_id int8,
  user_id int8,
  primary key (id));

create table characters_abilities (
  time_learning timestamp not null,
  character_id char(1) not null,
  ability_id int8 not null,
  primary key (ability_id, character_id)
  );

create table characters_effects (
  time_overlay timestamp not null,
  time_removal timestamp not null,
  character_id char(1) not null,
  effect_id int8 not null,
  primary key (character_id, effect_id));

create table effects (
  id  bigserial not null,
  description varchar(255),
  name varchar(255) not null,
  primary key (id));


create table games (
  id  bigserial not null,
  description varchar(255),
  name varchar(255),
  state varchar(255) not null,
  time_creating timestamp not null,
  time_deleting timestamp not null,
  date_end timestamp not null,
  date_start timestamp not null,
  gms_rating float4,
  genre_id int8,
  gm_id int8,
  rules_id int8,
  game_id int8,
  primary key (id));

create table genres (
  id  bigserial not null,
  name varchar(255) not null,
  primary key (id));

create table groups (
  character_id char(1) not null,
  game_id int8 not null,
  primary key (character_id, game_id));

create table inventory (
  time_getting timestamp not null,
  time_selling timestamp not null,
  character_id char(1) not null,
  item_id int8 not null,
  primary key (character_id, item_id));

create table items (
  id  bigserial not null,
  description varchar(255),
  name varchar(255) not null,
  price float8 not null,
  weight float8 not null,
  primary key (id));

create table members (
  characters_rating float4,
  character_id char(1) not null,
  session_id int8 not null,
  primary key (character_id, session_id));

create table rules (
  id  bigserial not null,
  description varchar(255) not null,
  title varchar(255) not null,
  creator_id int8, primary key (id));

create table race (
  id  bigserial not null,
  img_path varchar(255),
  sex char(1) not null,
  type varchar(255),
  primary key (id));


create table user_role (
  user_id int8 not null,
  roles varchar(255));


create table users (
  id  bigserial not null,
  activation_code varchar(255),
  active boolean,
  date_register timestamp not null,
  email varchar(255) not null,
  filename varchar(255),
  login varchar(255) not null,
  password varchar(40) not null,
  sex char(1) not null,
  primary key (id));

create table users_genres (
  user_id int8 not null,
  genre_id int8 not null,
  primary key (genre_id, user_id));

alter table if exists abilities add constraint UK_dqjptw1ejm12s8ahgt9vxptpj unique (name);
alter table if exists characters add constraint UK_r3m7dafrtn7gkievcf8fw7nt5 unique (name);
alter table if exists effects add constraint UK_fkfveuwhd2fovgg6fweb03pym unique (name);
alter table if exists genres add constraint UK_pe1a9woik1k97l87cieguyhh4 unique (name);
alter table if exists items add constraint UK_mnhl79u3u6jdvutuoeq54stne unique (name);
alter table if exists rules add constraint UK_ijbudtpsxby634taeqpl8fngd unique (description);
alter table if exists rules add constraint UK_5ajydv93eveurhwiye50g5tpp unique (title);
alter table if exists users add constraint UK_ow0gan20590jrb00upg3va2fn unique (login);

alter table if exists characters add constraint FK27yx743bsnnsqplnjhk5yf224 foreign key (user_id) references users;
alter table if exists characters_abilities add constraint FK35j9mn5tihv3b9aarka58b3qr foreign key (ability_id) references abilities;
alter table if exists characters_effects add constraint FKfuqkijbcvwayg8213v83if3ai foreign key (effect_id) references effects;
alter table if exists games add constraint FKfnb2pp2b4p361k65kaf7kig55 foreign key (genre_id) references genres;
alter table if exists games add constraint FKdce8igggv304msoc4n9viv3rs foreign key (gm_id) references users;
alter table if exists games add constraint FKbw641s5pl9hxqnu62k9o9usig foreign key (rules_id) references rules;
alter table if exists games add constraint FK96mh9a45l0ttt9igu5lxel207 foreign key (game_id) references games;
alter table if exists groups add constraint FKq5odfls4c242crio7ewsbqyc0 foreign key (game_id) references games;

alter table if exists inventory add constraint FKem4n7umseo46fdpsowncsbwac foreign key (item_id) references items;
alter table if exists members add constraint FK5fv6ms2l7erm7783ttb8t1n1b foreign key (session_id) references games;
alter table if exists rules add constraint FKrfy1hyhwm7sgnhipgr6co6qj4 foreign key (creator_id) references users;
alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users;
alter table if exists users_genres add constraint FK6jttrkotguvhmy7p9xmotflqq foreign key (user_id) references users;
alter table if exists users_genres add constraint FKqc52o4roeu9ub81u9xj35dfdc foreign key (genre_id) references genres;
