package GestionUnidades;
import GestionBusquedas.BuscarRTO;
import GestionBusquedas.BuscarUnidad;
import GestionBusquedas.Unidad;
import GestionBusquedas.Empresa;

public class CargarUnidad {
    private String dominio = "";
    private int nroInterno,nroRTO,modelo = 0;
    private String corredor,cuitEmpresa ="";
    private String nroExpediente,nroResolucion,nroChasis,nroMotor,nroCarroceria = "";
    private BuscarRTO buscarRTO;
    private Unidad unidad;
    private BuscarUnidad buscarUnidad;
    private Empresa empresa;

    /**
     * Constructor para dar el alta a una unidad que "NO" existe
     * en el sistema.
     */
    public CargarUnidad(String dominio,int modelo,int nroInterno,
                        int nroRTO,String corredor,String cuitEmpresa,
                        String nroExpediente,String nroResolucion,String nroChasis,
                        String nroMotor, String nroCarroceria ) {

        this.buscarUnidad = new BuscarUnidad(dominio);
        this.buscarRTO = new BuscarRTO(nroRTO);
        this.empresa = new Empresa(cuitEmpresa);
        /**
         * Si la unidad "no" ha existido nunca en el sistema.
         */
        if(! buscarUnidad.existeUnidad()){
            /**
             * Veifico si la RTO existe "y" está aprobada.
             */
            if(this.buscarRTO.verifyRTO()) {
                if(empresa.isActive()) {
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
                    altaNuevaUnidad();
                }
                else{
                    System.out.print("LA EMPRESA NO TIENE UN CONTRATO ACTIVO ...");
                }
            }
            else{
                System.out.print("La RTO presentada no es valida -> rechazar carga !!!");
            }
            /**
             * Ya sé que la unidad existe o ha existido en un momento
             * en el tiempo. Tengo que averiguar si esta activa, es decir,
             * si asociada a alguna empresa-
             */
        } else if (! this.buscarUnidad.isActive()) {
            
        }

    }

    public CargarUnidad() {
    }

    private void altaNuevaUnidad(){
        Unidad nuevaUnidad = new Unidad( this.dominio,this.modelo,
                                        this.nroInterno,this.nroRTO,
                                        this.corredor,this.cuitEmpresa,
                                        this.nroExpediente, this.nroResolucion,
                                        this.nroChasis,this.nroMotor,this.nroCarroceria);
        nuevaUnidad.altaNuevaUnidad(empresa.getIdFlota());
    }
}
