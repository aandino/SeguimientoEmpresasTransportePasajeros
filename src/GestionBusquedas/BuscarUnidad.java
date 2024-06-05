package GestionBusquedas;

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

    /**
     * Verifica si la unidad existe ó ha existido
     * @return false: no existe o esta inactiva.
     */
    public boolean existeUnidad() {
        return this.unidad.exist();
    }

    public boolean isActive(){
        return unidad.isActive();
    }
}
