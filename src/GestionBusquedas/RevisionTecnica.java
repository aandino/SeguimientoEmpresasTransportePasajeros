package GestionBusquedas;

// Clase ENTIDAD RevisionTecnica (RTO)

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

    public int existRTO(int nroTecnica) {
        for (int i = 0; i < listaRTO.length; i++) {
            if (listaRTO[i].getNroTecnica() == nroTecnica) {
                return i;
            }
        }
        return -1;
    }

    public boolean isAprobe(int nroTecnica){
        int indice = existRTO(nroTecnica);
        if(indice >= 0)
            return listaRTO[indice].getRtoAprobada() ;
        else
            return false;
    }
}
