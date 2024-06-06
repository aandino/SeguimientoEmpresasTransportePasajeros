package GestionBusquedas;

public class Empresa {

    private String cuit, razonSocial, domicilioComercial, emailEmpresa ="";
    private int activa =0;

    /**
     * Constructor para cuando quiera dar de alta una nueva empresa.
     */
    public Empresa(String cuit, String razonSocial, String domicilioComercial, String emailEmpresa, int activa) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.domicilioComercial = domicilioComercial;
        this.emailEmpresa = emailEmpresa;
        this.activa = activa;
    }

    /**
     * Constructor para cuando solo quiero saber informacion sobre una empresa
     * teniendo como unico dato su "cuit"
     */
    public Empresa(String cuit) {
        this.cuit = cuit;
    }

    /**
     * Metodo responsable de la realizacion de las conexiones y/o
     * consultas a la base de datos (capa de abstraccion)
     */
    private String runQuery(String query){
        return("resulado del query on Mysql !!");
    }

    /**
     * Quiero saber si la empresa esta/estuvo registrada en el sistema.
     */
    public boolean exist(){
        String query = "select count(*) from Empresa where cuit = '" +this.cuit+"';";
        String resuladoQuery = runQuery(query);

        if(resuladoQuery != "0") {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Quiero saber si la empresa además de existir en el sistema está activa.
     * Supongo previamente que existe/existió en el sistema.
     */
    public boolean isActive(){
        String query = "select count(*) from Empresa where activo = '1' and cuit = '" +this.cuit+"';";
        String resuladoQuery = runQuery(query);
        if(resuladoQuery != "0") {
            return true;
        }
        else {
            return false;
        }
    }

    public String getIdFlota(){
        /**
         * Solo puede haber una flota activa para una empresa al mismo tiempo.
         */
        String query =
                "SELECT IdFlota from flota where activa = '1' and Empresa_cuit ='"+this.cuit+"' group by idFlota;";
        return(runQuery(query));
    }
}


