package GestionBusquedas;

// Este es la clase controlador BuscarRTO

public class BuscarRTO extends  Buscar{
    private int nroTecnica;
    private RevisionTecnica Rto;
    private String dominio ="";

    public BuscarRTO(int nroTecnica) {
        super();
        this.nroTecnica = nroTecnica;
        this.Rto = new RevisionTecnica(nroTecnica);
    }

    public BuscarRTO(int nroTecnica, String dominio) {
        super();
        this.nroTecnica = nroTecnica;
        this.dominio = dominio;
        this.Rto = new RevisionTecnica(nroTecnica);
    }

    /**
     * Verifica si el nro de comprobante de rto (revision tecnica obligatoria)
     * ha sido aprobada.
     * @return true: en caso afirmativo.
     */
    public boolean verifyRTO(){
        if(this.Rto.isAprobe(this.nroTecnica)) {
            return true;
        }
        else {
            return false;
        }
    }

}
