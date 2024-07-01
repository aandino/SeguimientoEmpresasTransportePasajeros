# VALORES INCIALES PARA PRECARGAR LA TABLA EMPRESA Y PODER REALIZAR EL CU CARGAR UNIDAD
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Empresa`(`cuit`,`razonSocial`,`domicilioComercial`,`emailEmpresa`,`activa`)
VALUES
('30700786206', 'Blanca Paloma srl', 'Av. libertador general san martin 91', 'administracion@blancapaloma.com', '1'),
'30710760965', 'Grupo MR SRL', 'Benedicto Morales 265', 'administracion@mr.com.ar', '1'),
'30714197661', 'PANAMERICANA', 'San Juan 1021', 'transporteprivadasl@gmail.com', '1');

# VALORES INCIALES PARA PRECARGAR LA TABLA CONTRATO Y PODER REALIZAR EL CU CARGAR UNIDAD
INSERT INTO Contrato (`idContrato`,`Empresa_cuil`,`nroExpAltaEmpresa`,`nroExpBajaEmpresa`,`nroResolucion`,`fechaInicio`,`fechaFinalizacion`,`dniGerente`,`gerenteNombre`)
VALUES
('2', '30700786206', 'EXP-4200398/21', NULL, '154/2021', '2021-01-01', NULL, '24793222', 'GONZALO MARTIN MARTINE'),
('3', '30714197661', 'EXP-9240478/21', NULL, '216/2021', '2021-02-02', NULL, ' 33362333', 'MIGUEL ANGEL'),
('4', '30710760965', 'EXP-3110440/21', NULL, '28/2021', '2021-03-03', NULL, '27999555', 'SERGIO ANGEL');

#VALORES INCIALES PARA PRECARGAR LA TABLA REVISION TECNICA Y PODER REALIZAR EL CU CARGAR UNIDAD
INSERT INTO RevisionTecnica (`nroTecnica`,`Unidad_dominio`,`nroInterno`,`pagoTasa`,`rtoAprobada`,`fechaEmisionRTO`)
VALUES
('2187', 'HUW814', '125', '1', '1', '2020-05-05'),
('2466', 'GWJ067', '140', '1', '1', '2021-01-19'),
('2467', 'GOJ761', '110', '1', '1', '2020-12-31'),
('2602', 'FIX841', '154', '1', '1', '2021-06-16'),
('2603', 'KIO853', '107', '1', '1', '2021-06-16'),
('2604', 'AB5449', '101', '1', '1', '2021-06-16'),
('2605', 'KIA817', '120', '1', '1', '2021-04-08'),
('2674', 'KII 356', '16', '1', '1', '2021-04-27'),
('2677', 'GOD183', '172', '1', '1', '2021-04-27'),
('2692', 'FVD279', '157', '1', '1', '2021-04-08'),
('2792', 'KEY 790', '15', '1', '1', '2021-06-16');

#

