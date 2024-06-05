package GestionBusquedas;

// Este es la clase controlador BuscarRTO

public class BuscarRTO extends  Buscar{
    private RevisionTecnica nroRto;
    private String dominio ="";

    public BuscarRTO(int nroTecnica) {
        super();
        this.nroRto = new RevisionTecnica(nroTecnica);
    }

    public BuscarRTO(int nroTecnica, String dominio) {
        super();
        this.nroRto = nroRto;
        this.dominio = dominio;
    }

    /**
     * Verifica si el nro de comprobante de rto (revision tecnica obligatoria)
     * ha sido aprobada.
     * @return true: en caso afirmativo.
     */
    public boolean queryRTO(){
        if (this.nroRto.existRTO() && this.nroRto.isAprobe())
            return true;
        else
            return false;
    }

}
