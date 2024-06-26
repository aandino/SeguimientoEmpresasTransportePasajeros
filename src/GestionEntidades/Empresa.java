/**
 * Set de Prueba para dar de alta una unidad.
 *
 * Pre-Requisito:
 *  Debe estar cargada la relación Unidad-RevisionTecnica.
 *  Para este caso se suministra la relación en:
 *  RevisionTecnica.listaRTO[4] = new RevisionTecnica(2606,"AA381BB",120,1,1);
 *  Donde se refleja que la unidad con dominio:AA381BB se le asignó el
 *  nro de técnica "2606", la cual está aprobada, bit 1 final.
 *  El sistema no permite cargar una nueva unidad que no haya pasado
 *  el proceso de revision técnica obligatoria, es decir un registro
 *  RevisionTecnica.listaRTO.
 *
 *  Datos Unidad:
 *      Dominio: AA381BB
 *      Modelo: 2016
 *      Nro. Interno: 120
 *      Corredor: San Luis - La Carolina
 *      Nro. Revision Técnica: 2606
 *      CUIT: 30710760965
 *      EXP de Alta: EXP-1290150/22
 *      Resolución: 44/11
 *      Nro. Chasis: 8BBC51A1AGM001214
 *      Nro. Motor: DCA000280
 *      Carrocería: TODOBUS
 */

package GestionEntidades;
import GestionEntidades.BaseDatos.MysqlConect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase entidad Empresa: es la capa de persistencia
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


