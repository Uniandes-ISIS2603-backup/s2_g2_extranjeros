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




insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario, imagen) values (140000,'1020840628', '3223854596', 'estoEs123', 'jsantos_pres@hotmail.com', 62, 'Juan Manuel Santos', 'jSantos07', 'https://static.iris.net.co/semana/upload/images/2016/10/8/498264_1.jpg');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario, imagen) values (140001,'1020840629', '3223854596', 'estoEs123', 'crusoerobin_90@hotmail.com', 27, 'Robinson Crusoe', 'robinCr_10','https://images.pexels.com/photos/91227/pexels-photo-91227.jpeg?cs=srgb&dl=black-and-white-fun-happy-91227.jpg&fm=jpg');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario, imagen) values (140002,'1020840630', '3223854596', 'estoEs123', 'natacruz007@hotmail.com', 25, 'Nathalia Cruz', 'ncruz_10', 'https://images.pexels.com/photos/253758/pexels-photo-253758.jpeg?cs=srgb&dl=adolescent-adult-beautiful-253758.jpg&fm=jpg');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario, imagen) values (140003,'1020840631', '3223854596', 'estoEs123', 'sara_riascos09@hotmail.com', 19, 'Sara Lucia Rodriguez', 'saraRodriguez95', 'https://images.pexels.com/photos/762020/pexels-photo-762020.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');
insert into ArrendatarioEntity (id, cedula, celular, clave, correo, edad, nombre, usuario, imagen) values (140004,'1020840632', '3223854596', 'estoEs123', 'rodi_elmejor@hotmail.com', 80, 'Rodrigo Cardoso M', 'rodrigoCard1920', 'https://images.pexels.com/photos/759829/pexels-photo-759829.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940');

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

insert into EventoEntity (id, nombreEevnto, tipoEvento, fechaEvento, distanciaVivienda, ubicacionLon, ubicacionLat, privado, capacidad) values (146841989,'Rumba deliciosa','Fiesta','2018-11-01 17:54:01.11',10,'4.669595','-74.059950', 1,200);
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

insert into ServicioEntity (id, tipo, adicional,preciomensual,imagen) values (100,'Agua',0,350000.0,'http://datosabiertos.bogota.gov.co/base/imagenes/acueducto.jpg');
insert into ServicioEntity (id, tipo, adicional,preciomensual,imagen) values (233422,'Gas',0,350000.0,'http://logodatabases.com/wp-content/uploads/2012/08/gas-natural.jpg');
insert into ServicioEntity (id, tipo, adicional,preciomensual,imagen) values (434235,'Wi-Fi',1,350000.500,'https://images.anandtech.com/doci/11936/wifi_678x452.png');
insert into ServicioEntity (id, tipo, adicional,preciomensual,imagen) values (687,'Luz',0,350000.050,'https://www.explora.cl/images/luzderechouniversalsabias.jpg');
insert into ServicioEntity (id, tipo, adicional,preciomensual,imagen) values (101,'Televisión',1,350000.090,'https://www.realitybytesinc.com/images/watch-tv.jpg');

insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (10111,'VISA',4789215346878546,'10/2018');
insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (101251,'MasterCard',5789215346878546,'10/2018');
insert into TarjetaEntity (id, banco, numero, fechaCaducidad) values (101451,'VISA',378921534687854,'10/2018');

insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (100,'Universidad de los Andes','Cra 1 Nº 18A - 12','4.602135','-74.066245');
insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (101,'Universidad Jorge Tadeo Lozano','Cra 4 Nº 22 - 61','4.606727','-74.067585');
insert into UniversidadEntity (id, nombre,direccion,ubicacionLat,ubicacionLon) values (102,'Pontificia Universidad Javeriana','Cra 7 Nº 40 - 50','4.628464','-74.065118');

insert into ValoracionEntity (id, valoracion,comentario) values (1,3,'habia mucho ruido');
insert into ValoracionEntity (id, valoracion,comentario) values (3125,4,'muy caliente');
insert into ValoracionEntity (id, valoracion,comentario) values (3126,2,'terrible');

insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento, precioMensual) values (11,'carrera 12 7609',14,1,4,'4.643998','-74.071531','A',54637);
insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento,precioMensual) values (8,'calle 189 2345',89,1,22,'4.668502','-74.058386','C',69000);
insert into ViviendaEntity (id,direccion,capacidad,disponible,inquilinos,latitud,longitud,tipoAlojamiento,precioMensual) values (9,'calle 21 1307',3,1,3,'4.652762','-74.056516','B',1234256);
