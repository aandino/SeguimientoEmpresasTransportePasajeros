package GestionEntidades;
import GestionEntidades.BaseDatos.MysqlConect;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 La clase “Flota” es una clase entidad consecuencia del análisis de los datos y
 en ella recae la responsabilidad de mantener la relación de agregación
 existente en el modelo del negocio. Ella será la responsable de mantener la
 persistencia de los datos de la relación existente entre las tablas Unidad y
 Contrato, brindando información sobre esta y asignando las unidades a la flota de
 vehículos de una empresa.
 */
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
    public int getIdFlotaContrato(){
        ResultSet resultado = null;
        int idActualResult = -1;
        try{
            MysqlConect conect = new MysqlConect();
            resultado = conect.runQuery("COUNT(idFlota),MAX(idFlota)",
                    "Flota","nroExpBajaUnidad IS NULL AND Contrato_idContrato",this.idContratoEmpresa);
            if (resultado.next())
                if(resultado.getInt(1) > 0)
                    return(resultado.getInt(2));
                else
                    return(getLastIdFlota()+1);
            else
                throw new RuntimeException("ERROR linea 94 FLOTA.java !!! ");
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
                throw new RuntimeException("flota.getLastIdFlota 119");
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
        int idFlota = getIdFlotaContrato();
        if(idFlota ==0)
            idFlota = 1;

        String tabla = "Flota";
        String columnas="";
        String valores = "";
        columnas +="Contrato_idContrato,Unidad_dominio,idFlota,nroExpAltaUnidad,fechaAltaUnidad,";
        columnas +="nroResolucionAltaUnidada,corredor,nroInterno";
        valores +="'"+this.idContratoEmpresa+"','"+dominioUnidad+"',"+idFlota+",'"+this.nroExpAltaUnidad+"','"+fechaAltaUnidad+"','";
        valores +=this.nroResolucionAlta+"','"+this.corredor+"',"+this.nroInterno;
        MysqlConect conect = new MysqlConect();
        resultado = conect.runInsertUnidadFlota(tabla,columnas,valores);
        return resultado;
    }
}