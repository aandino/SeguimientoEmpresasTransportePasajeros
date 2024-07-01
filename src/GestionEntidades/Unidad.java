package GestionEntidades;
import GestionEntidades.BaseDatos.MysqlConect;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase Entidad que representa a la tabla "Unidad" del modelo de datos,
 * por lo que será la responsable de interactuar con esta.
 * Será la encargada de conectar, select, update sobre la tabla UNIDAD.
 */
public class Unidad {
    private String dominio = null;
    private  int modelo =-1;
    private String nroChasis =null;
    private String nroMotor = null;
    private String carroceria = null;
    private int activo = -1;

    /**
     * Constructor para crear una nueva unidad no existente.
     *
     */
    public Unidad(String dominio,int modelo,
                  String nroChasis,String nroMotor, String carroceria) {
        this.dominio = dominio;
        this.modelo = modelo;
        this.nroChasis = nroChasis;
        this.nroMotor = nroMotor;
        this.carroceria = carroceria;
        this.activo = 1;
    }

    /**
     * Polimorfismo.
     * Constructor para cuando "solo" se van a realizar consultas
     * sobre una unidad determinada, exista o no.
     * @param dominio
     */
    public Unidad(String dominio) {
        this.dominio = dominio;
    }

    public String getDominio() {
        return dominio;
    }

    public String getNroChasis() {
        return this.nroChasis;
    }

    public boolean getActivo() {
        return activo == 1;
    }

    public int getModelo() {
        return modelo;
    }

    public void setActivo() {
        this.activo = 1;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setNroChasis(String nroChasis) {
        this.nroChasis = nroChasis;
    }

    public void setNroMotor(String nroMotor) {
        this.nroMotor = nroMotor;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    /**
     * Quiero saber si la unidad esta/estuvo registrada en el sistema.
     * @return -1,0,1 donde: -1 no existe, 0 es INactiva, 1 esta activa.
     */
    public int exist(String inDominio) {
        try {
            MysqlConect con = new MysqlConect();
            ResultSet resultado = con.runExist("Unidad", "dominio", inDominio);
            if (resultado.next())
                return (resultado.getInt("activa"));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return -1;
    }
    /**
     * Quiero saber si la unidad esta/estuvo registrada en el sistema.
     * @return -1,0,1 donde: -1 no existe, 0 es INactiva, 1 esta activa.
     */
    public int exist() {
        try {
            MysqlConect con = new MysqlConect();
            ResultSet resultado = con.runExist("Unidad", "dominio", this.dominio);
            if (resultado.next())
                return (resultado.getInt("activa"));
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return -1;
    }
    /**
     * Quiero saber si la unidad existe en el sistema "y" además está activa.
     * @return true unidad activa, false unidad no existe o no esta asociada a ninguna empresa.
     */
    public boolean isActive(String dominio){
        int testigo = exist(dominio);
        return(testigo == 1);
    }

    /**
     * Activo una unidad que en algún momento estuvo vinculada a una
     * empresa. Debe existir en el sistema y estar inactiva = 0.
     */
    public int setUnidadActiva(){
        int exito = -1;
        try {
            if(isActive(this.dominio))
                throw new SQLException("Unidad.setUnidadActiva linea 154: OJO LA UNIDAD YA ESTA ACTIVA !!");
            MysqlConect con = new MysqlConect();
            exito = con.runUpdateActivo("Unidad","dominio",this.dominio);
             if(exito !=1)
                 throw new SQLException("Unidad.setUnidadActiva 158:NO SE PUEDO ACTUALIZAR UNIDAD !!");
             else
                 return exito;
        }catch (SQLException sql){
            System.out.println(sql.getMessage());
            sql.printStackTrace();
            return exito;
        }
    }

    /**
     * @summary actualiza el estado de una unidad inactiva a activa ó inserta esta en el
     * sistema si no ha existido nunca.
     * @return nro de filas actualizadas o insertadas
     */
    public int addUnidad(){
        //Evito que se llame de forma incorrecta a este método.
        if(this.dominio == null || this.modelo == -1 || this.nroChasis == null||
            this.nroMotor == null || this.carroceria == null)
            throw new RuntimeException("UNIDAD 171: SE ESPERA QUE USE UN CONSTRUCTOR CON TODOS LOS PARÁMETROS !!");
        int resultado = -1;
        int activa = exist();
        if(activa ==0) // existe una entrada en la tabla Unidad pero está inactiva
            return(setUnidadActiva());
        else if (activa < 0) { // no existe esta unidad en la tabla Unidad.
            String tabla = "Unidad";
            String columnas="";
            String valores = "";
            columnas +="dominio,modelo,nroChasis,nroMotor,carroceria,activa";
            valores +="'"+this.dominio+"','"+this.modelo+"','"+this.nroChasis+"','"+this.nroMotor+"','"+this.carroceria+"',1";
            MysqlConect conect = new MysqlConect();
            resultado = conect.runInsertNuevaUnidad(tabla,columnas,valores);
            return resultado;
        }else
            throw new RuntimeException("UNIDAD 186: NO SE PUDO INSERTAR NUEVA UNIDAD ACTIVA = 1 ");
    }

    /**
     * @summary futuro método que devolverá una colección de DTO.
     */
    public void getAll(){
        try {
            MysqlConect conect = new MysqlConect();
            ResultSet resultSet = conect.runQuery("dominio,modelo", "Unidad", "activa", 1);
            System.out.println("");
            System.out.println("------ UNIDADES ACTIVAS ------------");
            System.out.println("");
            while (resultSet.next()) {
                System.out.println("Domino: " + resultSet.getString("dominio")+" | Modelo: " + resultSet.getInt("modelo"));
                System.out.println("");
            }
            System.out.println("");
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }

}
