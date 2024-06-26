package GestionRTO;

// Este es la clase controlador BuscarRTO

import GestionEntidades.RevisionTecnica;

public class BuscarRTO {
    private int nroTecnica;
    private RevisionTecnica Rto;
    private String dominio ="";

    public BuscarRTO(int nroTecnica, String dominio) {

        this.nroTecnica = nroTecnica;
        this.Rto = new RevisionTecnica(nroTecnica, dominio);
    }


    /**
     * Verifica si el nro de comprobante de rto (revision tecnica obligatoria)
     * ha sido aprobada.
     * @return true: en caso afirmativo.
     */
    public boolean verifyRTO(){
        if((this.Rto.isAprobe() == 1)) {
            return true;
        }
        else {
            return false;
        }
    }

}
