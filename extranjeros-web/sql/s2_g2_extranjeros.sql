delete from ArrendatarioEntity;
delete from CuentaBancariaEntity;
delete from EstudianteEntity;
delete from EventoEntity;
delete from FacturaEntity;
delete from LugaresDeInteresEntity;
delete from ProvidenciaEntity;
delete from ServicioEntity;
delete from TarjetaEntity;
delete from UniversidadEntity;
delete from ValoracionEntity;
delete from ViviendaEntity;




insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario) values (140000,'1020840628', '3223854596', 'estoEs123', 'pruebaUno@hotmail.com', 20, 'Un nombre aleta 1', 'aletaJeje');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario) values (140001,'1020840629', '3223854596', 'estoEs123', 'pruebaDos@hotmail.com', 20, 'Un nombre aleta 2', 'aletaJeje2');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario) values (140002,'1020840630', '3223854596', 'estoEs123', 'pruebaTres@hotmail.com', 20, 'Un nombre aleta 3', 'aletaJeje3');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario) values (140003,'1020840631', '3223854596', 'estoEs123', 'pruebaCuatro@hotmail.com', 20, 'Un nombre aleta 4', 'aletaJeje4');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario) values (140004,'1020840632', '3223854596', 'estoEs123', 'pruebaCinco@hotmail.com', 20, 'Un nombre aleta 5', 'aletaJeje5');

insert into CuentaBancariaEntity (id, bancoAsociado, numeroCuenta, saldoCuenta, tipoCuenta) values (15000,'Banco Caja Social', 20202020, 30000, 'Ahorros');
insert into CuentaBancariaEntity (id, bancoAsociado, numeroCuenta, saldoCuenta, tipoCuenta) values (15001,'Banco de Bogota', 20202021, 300000002, 'Corriente');
insert into CuentaBancariaEntity (id, bancoAsociado, numeroCuenta, saldoCuenta, tipoCuenta) values (15002,'Banco Caja Social', 121341232, 324234323432, 'Ahorros');
insert into CuentaBancariaEntity (id, bancoAsociado, numeroCuenta, saldoCuenta, tipoCuenta) values (15003,'Banco Agrario', 324213312112, 12, 'Corriente');
insert into CuentaBancariaEntity (id, bancoAsociado, numeroCuenta, saldoCuenta, tipoCuenta) values (15004,'Banco de los Ricos', 12345678910, 14000000000000, 'Ahorros');

insert into EstudianteEntity (id, nombre, cedula, usuario, correo, clave, celular, edad) values (201616977,'Ana','1020889765','ana123','ana123@gmail.com','clave111','3102312132',18);
insert into EstudianteEntity (id, nombre, cedula, usuario, correo, clave, celular, edad) values (201736899,'Daniel','1020976897','dani123','daniel123@gmail.com','empanada','3214879822',19);
insert into EstudianteEntity (id, nombre, cedula, usuario, correo, clave, celular, edad) values (201568972,'Juan', '10208,76567','juanito','juanmail@gmail.com', 'hola123', '3145678769', 20 );
insert into EstudianteEntity (id, nombre, cedula, usuario, correo, clave, celular, edad) values (201687345,'Jose', '1020789654', 'eljose','granjose@hotmail.com','miclave','3128976543', 21);
insert into EstudianteEntity (id, nombre, cedula, usuario, correo, clave, celular, edad) values (201789373,'Laura', '1020654287','lauris1','lau2018@gmail.com','laulau','3208362548',20);

insert into EventoEntity (id, nombreEvento, tipoEvento, fechaEvento, distanciaVivienda, ubicacionLon, ubicacionLat, privado, capacidad) values (146841989,'Rumba deliciosa','Fiesta','2018-11-01 17:54:01.11',10,'4.669595','-74.059950', 1,200);
insert into EventoEntity (id, nombreEvento, tipoEvento, fechaEvento, distanciaVivienda, ubicacionLon, ubicacionLat, privado, capacidad) values (416841989,'Mueve el bote','Fiesta','2018-04-01 17:54:01.11',10,'4.669595','-74.059950', 1,200);
insert into EventoEntity (id, nombreEvento, tipoEvento, fechaEvento, distanciaVivienda, ubicacionLon, ubicacionLat, privado, capacidad) values (336841989,'Scooby Do papa','Fiesta','2018-05-01 17:54:01.11',10,'4.669595','-74.059950', 1,200);

insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (1234,350.200,100.500,'cobros mensuales',0,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (2,350.200,150.500,'cargo único',0,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (6969696,350.200,122.500,'cobros mensuales',0,{ts '2017-07-04 15:52:25'},{ts '2022-05-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (78945,350.200,10.500,'cargo único',1,{ts '2018-04-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (141454,350.200,203.500,'cobros mensuales',1,{ts '2018-04-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);

insert into LugaresDeInteresEntity (id, tipo,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (132212,'Centro Comercial','Unicentro','Carrera 15 # 127 - 30',6622732,'4.644277','-74.071385"');
insert into LugaresDeInteresEntity (id, tipo,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (2241322,'Bar','La Pola','Carrera 3 # 19 - 30',6626333,'4.669595','-74.059950"');
insert into LugaresDeInteresEntity (id, tipo,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (3342361,'Entretenimiento','Cinema Uniandes','Carrera 19 # 71 - 85',3226574,'4.654693','-74.055516');

insert into ProvidenciaEntity (id, pais, region) values (5324671,'Colombia','Medellin');
insert into ProvidenciaEntity (id, pais, region) values (5764572,'Colombia','Cali');
insert into ProvidenciaEntity (id, pais, region) values (4234423,'Colombia','Cartagena');
insert into ProvidenciaEntity (id, pais, region) values (3423441,'España','Madrid');
insert into ProvidenciaEntity (id, pais, region) values (3454532,'España','Barcelona');

insert into ServicioEntity (id, tipo, adicional) values (100,'Agua',0);
insert into ServicioEntity (id, tipo, adicional) values (233422,'Gas',0);
insert into ServicioEntity (id, tipo, adicional) values (434235,'Wi-Fi',1);
insert into ServicioEntity (id, tipo, adicional) values (687,'Luz',0);
insert into ServicioEntity (id, tipo, adicional) values (101,'Televisión',1);

insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (10111,'VISA',4789215346878546,'10/2018');
insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (101251,'MasterCard',5789215346878546,'10/2018');
insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (101451,'VISA',378921534687854,'10/2018');

insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (100,'Universidad de los Andes','Cra 1 Nº 18A - 12','4.602135','-74.066245');
insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (101,'Universidad Jorge Tadeo Lozano','Cra 4 Nº 22 - 61','4.606727','-74.067585');
insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (102,'Pontificia Universidad Javeriana','Cra 7 Nº 40 - 50','4.628464','-74.065118');

insert into ValoracionEntity (id, valoracion,comentario) values (1,3,'habia mucho ruido');
insert into ValoracionEntity (id, valoracion,comentario) values (3125,4,'muy caliente');
insert into ValoracionEntity (id, valoracion,comentario) values (3126,2,'terrible');

insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento) values (11,'carrera 12 7609',1,0,34,'4.643998','-74.071531','A');
insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento) values (8,'calle 189 2345',2,1,22,'4.668502','-74.058386','C');
insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento) values (9,'calle 21 1307',3,1,3,'4.652762','-74.056516','B');
