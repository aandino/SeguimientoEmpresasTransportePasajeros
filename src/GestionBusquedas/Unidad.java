package GestionBusquedas;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Unidad {
    private String dominio;
    private  int modelo;
    private String nroChasis;
    private String nroMotor;
    private String carroceria;
    private int activo;

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
/**
 * Datos de prueba - esta mal hay que sacar nroInterno, corredor, nroExp,nroResolucion
 *       listaUnidades[4] = new Unidad("AA381BB",2016,2606,120,"San Luis - La Carolina","30710760965",
 *               "EXP-1290150/22","44/11","8BBC51A1AGM001214","DCA000280","TODO BUS");
 */
    }

    /**
     * Constructor para crear una nueva unidad no existente.
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
    public int exist(String dominio){
        for (int i = 0; i < listaUnidades.length; i++) {
            if (listaUnidades[i].getDominio().contentEquals(dominio)) {
                return i;
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

    public void addNuevaUnidad(){
        int indice = nextDisponible();
        if(indice >= 0 &&  indice < listaUnidades.length) {
            listaUnidades[indice].setDominio(this.dominio);
            listaUnidades[indice].setModelo(this.modelo);
            listaUnidades[indice].setNroChasis(this.nroChasis);
            listaUnidades[indice].setNroMotor(this.nroMotor);
            listaUnidades[indice].setCarroceria(this.carroceria);
            listaUnidades[indice].setActivo();
        }
        else
            System.out.println("Unidad.addNuevaUnidad: No existe mas lugares donde insertar, disparar excepción !!");
    }

}
