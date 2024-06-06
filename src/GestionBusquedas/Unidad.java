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
     * Quiero saber si la unidad esta/estuvo registrada en el sistema.
     * @return true: el dominio exist en el sistema.
     * false: el dominio nunca ha sido dado de alta.
     */
    public boolean exist(){
        String query = "select count(*) from Unidad where dominio = '" +this.dominio+"';";
        String resultadoQuery = runQuery(query);
        if(resultadoQuery != "0")
            return true;
        else
            return false;
    }

    /**
     * Quiero saber si la unidad existe en el sistema "y" además está activa.
     * @return true: unidad activa, pertenece a la flota de alguna empresa.
     * @return false: la unidad no existe o no esta asociada a ninguna empresa.
     */
    public boolean isActive(){
        String query = "select count(*) from Unidad where activo ='1' and dominio = '" +this.dominio+"';";
        String resultadoQuery = runQuery(query);

        if(resultadoQuery != "0") {
            return true;
        }
        else {
            return false;
        }

    }

    public void altaNuevaUnidad(String idFlota){
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);

        String insertarFlotaUnidad =
                "insert into Flota (IdFlota,Empresa_cuil,Unidad_dominio,fechaAlta,nroExpediente,nroResolicionAlta)" +
                "values('"+idFlota+"','"+this.cuitEmpresa+"','"+this.dominio+"','"+fechaFormateada+"','"+this.nroExpediente+"','"+
                this.nroResolucion+");";

        String insertarUnidad =
                "INSERT INTO UNIDAD (dominio, modelo, nroChasis, nroMotor, nroCarroceria, activo) VALUES("+
                        this.dominio+","+this.modelo+","+this.nroChasis+","+this.nroCarroceria+",1);";
        runQuery(insertarFlotaUnidad);
        runQuery(insertarFlotaUnidad);
    }
}
