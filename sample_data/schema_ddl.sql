DROP TABLE IF EXISTS public.game_state;
CREATE TABLE public.game_state (
    id serial NOT NULL PRIMARY KEY,
    text text NOT NULL,
    current_map text NOT NULL,
    saved_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    player_id integer NOT NULL
);

DROP TABLE IF EXISTS public.player;
CREATE TABLE public.player (
    id serial NOT NULL PRIMARY KEY,
    player_name text NOT NULL,
    health integer NOT NULL,
    attack_strength integer NOT NULL,
    x integer NOT NULL,
    y integer NOT NULL
);

ALTER TABLE ONLY public.game_state
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);

--Additions

DROP TABLE IF EXISTS public.inventory;
CREATE TABLE public.inventory (
      player_id integer NOT NULL,
      item_id integer NOT NULL
);

DROP TABLE IF EXISTS public.items;
CREATE TABLE public.items (
      id serial NOT NULL PRIMARY KEY,
      name text NOT NULL
);

ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES public.player(id);
ALTER TABLE ONLY public.inventory
    ADD CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES public.items(id);


DROP TABLE IF EXISTS public.game_state_map;
CREATE TABLE public.game_state_map (
   map_id integer NOT NULL,
   game_state_id integer NOT NULL
);

DROP TABLE IF EXISTS public.maps;
CREATE TABLE public.maps (
     id serial NOT NULL PRIMARY KEY,
     level integer NOT NULL,
     width integer NOT NULL,
     height integer NOT NULL,
     map_string text NOT NULL
);


ALTER TABLE ONLY public.game_state_map
    ADD CONSTRAINT fk_map_id FOREIGN KEY (map_id) REFERENCES public.maps(id);
ALTER TABLE ONLY public.game_state_map
    ADD CONSTRAINT fk_game_state_id FOREIGN KEY (game_state_id) REFERENCES public.game_state(id);


INSERT INTO maps(level, width, height, map_string) VALUES (1, 44, 44, '44 44
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------#############-----------------------
-----------#...........#    ########-----------
-----------#...s...s...######.t....#-----------
-----------#.......................#-----------
-----------#...........######......#-----------
-----------#.....s.....#    #...g..#-----------
-----------#...........#    #......#-----------
-----------#.M.........#    #......#-----------
------------####..######     ##..###-----------
---------------#..#           #..#-------------
---------------#..#           #..#-------------
---------------#..#          ##..##------------
-------------###..####      #......#-----------
-------------#..@....#      git #...k..#-----------
-------------#.......#      #......#-----------
-------------#....G..#      #......#-----------
-------------#.......#      #......#-----------
-------------#....g..#      #......1-----------
-------------#.......#      #......#-----------
-------------#########       ######------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------
-----------------------------------------------');

INSERT INTO maps(level, width, height, map_string) VALUES (2, 44, 44, '44 44
LlLlLlllWWlLllLlLlLlLlLlL%/WWWWWWWWWWWWlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL
lLlLlLl%WWW%/=/%//%/%/%WWWWWWWWWWWWWWWW=/%LlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
LlLlLlL/%WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW%lLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
lLlLlLlL/%/WWWWWWWWWWWWWWWWWWWWWWWWWWWWWW/lLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
LlLlLlLlLl%//%/%/%/%/%/%/=/WWWWWWWWWWWW/%/LlLlLlLlLlLlLlLlLlLlxxLlLlLlLlLlLlLlLlL
lLlLlLlLlLlLlLlLlLlLlLl%/%/%/%WWWWWWWW%/%lLlLlLlLlLlLlLlLlLlLlLlxx=lLlLlLlLlLlLlLlL
LlLlLlLlLlLlLlLlLlLlLlLlLlLlL%/%WWWW=/lLlLlLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl/WW%/LlLlLlLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl/WW/=lLlLlLlLlLlLlLlLlLlLlLlLlLlxxLlLlLlLlLlLlLlLlL
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%WW/LlLlLlLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlLlLl
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl/%WW%lLlLlLlLlLlLlLlLlLlLlLlLlLlgxxxxxxxxxxxxxxxxxl
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%/WW/%lLlLlLlLlLlLlLlLlLl/%xxxxxxxxxxxxxxxxxxxxxxl
LlLlLlLlLlLlLlLl/%/xx=gsxxxxxxxx/nmmv%xxxx=xxxxxxGxxxxxxxx//%=%/%/lLlLlLlLlLlLlLl
lLlLlLl%/xx=xxxxxxxxxxxxx=xxxxxnmmv/%xxxxxxxxxxxxxx=xxx%/%LlLl/%LlLlLlLlLlLlLlLlL
l@xxxxxxxxxxx/%/xxxlLlLlLlLlLlL/G/WWW/%LlLlLlLlLlLlLlLlLlLlLlLlLl%/LlLlLlLlLlLlLlLlL
lx=xxx/%lLlLlL/LlLlLlLlLlLlLlLl%/%WWW/lLlLlLlLlLlLlLlLlLlLlLlLlL/%lLlLlLlLlLlLlLlLl
%/LlLlLlLlLlLl%lLlLlLlLlLlLlLlLl%/%WW%LlLlLlLlLlLlLlLlLlLlLlLlLl%/LlLlLlLlLlLlLlLlL
lLlLlLlLlLlLlL/LlLlLlLlLlLlLlL%//WW/lLlLlLlLlLlLlLlLlLlLlLlLlL/%lLlLlLlLlLlLlLlLl
LlLlLlLlLlLlLl%/LlLlLlLlLlLlLlL%%/WW%LlLlLlLlLlLlLlLlLlLlLlLlLl%/LlLlLlLlLlLlLlLlL
lLlLlLlLlLlLlLl%/LlLlLlLlLlLlLlL%%/WW/lLlLlLlLlLlLlLlLlLlLlLlLlL/%lLlLlLlLlLlLlLlLl
LlLlL%/%LlLlLlLl%/%lLlLlLlLlLlLlLl%WW%LlLlLlLlLlLlLlLlLlLlLlLlLl%/LlLlLlLlLlLlLlLlL
lLlL%/%/%LlLlLlLlL/%/LlLlLlLlLlL%%/WW/lLlLlLlLlLlLlLlLlLlLlLlLlL/%lLlLlLlLlLlLlLlLl
LlL%/%N/%=LlLlLlLlLl%/LlLlLlLlLlLG%WWLlLlLlLlLlLlLlLlLlLlLlLlL//LlLlLlLlLlLlLlLlL
lLl%/%g/%/lLlLlLlLlL%/LlLlLlLLlLl%/WWW/lLlLlLlLlLlLlLlLlLlLlLNxxx/LlLlLlLlLlLlLlLl
LlLl%/%/%/%/LlLlLlLl%/LlLlLlLlLlLl%WWLlLlLlLlLlLlLlLlLlLlL/%/%xxx%/%lLlLlLlLlLlLlL
lLlLlL%/%/%/%/s/%/%/%LlLlLlLlLlLlL%/WW/lLlLlLlLlLlLlLlLlLl=/%Dxxxxx/%/%/%/LlLlLlLlLl
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%%WW%LlLlLlLlLlLlLlLlLl%/%/%/%xxxE/%/%/%/WWLlLlLlL
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%%/WWW/lLlLlLlLlLlLlLlLlL/G/%/%/xxx/%/%/%/WWWWLlLlLl
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%%WW%LlLlLlLlLlLlLlLlLl%/%/%/%xxxF/%/%/%WWWWlLlLlL
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%%/WW/lLlLlLlLlLlLlLlLlLl=/%/%/xxxxxxxA/%WWlLlLlLl
LlLlL%/%/%/%LlLlLlLlLlLlLlLlLlLlL%%WW%LlLlLlLlLlLlLlLlLlLl%/%/%Sxxxx%/%/%lLlLlLlLlL
lLlL%xxy!yxxxxlLlLlLlLlLlLlLlLl%%/WWW/lLlLlLlLlLlLlLlLlLlLlLl%/%xxxx/%/LlLlLlLlLlLl
LlLl/yyyyyyxxxlLlLlLlLlLlLlLl%%%/WWWW%lLlLlLlLlLlLlLlLlLlLlLlLlL%/xxxLlLlLlLlLlLlLl
lLl/x!ycccyyxx=LlLlLlLlLl=%/%/%//WW/LlLlLlLlLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlL
LlL%xxycCyxxxxxG/%/%/%/%/%/%Ll/%WW%lLlLlLlLlLlLlLlLlLlLlLlLlLlLlxxLlLlLlLlLlLlLl
lLl/!xycccyyxxs=%/%/%//%/lLlLlL%/WWW/LlLlLlLlLlLlLlLlLlLlLlLlLlLlLxxlLlLlLlLlLlLlL
LlLlxxyyyyyyxxxlLlLlLlLlLlLlLlLlL/%WW%LlLlLlLlLlLlLlLlLlLlLlLlLlLlLxxxlLlLlLlLlLlLl
lLlLxxyx!yxxxLlLlLlLlLlLlLlLlLlL%/WWW/LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlxxxxLlLlLlLlLlL
LlLl=!xxxxxx=lLlLlLlLlLlLlLlLlLlL%WWW%LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlxxxxxxxxxxl
lLlL%/%/%!%/lLlLlLlLlLlLlLlLlLlLl/WWW/lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLxxxxxxxxxxl
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL%WW/%LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL/WW%LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl%WW/lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL
LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL/WW%LlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLl
lLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLWWLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlLlL');
