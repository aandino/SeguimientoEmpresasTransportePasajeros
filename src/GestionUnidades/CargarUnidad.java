package GestionUnidades;
import java.time.LocalDate;
import java.time.Period;
import GestionBusquedas.*;

public class CargarUnidad {
    private String dominio = "";
    private int nroInterno,nroRTO,modelo = 0;
    private String corredor,cuitEmpresa ="";
    private String nroExpediente,nroResolucion,nroChasis,nroMotor,nroCarroceria = "";

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
        Empresa empresa = new Empresa(inCuitEmpresa);
        return empresa.isActive();
    }

    /**
     * @return true: el nro RTO existe y esta aprobada (RevisionTecnica.aprobado = 1 && existe)
     */
    public boolean validarRTO(int inRto){
        RevisionTecnica rto = new RevisionTecnica(inRto);
        if(rto.isAprobe()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @return true: la unidad no existe en el sistema (unidad.dominio && Unidad.activo+Unidad.dominio not found)
     */
    public boolean isUnidadInexistente(String inDominio){
        Unidad unidad = new Unidad(inDominio);
        if(!unidad.isActive() && !unidad.exist()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @return true: la unidad existe en el sistema, está cargada,
     * pero actualmente su estado es inactivo (unidad.activo=0).
     */
    public boolean isUnidadInactiva(String inDomino){
        Unidad unidad = new Unidad(inDomino);
        if(unidad.exist() && !unidad.isActive()){
            return true;
        }
        else{
            return false;
        }
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
        Unidad nuevaUnidad = new Unidad( this.dominio,this.modelo,
                                        this.nroInterno,this.nroRTO,
                                        this.corredor,this.cuitEmpresa,
                                        this.nroExpediente, this.nroResolucion,
                                        this.nroChasis,this.nroMotor,this.nroCarroceria);
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
