
-- Tabla Zona
insert into ZonaEntity (id, infoZona) values (1, 'Usaquen');
insert into ZonaEntity (id, infoZona) values (2, 'Suba');
insert into ZonaEntity (id, infoZona) values (3, 'La Candelaria');
insert into ZonaEntity (id, infoZona) values (4, 'Bosa');
insert into ZonaEntity (id, infoZona) values (5, 'Soacha');

-- Tabla Cliente
insert into ClienteEntity (id, contrasena, correo, infoContacto, nombre) values (1, 'contrasena', 'daniel@clientes.com', 'Daniel', 'Daniel Garcia');
insert into ClienteEntity (id, contrasena, correo, infoContacto, nombre) values (2, 'contrasena', 'santiago@clientes.com', 'Santiago', 'Santiago Bolanos');
insert into ClienteEntity (id, contrasena, correo, infoContacto, nombre) values (3, 'contrasena', 'nicolas@clientes.com', 'Nicolas', 'Nicolas Potes');
insert into ClienteEntity (id, contrasena, correo, infoContacto, nombre) values (4, 'contrasena', 'mario@clientes.com', 'Mario', 'Mario Hurtado');
insert into ClienteEntity (id, contrasena, correo, infoContacto, nombre) values (5, 'contrasena', 'juan@clientes.com', 'Juan', 'Juan Vergara');

-- Tabla Mascota
insert into MascotaEntity (id, infoMascota, nombre, cliente_id) values (1, 'Golden Retriever', 'Milo', 1);
insert into MascotaEntity (id, infoMascota, nombre, cliente_id) values (2, 'Beagle', 'Tobias', 2);
insert into MascotaEntity (id, infoMascota, nombre, cliente_id) values (3, 'Beagle', 'Lucky', 3);
insert into MascotaEntity (id, infoMascota, nombre, cliente_id) values (4, 'Bernes de la Montaña', 'Magnus', 4);
insert into MascotaEntity (id, infoMascota, nombre, cliente_id) values (5, 'Pastor Belga', 'Lemmy', 4);

-- Tabla FormaPago
insert into FormaPagoEntity (id, capacidadPago, cliente_id) values (1, 2000, 1);
insert into FormaPagoEntity (id, capacidadPago, cliente_id) values (2, 4000, 2);
insert into FormaPagoEntity (id, capacidadPago, cliente_id) values (3, 6000, 3);
insert into FormaPagoEntity (id, capacidadPago, cliente_id) values (4, 8000, 4);
insert into FormaPagoEntity (id, capacidadPago, cliente_id) values (5, 5000, 5);

-- Tabla Paseador
insert into PaseadorEntity (id, contrasena, correo, foto, ganancias, infoAdicional, infoContacto, nombre, precio) values (1, 'contrasena', 'daniel@paseadores.com', 'sinFoto', 0, 'Me gusta pasear perros', 'Sin Contacto' ,'Daniel Garcia', 1000);
insert into PaseadorEntity (id, contrasena, correo, foto, ganancias, infoAdicional, infoContacto, nombre, precio) values (2, 'contrasena', 'santiago@paseadores.com', 'sinFoto', 0, 'Me gusta pasear perros', 'Sin Contacto',  'Santiago Bolanos', 2000);
insert into PaseadorEntity (id, contrasena, correo, foto, ganancias, infoAdicional, infoContacto, nombre, precio) values (3, 'contrasena', 'nicolas@paseadores.com', 'sinFoto', 0, 'Me gusta pasear perros', 'Sin Contacto', 'Nicolas Potes', 3000);
insert into PaseadorEntity (id, contrasena, correo, foto, ganancias, infoAdicional, infoContacto, nombre, precio) values (4, 'contrasena', 'mario@paseadores.com', 'sinFoto', 0, 'Me gusta pasear perros', 'Sin Contacto', 'Mario Hurtado', 2000);
insert into PaseadorEntity (id, contrasena, correo, foto, ganancias, infoAdicional, infoContacto, nombre, precio) values (5, 'contrasena', 'juan@paseadores.com', 'sinFoto', 0, 'Me gusta pasear perros', 'Sin Contacto', 'Juan Vergara', 2000);

--Tabla FranjaHoraria
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (1,'2012-11-11 16:00:00', '2012-11-11 17:00:00', 1);
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (2,'2012-11-11 17:00:00', '2012-11-11 18:00:00', 2);
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (3,'2012-11-11 18:00:00', '2012-11-11 19:00:00', 3);
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (4,'2012-11-12 16:00:00', '2012-11-12 17:00:00', 1);
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (5,'2012-11-11 12:00:00', '2012-11-11 13:00:00', 4);
insert into FranjaHorariaEntity (id, inicio, fin, paseador_id) values (6,'2012-11-11 14:00:00', '2012-11-11 15:00:00', 5);

-- Tabla Contrato
insert into ContratoEntity (id, finalizado, satisfactorio, valorServicio, cliente_id, paseador_id, zona_id, franja_id) values (2, 1, 1, 1000, 1, 1, 1, 1);
insert into ContratoEntity (id, finalizado, satisfactorio, valorServicio, cliente_id, paseador_id, zona_id, franja_id) values (3, 1, 1, 1000, 2, 4, 4, 3);
insert into ContratoEntity (id, finalizado, satisfactorio, valorServicio, cliente_id, paseador_id, zona_id, franja_id) values (4, 1, 1, 1000, 3, 5, 5, 4);

-- Tabla Calificacion
insert into CalificacionEntity (id, calificacion, paseador_id, contrato_id) values (1, 4, 1, 2);
insert into CalificacionEntity (id, calificacion, paseador_id, contrato_id) values (2, 5, 4, 3);
insert into CalificacionEntity (id, calificacion, paseador_id, contrato_id) values (3, 1, 5, 4);

-- Tabla Comentario
insert into ComentarioEntity (id, infoComentario, name, paseador_id, contrato_id) values (1, 'Buen paseador, muy amable', 'Buen servicio', 1, 2);
insert into ComentarioEntity (id, infoComentario, name, paseador_id, contrato_id) values (2, 'Amable', 'Bueno', 4, 3);
insert into ComentarioEntity (id, infoComentario, name, paseador_id, contrato_id) values (3, 'Mato mi perro a patadas', 'Pesimo', 5, 4);

-- Tabla Pago
insert into PagoEntity (id, pagoRealizado, valorServicio, formaPago_id, contrato_id) values (1, 1, 2000, 1, 4);
insert into PagoEntity (id, pagoRealizado, valorServicio, formaPago_id, contrato_id) values (2, 1, 2000, 2, 2);
insert into PagoEntity (id, pagoRealizado, valorServicio, formaPago_id, contrato_id) values (3, 1, 2000, 3, 3);

-- Tabla Mascota_Contrato
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (4, 1);
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (4, 2);
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (4, 3);
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (2, 4);
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (2, 3);
insert into MascotaEntity_ContratoEntity (contratos_id, mascotas_id) values (3, 5);

-- Tabla Zona_Paseador
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (1, 1); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (1, 2); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (2, 3); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (2, 4); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (3, 5); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (4, 3); 
insert into ZonaEntity_PaseadorEntity (zonas_id, paseadores_id) values (5, 4); 





