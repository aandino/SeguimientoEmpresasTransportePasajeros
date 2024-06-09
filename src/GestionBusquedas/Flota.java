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
}
