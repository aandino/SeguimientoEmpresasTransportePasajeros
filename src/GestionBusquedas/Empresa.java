package GestionBusquedas;

public class Empresa {

    private String cuit;
    private String razonSocial;
    private String domicilioComercial;
    private String emailEmpresa;
    private int activa;

    public static Empresa[] listaEmpresas = new Empresa[2];

    static{
        listaEmpresas[0] = new Empresa("30700786206","Blanca Paloma srl","av. libertador general san martin 91",
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
     * Solo para cuando quiero saber información sobre una empresa
     * teniendo como único dato su "cuit"
     */
    public Empresa(String cuit) {
        this.cuit = cuit;
    }

    public String getCuit() {
        return cuit;
    }

    public boolean getActiva() {
        return activa == 1;
    }

    /**
     * Quiero saber si la empresa esta/estuvo registrada en el sistema.
     */

    public int exist(String ciut) {
        for (int i = 0; i < listaEmpresas.length; i++) {
            if (listaEmpresas[i].getCuit().equals(ciut)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Quiero saber si la empresa además de existir en el sistema está activa.
     */
    public boolean isActive(String ciut){
        int indice = exist(ciut);
        if(indice >= 0){
            if (listaEmpresas[indice].getActiva())
                return true;
            else
                return false;
        } else
            return false;
        // Deberia lanzar una excepción: la empresa no se encuentra en la lista !!!
    }

}


