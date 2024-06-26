/**
 * Set de Prueba para dar de alta una unidad.
 *
 * Pre-Requisito:
 *  Debe estar cargada la relación Unidad-RevisionTecnica.
 *  Para este caso se suministra la relación en:
 *  RevisionTecnica.listaRTO[4] = new RevisionTecnica(2606,"AA381BB",120,1,1);
 *  Donde se refleja que la unidad con dominio:AA381BB se le asignó el
 *  nro de técnica "2606", la cual está aprobada, bit 1 final.
 *  El sistema no permite cargar una nueva unidad que no haya pasado
 *  el proceso de revision técnica obligatoria, es decir un registro
 *  RevisionTecnica.listaRTO.
 *
 *  Datos Unidad:
 *      Dominio: AA381BB
 *      Modelo: 2016
 *      Nro. Interno: 120
 *      Corredor: San Luis - La Carolina
 *      Nro. Revision Técnica: 2606
 *      CUIT: 30710760965
 *      EXP de Alta: EXP-1290150/22
 *      Resolución: 44/11
 *      Nro. Chasis: 8BBC51A1AGM001214
 *      Nro. Motor: DCA000280
 *      Carrocería: TODOBUS
 */

package GestionUnidades;
import java.time.LocalDate;
import java.time.Period;
import GestionEntidades.*;

/**
 * Clase controladora Cargar Unidad: es la responsable de llevar
 * adelante el CU07 - CargarUnidad.
 */
public class CargarUnidad {
    /**
     * Definicion de los atributos de la clase.
     */
    private String dominio;
    private int nroInterno;
    private int nroRTO;
    private int modelo;
    private String corredor;
    private String cuitEmpresa;
    private String nroExpediente;
    private String nroResolucion;
    private String nroChasis;
    private String nroMotor;
    private String carroceria;

    /**
     * Constructor para dar el alta a una unidad.
     * @param nroRTO: número revisión técnica o nroTecnica.
     */
    public CargarUnidad(String dominio,int modelo,int nroInterno,
                        int nroRTO,String corredor,String cuitEmpresa,
                        String nroExpediente,String nroResolucion,String nroChasis,
                        String nroMotor, String carroceria ) {
        this.dominio = dominio;
        this.modelo = modelo;
        this.nroInterno = nroInterno;
        this.nroRTO = nroRTO;
        this.corredor = corredor;
        this.cuitEmpresa = cuitEmpresa;
        this.nroExpediente = nroExpediente;
        this.nroResolucion = nroResolucion;
        this.nroChasis = nroChasis;
        this.nroMotor = nroMotor;
        this.carroceria = carroceria;
    }

    public boolean isAntiguedadOK(int modelo){
        LocalDate currentDate = LocalDate.now();
        LocalDate modeloUnidad = LocalDate.of(modelo, 12, 31);
        Period difference = Period.between(modeloUnidad, currentDate);
        int years = difference.getYears();
        return years < 15;
    }

    /***
     * crear una nueva unidad en la DB y asociar está a la flota
     * de la empresa.
     */
    public void altaNuevaUnidad(){
        // antiguedadOK: si la unidad tiene menos de 15 años.
        boolean antiguedadOK = isAntiguedadOK(this.modelo);
        // rtoAprovada: -1,0,1 donde: -1 no existe, 0 no aprobada, 1 aprobada.
        int rtoAprovada = new RevisionTecnica(this.nroRTO,this.dominio).isAprobe();
        // idContrato: 0 no hay contrato vigente ó idContrato vigente actual.
        int idContrato = new Contrato(this.cuitEmpresa).getIdContrato();
        // Creo un objeto Unidad con todos los parámetros.
        Unidad nuevaUnidad = new Unidad(this.dominio,this.modelo,this.nroChasis,this.nroMotor,this.carroceria);
        // estadoUnidad: -1,0,1 donde: -1 no existe, 0 es INactiva, 1 esta activa.
        int estadoUnidad = nuevaUnidad.exist();
        if(antiguedadOK) {
            if (rtoAprovada == 1) {
                if (idContrato > 0) {
                    if(estadoUnidad <= 0) {
                        Flota registrarUnidadFlota = new Flota(idContrato,this.dominio,this.nroExpediente,
                                this.nroResolucion,this.corredor,this.nroInterno);
                        if(nuevaUnidad.addUnidad() ==1)
                            registrarUnidadFlota.addUnidadFlota(nuevaUnidad.getDominio());
                        else
                            throw new RuntimeException("CargarUnidad 106: ALGO SALIO MAL !!");
                    }else
                        throw new RuntimeException("ERROR CargarUnidad 108: LA UNIDAD YA ESTA ACTIVA !!");
                } else {
                        System.out.print("CargarUnidad.altaNuevaUnidad 110: NO HAY CONTRATO VIGENTE !!");
                        throw new RuntimeException("CargarUnidad 111: NO HAY CONTRATO VIGENTE !!");
                }

            } else {
                    System.out.print("CargarUnidad.altaNuevaUnidad 115: RTO no existe o no esta aprobada !!");
                    throw new RuntimeException("CargarUnidad 116: RTO NO EXISTE O NO ESTA APROBADA !!");
                }
        } else {
            System.out.print("CargarUnidad.altaNuevaUnidad 119: la unidad supera los 15 años  !!");
            throw new RuntimeException("CargarUnidad 120: LA UNIDAD SUPERA LOS 15 AÑOS!!");
        }
    }

}
