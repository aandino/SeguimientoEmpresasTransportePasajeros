package GestionBusquedas;

// Clase ENTIDAD RevisionTecnica (RTO)

public class RevisionTecnica {
    private int nroTecnica;
    private String dominio ="";

    public RevisionTecnica(int nroTecnica) {
        this.nroTecnica = nroTecnica;
    }

    public RevisionTecnica(int nroTecnica, String dominio) {
        this.nroTecnica = nroTecnica;
        this.dominio = dominio;
    }

    public boolean existRTO() {
        String query = "select count(*) from RevisionTecnica where nroTecnica = '" +this.nroTecnica+"';";
        if(query == null){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isAprobe(){
        String query = "select count(*) from RevisionTecnica where aprobado ='1' and nroTecnica = '" +this.nroTecnica+"';";
        if(query != "0")
            return true;
        else
            return false;
    }
}
