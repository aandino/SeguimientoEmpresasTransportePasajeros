package GestionUnidades;
import java.time.LocalDate;
import java.time.Period;
import GestionEntidades.*;

/**
 * Clase controladora CargarUnidad: es la responsable de llevar
 * adelante la lógica del negocio del CU07 - CargarUnidad.
 */
public class CargarUnidad {
    /**
     * Definición de los atributos de la clase.
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

    /**
     * Clase interna (private) que se encargara de realizar el calcúlo de antigûedad minima
     * de un vehículo
     * @param modelo año de fabricación del vehículo.
     * @return true si tiene menos de 15 años de atigûedad.
     */
    private boolean isAntiguedadOK(int modelo){
        LocalDate currentDate = LocalDate.now();
        LocalDate modeloUnidad = LocalDate.of(modelo, 12, 31);
        Period difference = Period.between(modeloUnidad, currentDate);
        int years = difference.getYears();
        return years < 15;
    }

    /***
     * Método con la responsabilidad de validar que se cumplan con todas las reglas del negocio.
     * Crear una nueva entrada en la tabla Unidad, si esta no existe en el sistema o actualizar
     * el estado en caso de que esta exista y este inactiva, en los demás casos que no se cumpla
     * debe rechazar la carga.
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
            if (rtoAprovada == 1) { // RTO aprobada
                if (idContrato > 0) { // Existe un contrato vigente
                    if(estadoUnidad <= 0) { // -1: unidad no existe en el sistema, 0: existió pero fue dada de baja.
                        // Objeto de tipo flota, para agregar y asociar la unidad a la tabla Flota.
                        Flota registrarUnidadFlota = new Flota(idContrato,this.dominio,this.nroExpediente,
                                this.nroResolucion,this.corredor,this.nroInterno);
                        if(nuevaUnidad.addUnidad() ==1) // se insertó correctamente 1 registro en la tabla Unidad.
                            //Asocio la unidad con la flota de la empresa/contrato.
                            registrarUnidadFlota.addUnidadFlota(nuevaUnidad.getDominio());
                        else // NO se pudo insertar el registro en la tabla Unidad.
                            throw new RuntimeException("CargarUnidad 106: ALGO SALIO MAL - CARGA RECHAZADA !!");
                    }else //estadoUnidad==1 la unidad activa, no se puede cargar 2 veces.
                        throw new RuntimeException("ERROR CargarUnidad 108: LA UNIDAD YA ESTA ACTIVA - CARGA RECHAZADA !!");
                } else {
                        //System.out.print("CargarUnidad.altaNuevaUnidad 110: NO HAY CONTRATO VIGENTE !!");
                        throw new RuntimeException("CargarUnidad 111: NO HAY CONTRATO VIGENTE - CARGA RECHAZADA !!");
                }

            } else {
                    //System.out.print("CargarUnidad.altaNuevaUnidad 115: RTO no existe o no esta aprobada !!");
                    throw new RuntimeException("CargarUnidad 116: RTO NO EXISTE O NO ESTA APROBADA - CARGA RECHAZADA !!");
                }
        } else {
            //System.out.print("CargarUnidad.altaNuevaUnidad 119: la unidad supera los 15 años  !!");
            throw new RuntimeException("CargarUnidad 120: LA UNIDAD SUPERA LOS 15 AÑOS - CARGA RECHAZADA!!");
        }
    }

}
