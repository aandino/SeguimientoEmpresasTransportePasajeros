package GestionBusquedas;

public class Empresa {

    private String cuit, razonSocial, domicilioComercial, emailEmpresa;
    private int activa;

    public Empresa(String cuit, String razonSocial, String domicilioComercial, String emailEmpresa, int activa) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.domicilioComercial = domicilioComercial;
        this.emailEmpresa = emailEmpresa;
        this.activa = activa;
    }

}


