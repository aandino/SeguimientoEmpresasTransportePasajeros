package GestionUnidades;

import GestionInterfaces.Buscar;
import GestionEntidades.Unidad;

public class BuscarUnidad extends Buscar {
    private String dominio;
    private String cuitEmpresa;
    Unidad unidad;

    public BuscarUnidad(String dominio) {
        super();
        this.dominio = dominio;
        this.unidad = new Unidad(this.dominio);
    }

    public BuscarUnidad(String dominio, String cuitEmpresa) {
        this.dominio = dominio;
        this.unidad = new Unidad(this.dominio);
        this.cuitEmpresa = cuitEmpresa;
    }

    public BuscarUnidad() {
        this.dominio = "";
        this.cuitEmpresa = "";
    }

}
