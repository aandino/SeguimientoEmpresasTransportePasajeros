package GestionBusquedas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Unidad {
    private String dominio;
    private int nroInterno,nroRTO,modelo;
    private String corredor,cuitEmpresa;
    private String nroExpediente,nroResolucion,nroChasis,nroMotor,nroCarroceria;

    /**
     * Constructor para crear una nueva unidad no existente.
     * @param dominio patente de la undiad
     * @param modelo año de fabricacion de la unidad.
     * @param nroRTO revision tecnica obligatoria, se realiza previo a la carga.
     * @param nroInterno nro asignada en el corredor que le corresponde.
     * @param corredor recorrido que realiza: CarlosPaz-Cordoba
     * @param cuitEmpresa
     * @param nroExpediente expediente asignada en el sistema general de gobierno.
     * @param nroResolucion nro de la resolucion de alta dentro de ese expediente.
     * @param nroChasis
     * @param nroMotor
     * @param nroCarroceria
     */
    public Unidad(String dominio,int modelo,int nroRTO,int nroInterno,
                  String corredor,String cuitEmpresa,
                  String nroExpediente,String nroResolucion, String nroChasis,
                  String nroMotor, String nroCarroceria) {
        this.dominio = dominio;
        this.modelo = modelo;
        this.nroRTO = nroRTO; //polemico no deberia estar aca !!!
        this.nroInterno = nroInterno;
        this.corredor = corredor;
        this.cuitEmpresa = cuitEmpresa;
        this.nroExpediente = nroExpediente;
        this.nroResolucion = nroResolucion;
        this.nroChasis = nroChasis;
        this.nroMotor = nroMotor;
        this.nroCarroceria = nroCarroceria;
    }

    /**
     * Constructor para cuando "solo" se van a realizar consultas
     * sobre una unidad determinada, exista o no.
     * @param dominio
     */
    public Unidad(String dominio) {
        this.dominio = dominio;
    }

    /**
     * Constructor para cuando se quiera hacer consultas sobre la tabla
     * flota (unidades pertenecientes a una empresa)
     * @param dominio
     * @param cuitEmpresa
     */
    public Unidad(String dominio, String cuitEmpresa) {
        this.dominio = dominio;
        this.cuitEmpresa = cuitEmpresa;
    }

    /**
     * Metodo responsable de la realizacion de las conexiones y/o
     * consultas a la base de datos (capa de abstraccion)
     * @param query
     * @return
     */
    private String runQuery(String query){
        return("resulado del query on Mysql !!");
    }

    /**
     * Determinar la existencia de una unidad
     * @return true: el dominio ya existe, aunque sea un dato historico.
     * false: el dominio nunca ha sido dado de alta.
     */
    public boolean exist(){
        String query = "select count(*) from Unidad where dominio = '" +this.dominio+"';";
        String resuladoQuery = runQuery(query);
        if(resuladoQuery != null)
            return true;
        else
            return false;
    }

    /**
     * Verifica si la unidad, ademas de tener datos historicos esta activa,
     * es decir, pertenece a la flota de alguna empresa.
     *
     * @return true; la unidad pertenece a la flota de alguna empresa.
     * @return false; la unidad no existe o no esta asociada a ninguna empresa.
     */
    public boolean isActive(){
        String query = "select activo from Unidad where dominio = '" +this.dominio+"';";
        if(exist()) {
            String resuladoQuery = runQuery(query);
            if (resuladoQuery == "1")
                return true;
        }
        else
            return false;
    }

    public void altaNuevaUnidad(){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);

        String queryEmpresaActiva =
                "select count(*) from Empresa where cuit='"+this.cuitEmpresa+"' and activa ='1';";

        String cargarFlotaUnidad =
                "insert into Flota (Empresa_cuil,Unidad_dominio,fechaAlta,nroExpediente,nroResolicionAlta)" +
                "values('"+this.cuitEmpresa+","+this.dominio+","+fechaFormateada+","+this.nroExpediente+","+
                this.nroResolucion+");";

        String isEmpresaActiva = runQuery(queryEmpresaActiva);

        if(isEmpresaActiva == '1'){
            runQuery(cargarFlotaUnidad);

        }
        else
            System.out.print("Se esta cargando una undiada en una empresa que no tiene un contrato acitvo !!!");

        String insertUnidad ="";
        String insertFlotaUnidad = "";

    }
}
