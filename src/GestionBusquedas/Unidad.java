package GestionBusquedas;
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

public class Unidad {
    private String dominio;
    private  int modelo;
    private String nroChasis;
    private String nroMotor;
    private String carroceria;
    private int activo;
    /**
     * listaUnidades: Array de unidades.
     * La misma ha sido estática a modo de mantener persistencia temporal de los
     * datos y ser alcanzada desde otras clases.
     */

    public static Unidad[] listaUnidades = new Unidad[6];
    static {
        listaUnidades[0] = new Unidad("AC443NB", 2018,
               "9BM384067HB000047", "924997U1170000", "ITALBUS");

            listaUnidades[1] = new Unidad("AA381BF",2016,
                "9BM384067HB035047","924997U1175569","ITALBUS");

        listaUnidades[2] = new Unidad("JQJ193",2011,
                "9BM384067BF136894","904968U0896049","METALPAR");

        listaUnidades[3] = new Unidad("KIA817",2011,
                "8BBC51A1ABM000335","D1A055820","METALPAR");
    }

    /**
     * Constructor para crear una nueva unidad no existente.
     *
     */
    public Unidad(String dominio,int modelo,
                  String nroChasis,String nroMotor, String carroceria) {
        this.dominio = dominio;
        this.modelo = modelo;
        this.nroChasis = nroChasis;
        this.nroMotor = nroMotor;
        this.carroceria = carroceria;
        this.activo = 1;
    }

    /**
     * Constructor para cuando "solo" se van a realizar consultas
     * sobre una unidad determinada, exista o no.
     * @param dominio
     */
    public Unidad(String dominio) {
        this.dominio = dominio;
    }

    public String getDominio() {
        return dominio;
    }

    public String getNroChasis() {
        return this.nroChasis;
    }

    public boolean getActivo() {
        return activo == 1;
    }

    public int getModelo() {
        return modelo;
    }

    public void setActivo() {
        this.activo = 1;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    /**
     * Quiero saber si la unidad esta/estuvo registrada en el sistema.
     * @return indice: si el dominio exist en el sistema.
     * -1 : si el dominio nunca ha sido dado de alta.
     */
    public int exist(String inDominio){
        for (int i = 0; i < listaUnidades.length; i++) {
            if ((listaUnidades[i] != null) && (listaUnidades[i].dominio.equals(inDominio))) {
                if (listaUnidades[i] == null)
                    return(-1);
                else if (listaUnidades[i].dominio.equals(inDominio)) {
                    return(i);
                }
            }
        }
        return -1;
    }

    /**
     * Quiero saber si la unidad existe en el sistema "y" además está activa.
     * @return true: unidad activa, pertenece a la flota de alguna empresa.
     * @return false: la unidad no existe o no esta asociada a ninguna empresa.
     */
    public boolean isActive(String dominio){
        int indice = exist(dominio);
        if(indice >= 0)
            return listaUnidades[indice].getActivo();
        else
            return false;
        // Deberia lanzar una excepción la unidad no existe !!!
        // Ojo !! puedo pensar que la unidad esta inactiva cuando en
        // realidad no existe !!
    }


    public void setUnidadActiva(String dominio){
        int indice = exist(dominio);
        if(indice >= 0)
            listaUnidades[indice].setActivo();
        else
            System.out.println("Unidad.setUnidadActiva: La unidad no existe disparar excepción !!");
    }

    public int nextDisponible(){
        for (int i = 0; i < listaUnidades.length; i++) {
            if(listaUnidades[i] == null)
                return i;
        }
        return -1;
    }

}
