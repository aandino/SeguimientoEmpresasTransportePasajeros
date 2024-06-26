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
import java.time.LocalDate;

public class RevisionTecnica {
    private int nroTecnica = -1;
    private String dominio = null;
    private int nroInterno = -1;
    private int pagoTasa = -1;
    private int rtoAprobada = -1;
    private LocalDate fechaEmisionRTO = null;


     /** Polimorfismo.
     * Constructor para cuando "solo" se van a realizar consultas
     * sobre una unidad determinada, exista o no.
     */

    public RevisionTecnica(int nroTecnica,String dominio) {
        this.nroTecnica = nroTecnica;
        this.dominio = dominio;
    }

    /**
     * Constructor principal a la hora de crear una nueva RTO.
     * Voy a revisar que ningúno de los parametros tenga el valor por defecto.
     * @param nroTecnica
     * @param dominio
     * @param nroInterno
     * @param pagoTasa
     * @param rtoAprobada
     */
    public RevisionTecnica(int nroTecnica, String dominio, int nroInterno, int pagoTasa, int rtoAprobada) {
        this.nroTecnica = nroTecnica;
        this.dominio = dominio;
        this.nroInterno = nroInterno;
        this.pagoTasa = pagoTasa;
        this.rtoAprobada = rtoAprobada;
        this.fechaEmisionRTO = LocalDate.now();
    }

    public int getNroTecnica(){
        return this.nroTecnica;
    }

    public String getDominio(){
        return this.dominio;
    }

    public int getNroInterno() {
        return this.nroInterno;
    }

    public boolean getPagoTasa() {
        return this.pagoTasa ==1;
    }

    public boolean getRtoAprobada() {
        return this.rtoAprobada == 1;
    }

    public LocalDate getFechaEmisionRTO() {
        return fechaEmisionRTO;
    }


    /**
     * Deseo saber el estado de una RTO (revisión técnica)
     * @return -1,0,1 donde: -1 no existe, 0 no aprobada, 1 aprobada.
     */
    public int isAprobe(){
        //String where ="(Unidad_dominio like '%"+this.dominio+"%')' AND 'nroTecnica";
        String where ="nroTecnica";
        try {
            MysqlConect con = new MysqlConect();
            ResultSet resultado = con.runQuery("rtoAprobada",
                    "RevisionTecnica", where, this.nroTecnica);
            if (resultado.next()) {
                // retorna 0 ó 1
                return (resultado.getInt("rtoAprobada"));
            }
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            sql.printStackTrace();
        }
        return -1;
    }
}
