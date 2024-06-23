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
    private int idContratoEmpresa = -1;
    private String dominioUnidad = null;
    private int idFlota = -1;
    private String nroExpAltaUnidad = null;
    private String nroExpBajaUnidad = null;
    private LocalDate fechaAltaUnidad = null;
    private LocalDate fechaBajaUnidad = null;
    private String nroResolucionAlta = null;
    private String nroResolucionBaja = null;
    private String corredor = null;
    private int nroInterno = -1;

    /**
     *
     * Constructor principal para crear la relación Unidad - Flota
     */
    public Flota(int idContratoEmpresa, String dominioUnidad,
                 String nroExpAltaUnidad,String nroResolucionAlta,String corredor,int nroInterno) {
        this.idContratoEmpresa = idContratoEmpresa;
        this.dominioUnidad = dominioUnidad;
        this.idFlota = -1;
        this.nroExpAltaUnidad = nroExpAltaUnidad;
        this.nroExpBajaUnidad = null;
        this.fechaAltaUnidad = LocalDate.now();
        this.fechaBajaUnidad = null;
        this.nroResolucionAlta=nroResolucionAlta;
        this.nroResolucionBaja = null;
        this.corredor = corredor;
        this.nroInterno = nroInterno;
    }

    /**
     * Polimorfismo.
     * Constructor para poder acceder a los métodos de la clase
     * cuando el único objetivo es obtener información sobre la
     * existencia de una empresa.
     */
    public Flota(int idContratoEmpresa) {
        this.idContratoEmpresa = idContratoEmpresa;
    }

    /**
     * Busco el ID de flota para un contrato vigente.
     * Se debe haber validado previamente que Contrato (IDContrato) sea válido/vigente.
     * @return -1,0,IdFlota para ese contrato, -1: error sql, 0: la flota no existe aún.
     */

    public int getIdFlota(){
        ResultSet resultado = null;
        try{
            MysqlConect conect = new MysqlConect();
            resultado = conect.runQuery("idFlota","Flota","Contrato_id",this.idContratoEmpresa);
            if (resultado.next())
                return(resultado.getInt("idFlota"));
            else
                return 0;
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }
    }

    /**
     * Obtengo el último IdFlota de la tabla
     * @return -1,max(idFlota), 0: -1 error sql, 0 tabla sin IdFlota (vacía)
     */
    public int getLastIdFlota(){
        ResultSet resultado = null;
        try{
            MysqlConect conect = new MysqlConect();
            resultado = conect.runQuery("COUNT(idFlota),MAX(idFlota)","Flota");
            if (resultado.next()) {
                //Si COUNT(idFlota) > 0 -> hay filas en la tabla Flota
                if(resultado.getInt(1) > 0)
                    return (resultado.getInt(2));
                // COUNT(idFlota) == 0 -> no hay filas en la tabla Flota
                else
                    return 0;
            }
            else
                throw new RuntimeException("flota.getLastIdFlota 116");
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }
    }
    /**
     * Agrego una unidad a una flota existente, si la flota no, creo la primera.
     */

    public int addUnidadFlota(String dominioUnidad){
        //Evito que se llame de forma incorrecta a este método.
        if(this.nroInterno == -1 || this.corredor == null || this.nroExpAltaUnidad == null || this.nroResolucionAlta == null)
            throw new RuntimeException("FLOTA 124: SE ESPERA QUE USE UN CONSTRUCTOR CON TODOS LOS PARÁMETROS !!");
        int resultado = -1;
        LocalDate fechaAltaUnidad = LocalDate.now();
        //Si no hay ninguna flota aún, inserto el primer id en 1.
        //Ya que me retorne cero, en el método getLastIdFlota.
        int idFlota = getIdFlota();
        if(idFlota ==0){
            idFlota = getLastIdFlota()+1;
        }
        String tabla = "FLOTA";
        String columnas="";
        String valores = "";
        columnas +="Contrato_idContrato,Unidad_dominio,idFlota,nroExpAltaUnidad,fechaAltaUnidad";
        columnas +="nroResolucionAltaUnidada,corredor,nroInterno";
        valores +=this.idContratoEmpresa+","+dominioUnidad+","+idFlota+","+this.nroExpAltaUnidad+","+fechaAltaUnidad+",";
        valores +=this.nroResolucionAlta+","+this.corredor+","+this.nroInterno;
        MysqlConect conect = new MysqlConect();
        resultado = conect.runInsertUnidadFlota(tabla,columnas,valores);
        return resultado;
    }

}
