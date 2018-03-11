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



insert into ArrendatarioEntity (id, name) values (100,'Oveja Negra');
insert into ArrendatarioEntity (id, name) values (100,'Oveja Negra');
insert into ArrendatarioEntity (id, name) values (100,'Oveja Negra');
insert into ArrendatarioEntity (id, name) values (100,'Oveja Negra');
insert into ArrendatarioEntity (id, name) values (100,'Oveja Negra');

insert into CuentaBancariaEntity (id, name) values (100,'Oveja Negra');
insert into CuentaBancariaEntity (id, name) values (100,'Oveja Negra');
insert into CuentaBancariaEntity (id, name) values (100,'Oveja Negra');
insert into CuentaBancariaEntity (id, name) values (100,'Oveja Negra');
insert into CuentaBancariaEntity (id, name) values (100,'Oveja Negra');

insert into EstudianteEntity (id, name) values (100,'Oveja Negra');
insert into EstudianteEntity (id, name) values (100,'Oveja Negra');
insert into EstudianteEntity (id, name) values (100,'Oveja Negra');
insert into EstudianteEntity (id, name) values (100,'Oveja Negra');
insert into EstudianteEntity (id, name) values (100,'Oveja Negra');

insert into EventoEntity (id, name) values (100,'Oveja Negra');
insert into EventoEntity (id, name) values (100,'Oveja Negra');
insert into EventoEntity (id, name) values (100,'Oveja Negra');
insert into EventoEntity (id, name) values (100,'Oveja Negra');
insert into EventoEntity (id, name) values (100,'Oveja Negra');

insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (1234,350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (54321,350.200,150.500,'cargo único',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (6969696,350.200,122.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2022-05-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (78945,350.200,10.500,'cargo único',true,{ts '2018-04-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id,costoFijo,costosAdicionales,formaDePago,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (141454,350.200,203.500,'cobros mensuales',true,{ts '2018-04-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);

insert into LugaresDeInteresEntity (id, tipol,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (1,'Centro Comercial','Unicentro','Carrera 15 # 127 - 30',6622732,'4.644277','-74.071385"');
insert into LugaresDeInteresEntity (id, tipol,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (2,'Bar','La Pola','Carrera 3 # 19 - 30',6626333,'4.669595','-74.059950"');
insert into LugaresDeInteresEntity (id, tipol,nombre,direccion,telefono,ubicacionLat,ubicacionLon) values (3,'Entretenimiento','Cinema Uniandes','Carrera 19 # 71 - 85',3226574,'4.654693','-74.055516');

insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');

insert into ServicioEntity (id, name, adicional) values (100,'Agua',false);
insert into ServicioEntity (id, name, adicional) values (232,'Gas',false);
insert into ServicioEntity (id, name, adicional) values (45,'Wi-Fi',true);
insert into ServicioEntity (id, name, adicional) values (687,'Luz',false);
insert into ServicioEntity (id, name, adicional) values (101,'Televisión',true);

insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');

insert into UniversidadEntity (id, name) values (100,'Oveja Negra');
insert into UniversidadEntity (id, name) values (232,'Oveja Negra');
insert into UniversidadEntity (id, name) values (45,'Oveja Negra');
insert into UniversidadEntity (id, name) values (687,'Oveja Negra');
insert into UniversidadEntity (id, name) values (101,'Oveja Negra');

insert into ValoracionEntity (id, valoracion,comentario) values (3124,3,'habia mucho ruido');
insert into ValoracionEntity (id, valoracion,comentario) values (3125,4,'muy caliente');
insert into ValoracionEntity (id, valoracion,comentario) values (3126,2,'terrible');

insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (1001,'carrera 12 7609',1,false,'4.643998','-74.071531','A');
insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (1002,'calle 189 2345',2,true,'4.668502','-74.058386',p'C');
insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (2,'calle 21 1307',3,true,'4.652762','-74.056516','B');
