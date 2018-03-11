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

insert into FacturaEntity (id, name,costoFijo,costosAdicionales,formaDePago,numerodeInquilinos,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id, name,costoFijo,costosAdicionales,formaDePago,numerodeInquilinos,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id, name,costoFijo,costosAdicionales,formaDePago,numerodeInquilinos,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id, name,costoFijo,costosAdicionales,formaDePago,numerodeInquilinos,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);
insert into FacturaEntity (id, name,costoFijo,costosAdicionales,formaDePago,numerodeInquilinos,dividirCuentaServicios,fechaEntrada,fechaSalida,iva) values (350.200,100.500,'cobros mensuales',false,{ts '2017-07-04 15:52:25'},{ts '2018-07-04 15:52:25'},0.890);

insert into LugaresDeInteresEntity (id, name) values (100,'Oveja Negra');
insert into LugaresDeInteresEntity (id, name) values (100,'Oveja Negra');
insert into LugaresDeInteresEntity (id, name) values (100,'Oveja Negra');
insert into LugaresDeInteresEntity (id, name) values (100,'Oveja Negra');
insert into LugaresDeInteresEntity (id, name) values (100,'Oveja Negra');

insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');
insert into ProvidenciaEntity (id, name) values (100,'Oveja Negra');

insert into ServicioEntity (id, name) values (100,'Oveja Negra');
insert into ServicioEntity (id, name) values (100,'Oveja Negra');
insert into ServicioEntity (id, name) values (100,'Oveja Negra');
insert into ServicioEntity (id, name) values (100,'Oveja Negra');
insert into ServicioEntity (id, name) values (100,'Oveja Negra');

insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');
insert into TarjetaEntity (id, name) values (100,'Oveja Negra');

insert into UniversidadEntity (id, name) values (100,'Oveja Negra');
insert into UniversidadEntity (id, name) values (100,'Oveja Negra');
insert into UniversidadEntity (id, name) values (100,'Oveja Negra');
insert into UniversidadEntity (id, name) values (100,'Oveja Negra');
insert into UniversidadEntity (id, name) values (100,'Oveja Negra');

insert into ValoracionEntity (id, valoracion,comentario) values (3124,3,'habia mucho ruido');
insert into ValoracionEntity (id, valoracion,comentario) values (3125,4,'muy caliente');
insert into ValoracionEntity (id, valoracion,comentario) values (3126,2,'terrible');

insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (1001,'carrera 12 7609',1,false,'A');
insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (1002,'calle 189 2345',2,true,p'C');
insert into ViviendaEntity (id,dirección,capacidad,disponible,latitud,longitud,tipoAlojamiento) values (2,'calle 21 1307',3,true,l,l,'B');
