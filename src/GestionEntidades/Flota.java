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
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Flota {
    private String cuitEmpresa;
    private String dominioUnidad;
    private int idFlota;
    private String nroExpAltaUnidad;
    private String nroExpBajaUnidad;
    private LocalDate fechaAltaUnidad;
    private LocalDate fechaBajaUnidad;
    private String nroResolucionAlta;
    private String nroResolucionBaja;
    private String corredor;
    private int nroInterno;
    /**
     * listaFlota: Array que mantiene la relación Unidad-Flota-Empresa.
     * Es estática a modo de mantener persistencia temporal de los
     * datos y ser alcanzada desde otras clases, sin la necesidad de
     * instanciar objetos de clase.
     */

    public static Flota[] listaFlota = new Flota[10];
    static {
        listaFlota[0] = new Flota("30700786206","AC443NB",1
                ,"EXP-4200398/21","046/17"
                ,"San Luis - La Punta",200);

        listaFlota[1] = new Flota("30710760965","AA381BF",2,
                "EXP-4210681/21","136/18"
                ,"San Luis - Juana Koslay",121);

        listaFlota[2] = new Flota("30710760965","JQJ193",2,
                "EXP-4210405/21","76/11"
                ,"San Luis - Potrero",67);

        listaFlota[3] = new Flota("30710760965","KIA817",2,
                "EXP-1290147/21","131/11"
                ,"San Luis - Potrero",110);
    }

    /**
     *
     * Constructor principal para crear la relación Unidad - Flora - Empresa.
     */
    public Flota(String cuitEmpresa, String dominioUnidad,
                 int idFlota, String nroExpAltaUnidad,String nroResolucionAlta,
                 String corredor,int nroInterno) {

        this.cuitEmpresa = cuitEmpresa;
        this.dominioUnidad = dominioUnidad;
        this.idFlota = idFlota;
        this.nroExpAltaUnidad = nroExpAltaUnidad;
        this.nroExpBajaUnidad = null;
        this.fechaAltaUnidad = LocalDate.now();
        this.fechaBajaUnidad = null;
        this.nroResolucionAlta=null;
        this.nroResolucionBaja = null;
        this.corredor = corredor;
    }

    /**
     * Polimorfismo.
     * Constructor para poder acceder a los métodos de la clase
     * cuando el único objetivo es obtener información sobre la
     * existencia de una empresa.
     * @param cuitEmpresa
     */
    public Flota(String cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public int getIdFlota() {
        return idFlota;
    }

    public String getCuitEmpresa() {
        return cuitEmpresa;
    }

    public String getDominioUnidad() {
        return dominioUnidad;
    }

    public int getIdFlota(String inCuitEmpresa){
        int idEmpresa;
        int idContrato;
        ResultSet resultado = null;
        ResultSet resulIdFlota = null;
        try{
            MysqlConect con = new MysqlConect();
            resultado = con.runQuery("idContrato","Contrato","nroExpBajaEmpresa = NULL AND Empresa_cuit",inCuitEmpresa);
            if (resultado.next()) {
                idContrato = resultado.getInt("idContrato");
                resulIdFlota = con.runQuery("idFlota","Flota","Contrato_idContrato",idContrato);
                if (resulIdFlota.next())
                    return(resulIdFlota.getInt("IdFlota"));
                else
                    throw new SQLException("NO EXISTE UNA FLOTA ACTIVA Flota.getIdFlota linea 117");
            }
            else
                throw new SQLException("NO EXISTE UN CONTRATO VIGENTE Flota.getIdFlota linea 111");
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return -1;
    }

    public int getIdFlota(String inCuitEmpresa, String algo){
        for(Flota test : listaFlota){
            if(test.getCuitEmpresa().equals(inCuitEmpresa)){
                if(test.fechaBajaUnidad == null && test.nroExpBajaUnidad == null &&
                        test.nroResolucionBaja == null)
                    return test.getIdFlota();
            }
        }
        return(-1);
    }

    public int nextDisponible(){
        for (int i = 0; i < listaFlota.length; i++) {
            if(listaFlota[i] == null)
                return i;
        }
        return -1;
    }

    /**
     * @deprecated
     */
    public void addUnidadFlota(int idFlota, int idContrato,String dominioUnidad){

    }
}
