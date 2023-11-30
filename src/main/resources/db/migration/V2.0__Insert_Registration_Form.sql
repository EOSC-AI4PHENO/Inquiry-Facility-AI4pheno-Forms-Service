-- Insert into formHeader
INSERT INTO public."formHeader" ("name", "isActive", "type")
VALUES ('verification', true, 'REGISTRATION');


-- Insert into formAttribute
INSERT INTO public."formAttribute" ("name", "type", "defaultValue", "values", "parentId")
VALUES
    ('Name', 'text', 'Name', NULL, NULL),
    ('Surname', 'text', 'Surname', NULL, NULL),
--     ('e-mail', 'text', 'e-mail', NULL, NULL),
    ('Organisation', 'text', 'Organisation', NULL, NULL),
    ('Country of organisation', 'list', NULL, 'Afganistan;Albania;Algieria;Andora;Angola;Anguilla;Antarktyda;Antigua i Barbuda;Arabia Saudyjska;Argentyna;Armenia;Aruba;Australia;Austria;Azerbejdżan;Bahamy;Bahrajn;Bangladesz;Barbados;Belgia;Belize;Benin;Bermudy;Bhutan;Białoruś;Boliwia;Bonaire, Sint Eustatius i Saba;Bośnia i Hercegowina;Botswana;Brazylia;Brunei Darussalam;Brytyjskie Terytorium Oceanu Indyjskiego;Bułgaria;Burkina Faso;Burundi;Ceuta;Chile;Chiny;Curaçao;Chorwacja;Cypr;Czad;Czarnogóra;Czechy;Dania;Dominika;Dominikana;Dżibuti;Egipt;Ekwador;Erytrea;Estonia;Etiopia;Falklandy;Fidżi Republika;Filipiny;Finlandia;Francja;Gabon;Gambia;Ghana;Gibraltar;Grecja;Grenada;Grenlandia;Gruzja;Guam;Gujana;Gwatemala;Gwinea;Haiti;Hiszpania;Honduras;Hongkong;Indie;Indonezja;Irak;Iran;Irlandia;Islandia;Izrael;Jamajka;Japonia;Jemen;Jordania;Kajmany;Kambodża;Kamerun;Kanada;Katar;Kazachstan;Kenia;Kirgistan;Kiribati;Kolumbia;Komory;Kongo;Kongo, Republika Demokratyczna;Koreańska Republika Ludowo-Demokratyczna;Kosowo;Kostaryka;Kuba;Kuwejt;Laos;Lesotho;Liban;Liberia;Libia;Liechtenstein;Litwa;Luksemburg;Łotwa;Macedonia Północna;Madagaskar;Majotta;Makau;Malawi;Malediwy;Malezja;Mali;Malta;Mariany Północne;Maroko;Mauretania;Mauritius;Meksyk;Melilla;Mikronezja;Mołdawia;Mongolia;Montserrat;Mozambik;Myanmar (Burma);Namibia;Nauru;Nepal;Niderlandy;Niemcy;Niger;Nigeria;Nikaragua;Niue;Norfolk;Norwegia;Nowa Kaledonia;Nowa Zelandia;Okupowane Terytorium Palestyny;Oman;Pakistan;Palau;Panama;Papua Nowa Gwinea;Paragwaj;Peru;Pitcairn;Polinezja Francuska;Polska;Południowa Afryka;Portugalia;Republika Korei;Rep.Środkowoafryańska;Rosja;Rwanda;Sahara Zachodnia;Saint Barthelemy;Rumunia;Salwador;Samoa;Samoa Amerykańskie;San Marino;Senegal;Serbia;Seszele;Sierra Leone;Singapur;Suazi;Słowacja;Słowenia;Somalia;Sri Lanka;St. Pierre i Miquelon;St.Kitts i Nevis;St.Lucia;St.Vincent i Grenadyny;Stany Zjedn. Ameryki;Sudan;Surinam;Syria;Szwajcaria;Szwecja;Święta Helena;Tadżykistan;Tajlandia;Tajwan;Tanzania;Togo;Tokelau;Tonga;Trynidad i Tobago;Tunezja;Turcja;Turkmenistan;Wyspy Turks i Caicos;Tuvalu;Uganda;Ukraina;Urugwaj;Uzbekistan;Vanuatu;Wallis i Futuna;Watykan;Wenezuela;Węgry;Wielka Brytania;Wietnam;Włochy;Wschodni Timor;Wyb.Kości Słoniowej;Wyspa Bouveta;Wyspa Bożego Narodzenia;Wyspy Cooka;Wyspy Dziewicze-USA;Wyspy Dziewicze-W.B;Wyspy Heard i McDonald;Wyspy Kokosowe (Keelinga);Wyspy Owcze;Wyspy Marshalla;Wyspy Salomona;Wyspa Sint Maarten (część holenderska wyspy);Wyspy Św.Tomasza i Książęca;Zambia;Zielony Przylądek;Zimbabwe;Zjedn.Emiraty Arabskie', NULL),
    ('Position/level of expertise', 'list', NULL, 'student;junior researcher;senior researcher;technician;other', NULL),
    ('Scope of activity/research field', 'list', NULL, 'agronomy;botany;climatology;ecology;environmental protection;horticulture;hydrology;public health;silviculture;tourism, recreation and sport;other', NULL),
    ('Purpose of use', 'list', NULL, 'AI algorithm testing;image storage;phenological analyses', NULL),
    ('Project', 'text', 'Name', NULL, NULL);


-- Insert into header2attribute
INSERT INTO public."header2attribute" ("headerId", "attributeId", "isActive", "isRequired")
SELECT
    fh.id,
    fa.id,
    true,
    true
from public."formAttribute" fa
         cross join public."formHeader" fh
WHERE fh."type" = 'REGISTRATION'
