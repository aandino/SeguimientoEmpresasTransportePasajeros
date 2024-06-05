package GestionUnidades;
import GestionBusquedas.BuscarRTO;
import GestionBusquedas.BuscarUnidad;
import GestionBusquedas.Unidad;

public class CargarUnidad {
    private String dominio;
    private int nroInterno,nroRTO,modelo;
    private String corredor,cuitEmpresa;
    private String nroExpediente,nroResolucion,nroChasis,nroMotor,nroCarroceria;
    private BuscarRTO buscarRTO;
    private Unidad unidad;
    private BuscarUnidad buscarUnidad;

    /**
     * Constructor para dar el alta a una unidad inexistente.
     * @param dominio
     * @param modelo
     * @param nroInterno
     * @param nroRTO
     * @param corredor
     * @param cuitEmpresa
     * @param nroExpediente
     * @param nroResolucion
     * @param nroChasis
     * @param nroMotor
     * @param nroCarroceria
     */
    public CargarUnidad(String dominio,int modelo,int nroInterno,
                        int nroRTO,String corredor,String cuitEmpresa,
                        String nroExpediente,String nroResolucion,String nroChasis,
                        String nroMotor, String nroCarroceria ) {

        this.buscarUnidad = new BuscarUnidad(dominio);
        this.buscarRTO = new BuscarRTO(nroRTO);
        /**
         * Si la unidad "no" existe, ni ha existido, alta completa.
         */
        if(! buscarUnidad.existeUnidad()){
            /**
             * Veifico si la RTO existe y esta aprobada.
             */
            if(this.buscarRTO.queryRTO()) {
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
            else{
                // La RTO presentada no es valida -> rechazar carga !!!
            }
        } else if (! this.buscarUnidad.isActive()) { <<<<<<<<< ====================
            
        }

    }

    public CargarUnidad() {
    }


}
