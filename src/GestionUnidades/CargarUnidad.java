package GestionUnidades;
import java.time.LocalDate;
import java.time.Period;
import GestionBusquedas.*;

public class CargarUnidad {
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
    private String nroCarroceria;

    /**
     * Constructor para dar el alta a una unidad.
     */
    public CargarUnidad(String dominio,int modelo,int nroInterno,
                        int nroRTO,String corredor,String cuitEmpresa,
                        String nroExpediente,String nroResolucion,String nroChasis,
                        String nroMotor, String nroCarroceria ) {
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
        this.nroCarroceria = nroCarroceria;
    }

    public boolean isAntiguedadOK(){
        LocalDate currentDate = LocalDate.now();
        LocalDate modeloUnidad = LocalDate.of(this.modelo, 12, 31);
        Period difference = Period.between(modeloUnidad, currentDate);
        int years = difference.getYears();
        return years < 15;
    }

    /**
     * @return true: la empresa esta activa (empresa.activa = 1 && existe)
     */
    public boolean validarEmpresaActiva(String inCuitEmpresa){
        return new Empresa(inCuitEmpresa).isActive(inCuitEmpresa);
    }

    /**
     * @return true: el nro RTO existe y esta aprobada (RevisionTecnica.aprobado = 1 && existe)
     */
    public boolean validarRTO(int inNroTecnica){
        return new RevisionTecnica(inNroTecnica).isAprobe(inNroTecnica);
    }

    /**
     * @return true: la unidad no existe en el sistema (unidad.dominio && Unidad.activo+Unidad.dominio not found)
     */
    public boolean isUnidadInexistente(String inDominio){
        //  Unidad unidad = new Unidad(inDominio);
        return(new Unidad(inDominio).exist(inDominio) < 0);
        //        return(unidad.exist(inDominio) < 0);
    }

    /**
     * @return true: la unidad existe en el sistema, está cargada,
     * pero actualmente su estado es inactivo (unidad.activo=0).
     */
    public boolean isUnidadInactiva(String inDomino){
        if(! isUnidadInexistente(inDomino) && !(new Unidad(inDomino).isActive(inDomino)))
            return true;
        else
            return false;
    }

    /**
     * @return true: idFlota si la empresa esta activa (empresa.activo = 1 && floa.)
     */
    public String getIdFlotaActiva(String inCuitEmpresa){
        Empresa empresa = new Empresa(inCuitEmpresa);
        if(validarEmpresaActiva(inCuitEmpresa)){
            return(empresa.getIdFlota());
        }
        else{
            // Disparar una excepcion es un caso no controlado !!
            return("Largar execpcion .. la empresa no existe o no tiene un contrato activo !!");
        }
    }

    /***
     * crear una nueva unidad en la DB y asociar está a la flota
     * de la empresa.
     */
    public void altaNuevaUnidad(){
        Unidad nuevaUnidad = new Unidad( this.dominio,this.modelo,this.nroInterno,this.nroRTO,
                                        this.cuitEmpresa,this.nroChasis,this.nroMotor,this.nroCarroceria);
        if(isAntiguedadOK()) {
            if (validarRTO(this.nroRTO)) {
                if (validarEmpresaActiva(this.cuitEmpresa)) {
                    if (isUnidadInexistente(this.dominio)) {
                        nuevaUnidad.crearNuevaUnidad();
                        nuevaUnidad.asociarUnidadFlota(getIdFlotaActiva(this.cuitEmpresa));
                    } else if (isUnidadInactiva(this.dominio)) {
                        nuevaUnidad.setUnidadActiva();
                        nuevaUnidad.asociarUnidadFlota(getIdFlotaActiva(this.cuitEmpresa));
                    } else {
                        System.out.print("Largar execpcion .. situacion no controlada !!");
                    }
                } else {
                    System.out.print("Largar execpcion .. la empresa no existe o no tiene un contrato activo !!");
                }
            } else {
                System.out.print("Largar execpcion .. la RTO no existe o no esta aprobada !!");
            }
        } else {
            System.out.print("Largar execpcion .. la unidad supera los 15 años  !!");
        }
    }
}
