insert into usuario values ("904","cll28#14-09","mario@gmail.com","mario","3216758976","Heropro.12","unimario");
insert into usuario values ("905","cll28#14-10","pedro@gmail.com","pedro","3216758977","Heropro.13","unipedro");
insert into usuario values ("906","cll28#14-11","julian@gmail.com","julian","3216758978","Heropro.14","unijulian");
insert into usuario values ("907","cll28#14-22","julia@gmail.com","pedro","3336758978","Heropro.15","unijulia");
insert into usuario values ("908","cll28#14-33","lian@gmail.com","gabriel","38916758978","Heropro.16","unilian");

insert into categoria values ("301","ropa");
insert into categoria values ("302","vehiculo");
insert into categoria values ("303","Celulares");
insert into categoria values ("304","Electrodomesticos");
insert into categoria values ("305","Inmuebles");

insert into sub_categoria values ("21","motos","302");
insert into sub_categoria values ("22","carros","302");
insert into sub_categoria values ("23","zapatos","301");

insert into libro values ("1","celular 10 de 10","2023-10-23","2023-10-25","celular",200000,4,"301","904");
insert into libro values ("2","moto con el moto en perfecto estado","2021-10-23","2022-10-25","moto",400000,4,"302","904");
insert into libro values ("3","carro con 20.000 kilometros","2021-10-23","2022-10-25","carro",32,4,"302","905");
insert into libro values ("4","casa de 4 habitaciones","2021-10-23","2022-10-25","vivienda",32,4,"305","905");
insert into libro values ("5","bicicleta BMW","2020-10-23","2021-10-25","bicicleta",32,4,"302","906");
insert into libro values ("6","Televisor de 50 pulgadas smart tv","2023-03-01","2023-10-25","televisor",32,4,"304","906");

insert into usuario_productos_favoritos values ("908","1");
insert into usuario_productos_favoritos values ("908","2");

insert into imagen values ("21","src/view/log","1");
insert into imagen values ("22","src/view/log","1");
insert into imagen values ("23","src/view/log","2");
insert into imagen values ("24","src/view/log","2");

insert into compra values ("34","2023-04-01","MASTERCARD",200000,"904");
insert into compra values ("35","2023-04-02","MASTERCARD",200000,"904");
insert into compra values ("36","2023-04-03","MASTERCARD",200000,"904");
insert into compra values ("37","2023-04-04","MASTERCARD",200000,"904");

insert into detalle_compra values ("81",200000,1,"34","1");
insert into detalle_compra values ("82",400000,1,"34","2");

insert into comentario values("51","2023-07-02","cuanto vale","1","908");
insert into comentario values("52","2023-07-02","donde esta ubicado","1","908");
insert into comentario values("53","2023-07-02","viene con caja?","1","908");

--------------------------------------- Mas datos ---------------------------------------------

