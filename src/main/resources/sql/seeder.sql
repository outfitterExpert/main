USE outfitter_db;

# TRUNCATE TABLE users;

INSERT INTO users (username, password, first_name, last_name, email, outfitter)
VALUES  ('austin_user', 'password1', 'Austin', 'Whitley', 'admin1@email.com', true),
        ('naysa_user', 'password1', 'Naysa', 'Moreno', 'admin1@email.com', true),
        ('irvin_user', 'password1', 'Irvin', 'Buendia', 'admin1@email.com', true),
        ('benny_user', 'password1', 'Benny', 'Alvarez', 'admin1@email.com', true);

INSERT INTO animals (id, name)
VALUES (1, 'Bear'),
       (2, 'Black Bear'),
       (3, 'Brown Bear'),
       (4, 'Grizzly Bear'),
       (5, 'Polar Bear'),
       (6, 'Bison'),
       (7, 'Wood Bison'),
       (8, 'Caribou'),
       (9, 'Barren Ground Caribou'),
       (10, 'Dolphin Caribou'),
       (11, 'Union Caribou'),
       (12, 'Woodland Caribou'),
       (13, 'Mountain Lion'),
       (14, 'Deer'),
       (15, 'Axis Deer'),
       (16, 'Black-tailed Deer'),
       (17, 'Chital'),
       (18, 'Columbian Black-tailed Deer'),
       (19, 'Mule Deer'),
       (20, 'White-tailed Deer'),
       (21, 'Elk'),
       (22, 'Rocky Mountain Elk'),
       (23, 'Tule Elk'),
       (24, 'Gemsbok'),
       (25, 'Goat'),
       (26, 'Bezoar goat'),
       (27, 'Ibex'),
       (28, 'Mountain goat'),
       (29, 'Rocky Mountain goat'),
       (30, 'Moose'),
       (31, 'Shiras Moose'),
       (32, 'Muskox'),
       (33, 'Pronghorn'),
       (34, 'Sheep'),
       (35, 'Barbary Sheep'),
       (36, 'Bighorn Sheep'),
       (37, 'California Bighorn Sheep'),
       (38, 'Dall''s Sheep'),
       (39, 'Desert Bighorn Sheep'),
       (40, 'Lanai Mouflon Sheep'),
       (41, 'Nelson Bighorn Sheep'),
       (42, 'Rocky Mountain Bighorn Sheep'),
       (43, 'Stone Sheep'),
       (44, 'Thinhorn Mountain Sheep'),
       (45, 'Armadillo'),
       (46, 'Badger'),
       (47, 'Beaver'),
       (48, 'Bobcat'),
       (49, 'North American Civet Cat'),
       (50, 'Ring-tailed Cat'),
       (51, 'Spotted Skunk'),
       (52, 'Cayote'),
       (53, 'Ferret'),
       (54, 'Feral ferret'),
       (55, 'Fisher'),
       (56, 'Fox'),
       (57, 'Artic Fox'),
       (58, 'gray fox'),
       (59, 'red fox'),
       (60, 'swift fox'),
       (61, 'Lynx'),
       (62, 'Marmot'),
       (63, 'Alaska marmot'),
       (64, 'groundhog'),
       (65, 'hoary marmot'),
       (66, 'woodchuck'),
       (67, 'Marten'),
       (68, 'American marten'),
       (69, 'pine marten'),
       (70, 'Mink'),
       (71, 'Mole'),
       (72, 'Mouse'),
       (73, 'Muskrat'),
       (74, 'Nutria'),
       (75, 'Opossum'),
       (76, 'Pig'),
       (77, 'feral swine'),
       (78, 'javelina'),
       (79, 'wild boar'),
       (80, 'wild hogs'),
       (81, 'wild pigs'),
       (82, 'Pika'),
       (83, 'Porcupine'),
       (84, 'Prairie Dog'),
       (85, 'Black-tailed Prairie Dogs'),
       (86, 'White-tailed Prairie Dogs'),
       (87, 'Rabbit'),
       (88, 'Hare'),
       (89, 'Artic Hare'),
       (90, 'Black-tailed Jackrabbit'),
       (91, 'Cottontail Rabbit'),
       (92, 'Belgian Hare'),
       (93, 'European Hare'),
       (94, 'Snowshoe Hare'),
       (95, 'Swamp Rabbit'),
       (96, 'Varying Hare'),
       (97, 'White-tailed Jackrabbit'),
       (98, 'Raccoon'),
       (99, 'Rat'),
       (100, 'Kangaroo Rat'),
       (101, 'Wood Rat'),
       (102, 'Shrew'),
       (103, 'Skunk'),
       (104, 'Striped Skunk'),
       (105, 'Spotted Skunk'),
       (106, 'Squirrel'),
       (107, 'Abert''s Squirrel'),
       (108, 'Black Squirrel'),
       (109, 'Columbian Ground Squirrel'),
       (110, 'Gray Squirrel'),
       (111, 'Flying Squirrel'),
       (112, 'Fox Squirrel'),
       (113, 'Ground Squirrel'),
       (114, 'Pine Squirrel'),
       (115, 'Red Squirrel'),
       (116, 'Richardson''s Ground Squirrel'),
       (117, 'Tree Squirrel'),
       (118, 'Wyoming Ground Squirrel'),
       (119, 'Vole'),
       (120, 'Weasel'),
       (121, 'least weasel'),
       (122, 'long-tailed weasel'),
       (123, 'wolf'),
       (124, 'gray wolf'),
       (125, 'wolverine'),
       (126, 'Chachalaca'),
       (127, 'Chukar'),
       (128, 'Crow'),
       (129, 'Dove'),
       (130, 'Pigeon'),
       (131, 'Barred Dove'),
       (132, 'Eurasian Collared Dove'),
       (133, 'Mourning Dove'),
       (134, 'Ringed Turtledove'),
       (135, 'Rockdove'),
       (136, 'Spotted Dove'),
       (137, 'White-winged Dove'),
       (138, 'Francolin'),
       (139, 'Black Francolin'),
       (140, 'Erckel''s Francolin'),
       (141, 'Gray Francolin'),
       (142, 'Blue Grouse'),
       (143, 'Chestnut-bellied Sand Grouse'),
       (144, 'Columbian Sharp-tailed Grouse'),
       (145, 'Dusky Grouse'),
       (146, 'Forest Grouse'),
       (147, 'Franklin''s Grouse'),
       (148, 'Greater Prairie Chicken'),
       (149, 'Lesser Prairie Chicken'),
       (150, 'Ruffed Grouse'),
       (151, 'Greater Sage Grouse'),
       (152, 'Mountain Sharp-tailed Grouse'),
       (153, 'Sharp-tailed Grouse'),
       (154, 'Spruce Grouse'),
       (155, 'Partridge'),
       (156, 'Gray Partridge'),
       (157, 'Hungarian Partridge'),
       (158, 'Pheasant'),
       (159, 'Green Pheasant'),
       (160, 'Himalayan Snowcock'),
       (161, 'Kalik Pheasant'),
       (162, 'Ring-necked Pheasant'),
       (163, 'White-winged Pheasant'),
       (164, 'Ptarmigan'),
       (165, 'Rock Ptarmigan'),
       (166, 'White-tailed Ptarmigan'),
       (167, 'Willow Ptarmigan'),
       (168, 'Quail'),
       (169, 'Bobwhite'),
       (170, 'California Quail'),
       (171, 'Gambel''s Quail'),
       (172, 'Japanese Quail'),
       (173, 'Mountain Quail'),
       (174, 'Northern Bobwhite'),
       (175, 'Scaled Quail'),
       (176, 'English Sparrow'),
       (177, 'House Sparrow'),
       (178, 'Starling'),
       (179, 'Turkey'),
       (180, 'Eastern Turkey'),
       (181, 'Merriam''s Turkey'),
       (182, 'Rio Grande Turkey'),
       (183, 'Osceola Turkey'),
       (184, 'Gould''s Turkey'),
       (185, 'Cormorant'),
       (186, 'Sandhill Crane'),
       (187, 'Duck'),
       (188, 'Mallard'),
       (189, 'Black Duck'),
       (190, 'Mottled Duck'),
       (191, 'Northern Pintail'),
       (192, 'Gadwall'),
       (193, 'American Wigeon'),
       (194, 'Northern Shoveler'),
       (195, 'Blue-winged Teal'),
       (196, 'Cinnamon Teal'),
       (197, 'Green-winged Teal'),
       (198, 'Wood Duck'),
       (199, 'Fulvous Whistling-Duck'),
       (200, 'Black-bellied Whistling Duck'),
       (201, 'Redhead'),
       (202, 'Canvasback'),
       (203, 'Ring-necked Duck'),
       (204, 'Greater Scaup'),
       (205, 'Lesser Scaup'),
       (206, 'Common Goldeneye'),
       (207, 'Barrow''s Goldeneye'),
       (208, 'Bufflehead'),
       (209, 'Harlequin Duck'),
       (210, 'Common Eider'),
       (211, 'King Eider'),
       (212, 'Oldsquaw/Long-tailed Duck'),
       (213, 'Black Scoter'),
       (214, 'White-winged Scoter'),
       (215, 'Surf Scoter'),
       (216, 'Hooded Merganser'),
       (217, 'Red-breasted Merganser'),
       (218, 'Common Merganser'),
       (219, 'Ruddy Duck'),
       (220, 'Gallinule'),
       (221, 'Moorhen'),
       (222, 'American Coot'),
       (223, 'Goose'),
       (224, 'Canada Goose'),
       (225, 'Brant'),
       (226, 'Whitefronted Goose'),
       (227, 'Snow Goose'),
       (228, 'Ross'' Goose '),
       (229, 'Rail'),
       (230, 'Clapper Rail'),
       (231, 'King Rail'),
       (232, 'Sora Rail'),
       (233, 'Virginia Rail'),
       (234, 'Snipe'),
       (235, 'Wilson''s Snipe'),
       (236, 'Tundra Swan'),
       (237, 'Woodcock');


