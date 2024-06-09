package GestionBusquedas;
import java.time.LocalDate;

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

    public Flota(String cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public String getCuitEmpresa() {
        return this.cuitEmpresa;
    }

    public int getIdFlota() {
        return this.idFlota;
    }

    public String getNroExpBajaUnidad() {
        return this.nroExpBajaUnidad;
    }

    public LocalDate getFechaBajaUnidad() {
        return this.fechaBajaUnidad;
    }

    public void setCuitEmpresa(String cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public void setDominioUnidad(String dominioUnidad) {
        this.dominioUnidad = dominioUnidad;
    }

    public void setIdFlota(int idFlota) {
        this.idFlota = idFlota;
    }

    public void setNroExpAltaUnidad(String nroExpAltaUnidad) {
        this.nroExpAltaUnidad = nroExpAltaUnidad;
    }

    public void setNroExpBajaUnidad(String nroExpBajaUnidad) {
        this.nroExpBajaUnidad = nroExpBajaUnidad;
    }

    public void setFechaAltaUnidad(LocalDate fechaAltaUnidad) {
        this.fechaAltaUnidad = fechaAltaUnidad;
    }

    public void setFechaBajaUnidad(LocalDate fechaBajaUnidad) {
        this.fechaBajaUnidad = fechaBajaUnidad;
    }

    public void setNroResolucionAlta(String nroResolucionAlta) {
        this.nroResolucionAlta = nroResolucionAlta;
    }

    public void setNroResolucionBaja(String nroResolucionBaja) {
        this.nroResolucionBaja = nroResolucionBaja;
    }

    public void setCorredor(String corredor) {
        this.corredor = corredor;
    }

    public void setNroInterno(int nroInterno) {
        this.nroInterno = nroInterno;
    }

    public int getIdFlota(String inCuitEmpresa){
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
     * ESTA MAL SE DEBE EJECUTAR DESDE LA CLASE CONTROLADORA NO DESDE ACA !!!!
     */
    public void addUnidadFlota(){
        int indice = nextDisponible();
        int idFlota = getIdFlota(this.dominioUnidad);

        if(indice >= 0 &&  idFlota >0) {
            listaFlota[indice].setCuitEmpresa(this.cuitEmpresa);
            listaFlota[indice].setDominioUnidad(this.dominioUnidad);
            listaFlota[indice].setIdFlota(idFlota);
            listaFlota[indice].setNroExpAltaUnidad(this.nroExpAltaUnidad);
            listaFlota[indice].setNroResolucionAlta(this.nroResolucionAlta);
            listaFlota[indice].setCorredor(this.corredor);
            listaFlota[indice].setNroInterno(this.nroInterno);
        }
        else if(indice < 0)
            System.out.println("No existe mas lugares donde insertar, disparar excepción !!");
        else if (idFlota < 0)
            System.out.println("No se encontro unidades para activas en la flota para esa empresaa, disparar excepción !!");
    }

}
