package GestionEntidades;
import GestionEntidades.BaseDatos.MysqlConect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * La clase empresa, es una clase de tipo entidad que representa a las empresas
 * que intervienen en el sistema. Al momento de esta iteración solo provee servicios
 * de acceso a los datos almacenados, puntualmente a la información relacionada a
 * una empresa.
 */

public class Empresa {
    /**
     * Definicion de los atributos de la clase.
     */
    private String cuit = null;
    private String razonSocial = null;
    private String domicilioComercial = null;
    private String emailEmpresa = null;
    private int activa = -1;

    /**
     * Constructor para cuando quiera dar de alta una nueva empresa.
     */
    public Empresa(String cuit, String razonSocial, String domicilioComercial, String emailEmpresa) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.domicilioComercial = domicilioComercial;
        this.emailEmpresa = emailEmpresa;
        this.activa = 1;
    }

    /**
     * Solo para cuando quiero saber información sobre una empresa
     * teniendo como único dato su "cuit"
     */
    public Empresa(String cuit) {
        this.cuit = cuit;
    }

    public String getCuit() {
        return cuit;
    }

    /**
     * @return true si la empresa esta activa.
     */
    public boolean getActiva() {
        return(activa == 1);
    }

    /**
     * Quiero saber si la empresa esta/estuvo registrada en el sistema.
     * CUIT la empresa de la cual estoy realizando la búsqueda.
     * @return -1,0,1 donde: -1 no existe, 0 es INactiva, 1 esta Activa.
     */
    public int exist(String inCuit) {
        try {
            MysqlConect con = new MysqlConect();
            ResultSet resultado = con.runExist("Empresa", "cuit", inCuit);
            if (resultado.next())
                return (resultado.getInt("activa"));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return -1;
    }

    /**
     * Quiero saber si la empresa además de existir en el sistema está activa.
     * @return 1 si la empresa esta activa.
     */
    public boolean isActive(String inCiut){
        int testigo = exist(inCiut);
        return(testigo == 1);
    }

}


