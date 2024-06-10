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

package GestionBusquedas;

/**
 * Clase entidad Empresa: es la capa de persistencia
 */
public class Empresa {
    /**
     * Definicion de los atributos de la clase.
     */
    private String cuit;
    private String razonSocial;
    private String domicilioComercial;
    private String emailEmpresa;
    private int activa;
    /**
     * listaEmpresa: Array de Empresas.
     * Es estática a modo de mantener persistencia temporal de los
     * datos y ser alcanzada desde otras clases, sin la necesidad de
     * instanciar objetos de clase.
     */

    public static Empresa[] listaEmpresas = new Empresa[2];
    static{
        listaEmpresas[0] = new Empresa("30700786206","Blanca Paloma srl","Av. libertador general san martin 91",
                "administracion@blancapaloma.com");
        listaEmpresas[1] = new Empresa("30710760965","Grupo MR SRL","Benedicto Morales 265",
                "administracion@mr.com.ar");
    }

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
     * Polimorfismo.
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
        return activa == 1;
    }

    /**
     * Quiero saber si la empresa esta/estuvo registrada en el sistema.
     * CUIT la empresa de la cual estoy realizando la búsqueda.
     * @return [indíce] donde haya el registro -1 si no está.
     */

    public int exist(String ciut) {
        for (int i = 0; i < listaEmpresas.length; i++) {
            if ( (listaEmpresas[i] != null) && (listaEmpresas[i].getCuit().equals(ciut)) ) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Quiero saber si la empresa además de existir en el sistema está activa.
     * El uso de este método debe ir siempre acompañado del método exist().
     * Primero validar que exist() retorne >= 0, que es el [índice] donde se
     * encuentra el cuit.
     */
    public boolean isActive(String ciut){
        int indice = exist(ciut);
        if(indice >= 0)
            return listaEmpresas[indice].getActiva();
        else
            return false;
        // Deberia lanzar una excepción: la empresa no se encuentra en la lista !!!
    }

}


