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
    private String carroceria;

    /**
     * Constructor para dar el alta a una unidad.
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

    /**
     * @return true: la empresa esta activa (empresa.activa = 1 && existe)
     */
    public boolean validarEmpresaActiva(String inCuitEmpresa){
        return(new Empresa(inCuitEmpresa).isActive(inCuitEmpresa));
    }

    /**
     * @return true: el nro RTO existe y esta aprobada (RevisionTecnica.aprobado = 1 && existe)
     */
    public boolean validarRTO(int inNroTecnica){
        return( new RevisionTecnica(inNroTecnica).isAprobe(inNroTecnica));
    }

    /**
     * @return true: la unidad no existe, indice == -1 (no encontró el dominio en el array)
     */
    public boolean isUnidadInexistente(String inDominio){
        return((new Unidad(inDominio).exist(inDominio)) < 0);
    }

    /**
     * Necesito que la unidad exista "y" este inactiva
     * @return true: la unidad existe en el sistema, está cargada,
     * pero actualmente su estado es inactivo (unidad.activo=0).
     */
    public boolean isUnidadInactiva(String inDomino){
        if(! isUnidadInexistente(inDomino) && !((new Unidad(inDomino).isActive(inDomino))))
            return true;
        else
            return false;
    }

    /**
     * @return true: idFlota si la empresa esta activa (empresa.activo = 1 )
     */
    public int getIdFlotaActiva(String inCuitEmpresa){
        Empresa empresa = new Empresa(inCuitEmpresa);
        if( (empresa.exist(inCuitEmpresa) >= 0 ) && (empresa.isActive(inCuitEmpresa)) )
            return(new Flota(inCuitEmpresa).getIdFlota(inCuitEmpresa));
        else
            return(-1);
    }

    public void asociarUnidadFlota(){
        int idFlotaEmpresa = getIdFlotaActiva(this.cuitEmpresa);
        if(idFlotaEmpresa >= 0){
            Flota UnidadFlota = new Flota(this.cuitEmpresa,this.dominio,idFlotaEmpresa,
                    this.nroExpediente, this.nroResolucion, this.corredor,this.nroInterno);
            int indice = UnidadFlota.nextDisponible();
            if((indice >= 0 && indice < Flota.listaFlota.length) && (isUnidadInexistente(this.dominio) || isUnidadInactiva(this.dominio)) ){
                Flota.listaFlota[indice] = UnidadFlota;
            }else {
                System.out.println("CargarUnidad.asociarUnidadFlota lines 100");
                System.out.println("Valor de indice: "+indice);
                System.out.println("isUnidadInexistente(this.dominio): "+isUnidadInexistente(this.dominio));
                System.out.println("isUnidadInactiva(this.dominio): "+isUnidadInactiva(this.dominio));
                System.out.println("existe la unidad: "+(new Unidad(this.dominio).exist(this.dominio)));
            }
        } else if (idFlotaEmpresa < 0) {
            System.out.println("CargarUnidad.asociarUnidadFlota: No se localizo ninguna Unidad en flota que no haya sido daba de baja !!");
        } else if (isUnidadInexistente(this.dominio)) {
            System.out.println("CargarUnidad.asociarUnidadFlota: El dominio existe 'o' esta activo. !!");
        }
    }

    /***
     * crear una nueva unidad en la DB y asociar está a la flota
     * de la empresa.
     */
    public void altaNuevaUnidad(){
        Unidad nuevaUnidad = new Unidad(this.dominio,this.modelo,this.nroChasis,this.nroMotor,this.carroceria);
        if(isAntiguedadOK(this.modelo)) {
            if (validarRTO(this.nroRTO)) {
                if (validarEmpresaActiva(this.cuitEmpresa)) {
                    if (isUnidadInexistente(this.dominio)) {
                        int indice = nuevaUnidad.nextDisponible();
                        if(indice >= 0 &&  indice < Unidad.listaUnidades.length) {
                            Unidad.listaUnidades[indice] = nuevaUnidad;
                            asociarUnidadFlota();
                        }else {
                            System.out.print("CargarUnidad.altaNuevaUnidad: Overflow Unidad.listaUnidades !!");
                        }
                    } else if (isUnidadInactiva(this.dominio)) {
                        nuevaUnidad.setUnidadActiva(this.dominio);
                        asociarUnidadFlota();
                    } else {
                        System.out.print("CargarUnidad.altaNuevaUnidad:  Situación no controlada !!");
                    }
                } else {
                    System.out.print("CargarUnidad.altaNuevaUnidad: la empresa no existe o no tiene un contrato activo !!");
                }
            } else {
                System.out.print("CargarUnidad.altaNuevaUnidad: la RTO no existe o no esta aprobada !!");
            }
        } else {
            System.out.print("CargarUnidad.altaNuevaUnidad: la unidad supera los 15 años  !!");
        }
    }

    public static void imprimirUnidades(){
        System.out.println("Listado de Unidades: ");
        for (int i = 0; i < Unidad.listaUnidades.length; i++) {
            if ((Unidad.listaUnidades[i] != null)) {
                System.out.print("Dominio: "+Unidad.listaUnidades[i].getDominio()+"-");
                System.out.print("Modelo: "+Unidad.listaUnidades[i].getModelo()+"-");
                System.out.println("NroChasis: "+Unidad.listaUnidades[i].getNroChasis());
            }
        }
    }

    public static void imprimirFlota(){
        System.out.println("Listado de la Flota: ");
        for(int i = 0; i < Flota.listaFlota.length; i++){
            if ((Flota.listaFlota[i] != null)) {
                System.out.print("CUIT: "+Flota.listaFlota[i].getCuitEmpresa()+"-");
                System.out.print("idFlota: "+Flota.listaFlota[i].getIdFlota()+"-");
                System.out.println("Dominio: "+Flota.listaFlota[i].getDominioUnidad());
            }
        }
    }
}
