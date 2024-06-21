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
    private int nroTecnica;
    private String dominio;
    private int nroInterno;
    private int pagoTasa;
    private int rtoAprobada;
    private LocalDate fechaEmisionRTO;

    public static RevisionTecnica[] listaRTO = new RevisionTecnica[6];
    static{
        listaRTO[0] = new RevisionTecnica(2602,"AC443NB",200,1,1);
        listaRTO[1] = new RevisionTecnica(2603,"AA381BF",121,1,1);
        listaRTO[2] = new RevisionTecnica(2604,"JQJ193",67,1,1);
        listaRTO[3] = new RevisionTecnica(2605,"KIA817",110,1,1);
        listaRTO[4] = new RevisionTecnica(2606,"AA381BB",120,1,1);
    }

     /** Polimorfismo.
     * Constructor para cuando "solo" se van a realizar consultas
     * sobre una unidad determinada, exista o no.
     */

    public RevisionTecnica(int nroTecnica) {
        this.nroTecnica = nroTecnica;
    }

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

    public RevisionTecnica[] getListaRTO() {
        return listaRTO;
    }

    /**
     * Método para determinar si el nroTecnica subministrado existe en el sistema
     * y a su vez si esta ha sido aprobada.
     * @param nroTecnica
     * @return [indice] donde se encontró o -1 sino existe.
     */
    public int existRTO(int nroTecnica, String algo) {
        for (int i = 0; i < listaRTO.length; i++) {
            if ((listaRTO[i] != null) &&(listaRTO[i].getNroTecnica() == nroTecnica)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Deseo saber el estado de una RTO (revisión técnica)
     * @param inNroTecnica el nro. de una revisión técnica que estoy buscando.
     * @return -1,0,1 donde: -1 no existe, 0 no aprobada, 1 aprobada.
     */
    public int isAprobe(int inNroTecnica){
        try {
            MysqlConect con = new MysqlConect();
            ResultSet resultado = con.runQuery("rtoAprobada","RevisionTecnica", "nroTecnica", inNroTecnica);
            if (resultado.next())
                return (resultado.getInt("rtoAprobada"));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return -1;
    }

    /**
     * @deprecated era solo para el array estático de la entrega3
     * @param algo se agregó para sobrecargar el método y reemplazar
     *             las llamadas externas por su nueva versión.
     */
    public boolean isAprobe(int nroTecnica, String algo){
        int indice = existRTO(nroTecnica,"No se usa mas");
        if(indice >= 0)
            return listaRTO[indice].getRtoAprobada() ;
        else
            return false;
    }
}
