INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Empresa` (`cuit`, `razonSocial`, `domicilioComercial`, `emailEmpresa`, `activa`) VALUES ('30700786206', 'Blanca Paloma srl', 'Av. libertador general san martin 91', 'administracion@blancapaloma.com', 1);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Empresa` (`cuit`, `razonSocial`, `domicilioComercial`, `emailEmpresa`, `activa`) VALUES ('30710760965', 'Grupo MR SRL', 'Benedicto Morales 265', 'administracion@mr.com.ar', 1);

INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Unidad` (`dominio`, `modelo`, `nroChasis`, `nroMotor`, `nroCarroceria`, `activo`) VALUES ('AC443NB', '2018', '9BM384067HB000047', '924997U1170000', 'ITALBUS', 1);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Unidad` (`dominio`, `modelo`, `nroChasis`, `nroMotor`, `nroCarroceria`, `activo`) VALUES ('AA381BF', '2016', '9BM384067HB035047', '924997U1175569', 'ITALBUS', 1);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Unidad` (`dominio`, `modelo`, `nroChasis`, `nroMotor`, `nroCarroceria`, `activo`) VALUES ('JQJ193', '2011', '9BM384067BF136894', '904968U0896049', 'METALPAR', 1);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Unidad` (`dominio`, `modelo`, `nroChasis`, `nroMotor`, `nroCarroceria`, `activo`) VALUES ('KIA817', '2011', '8BBC51A1ABM000335', 'D1A055820', 'METALPAR', 1);

INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Flota` (`Empresa_cuit`, `Unidad_dominio`, `IdFlota`, `nroExpAltaUnidad`, `nroExpBajaUnidad`, `fechaAltaUnidad`, `fechaBajaUnidad`, `nroResolucionAltaUnidada`, `nroResolucionBajaUnidad`, `corredor`, `nroInterno`) VALUES ('30700786206', 'AC443NB', 1, 'EXP-4200398/21', NULL, '2024-06-09 22:59:59', NULL, '046/17', NULL, 'San Luis - La Punta', 200);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Flota` (`Empresa_cuit`, `Unidad_dominio`, `IdFlota`, `nroExpAltaUnidad`, `nroExpBajaUnidad`, `fechaAltaUnidad`, `fechaBajaUnidad`, `nroResolucionAltaUnidada`, `nroResolucionBajaUnidad`, `corredor`, `nroInterno`) VALUES ('30710760965', 'AA381BF', 2, 'EXP-4210681/21', NULL, '2024-06-09 22:59:59', NULL, '136/18', NULL, 'San Luis - Juana Koslay', 121);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Flota` (`Empresa_cuit`, `Unidad_dominio`, `IdFlota`, `nroExpAltaUnidad`, `nroExpBajaUnidad`, `fechaAltaUnidad`, `fechaBajaUnidad`, `nroResolucionAltaUnidada`, `nroResolucionBajaUnidad`, `corredor`, `nroInterno`) VALUES ('30710760965', 'JQJ193', 2, 'EXP-4210405/21', NULL, '2024-06-09 22:59:59', NULL, '76/11', NULL, 'San Luis - Potrero', 67);
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`Flota` (`Empresa_cuit`, `Unidad_dominio`, `IdFlota`, `nroExpAltaUnidad`, `nroExpBajaUnidad`, `fechaAltaUnidad`, `fechaBajaUnidad`, `nroResolucionAltaUnidada`, `nroResolucionBajaUnidad`, `corredor`, `nroInterno`) VALUES ('30710760965', 'KIA817', 2, 'EXP-1290147/21', NULL, '2024-06-09 22:59:59', NULL, '131/11', NULL, 'San Luis - Potrero', 110);

INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`RevisionTecnica` (`nroTecnica`, `Unidad_dominio`, `nroInterno`, `pagoTasa`, `rtoAprobada`, `fechaEmisionRTO`) VALUES (2602, 'AC443NB', 200, 1, 1, '2024-06-09');
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`RevisionTecnica` (`nroTecnica`, `Unidad_dominio`, `nroInterno`, `pagoTasa`, `rtoAprobada`, `fechaEmisionRTO`) VALUES (2603, 'AA381BF', 121, 1, 1, '2024-06-09');
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`RevisionTecnica` (`nroTecnica`, `Unidad_dominio`, `nroInterno`, `pagoTasa`, `rtoAprobada`, `fechaEmisionRTO`) VALUES (2604, 'JQJ193', 67, 1, 1, '2024-06-09');
INSERT INTO `SeguimientoEmpresasTransportePasajeros`.`RevisionTecnica` (`nroTecnica`, `Unidad_dominio`, `nroInterno`, `pagoTasa`, `rtoAprobada`, `fechaEmisionRTO`) VALUES (2605, 'KIA817', 110, 1, 1, '2024-06-09');

#

Select * from Flota;
Describe Flota;
select * from Unidad;
Select * from RevisionTecnica;
Select * from Empresa;
# Las Undiades pertenecientes a la Empresa GrupoMR que estan activas. 
Select Empresa_cuit as 'CUIT GrupoMR', Unidad_dominio,nroExpAltaUnidad  from Flota Where Empresa_cuit ='30710760965' and nroResolucionBajaUnidad = null;