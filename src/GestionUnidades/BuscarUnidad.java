package GestionUnidades;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import GestionEntidades.Unidad;
import GestionEntidades.BaseDatos.MysqlConect;

/**
 * Clase controladora es parte del CU10: "GestionarBúsquedas" !!
 * Esta clase "no" es parte del CU07:CargarUnidad, solo se ha desarrollado parte
 * de la funcionalidad para que muestre los resultados de las operaciones del CU10.
 *
 */

public class BuscarUnidad {
    private String dominio;
    private String cuitEmpresa;
    private Scanner scanner = new Scanner(System.in);

    public BuscarUnidad(String dominio, String cuitEmpresa) {
        this.dominio = dominio;
        this.cuitEmpresa = cuitEmpresa;
    }

    public BuscarUnidad() {
        this.dominio = "*";
        this.cuitEmpresa = "";
    }

    public void getAllUnidades(){
        try {
            MysqlConect conect = new MysqlConect();
            ResultSet resultSet = conect.runQuery("*", "Unidad", "activa", 1);
            System.out.println("");
            System.out.println("------ UNIDADES ACTIVAS ------------");
            System.out.println("");
            while (resultSet.next()) {
                System.out.print("Domino: " + resultSet.getString("dominio")+" | Modelo: " + resultSet.getInt("modelo")+" | ");
                System.out.println("NroChasis: " + resultSet.getString("nroChasis")+" | Carroceria: " + resultSet.getString("carroceria"));
                System.out.println("------------------------------------------");
            }
            System.out.println("ENTER PARA CONTINUAR ....");
            scanner.nextLine();
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    public void getAllFlota(){
        String columResultado = "idContrato,cuit, razonSocial,Unidad_dominio,IdFlota,nroInterno,corredor,fechaAltaUnidad";
        String fromTables ="Flota, (select cuit,razonSocial,idContrato from Empresa emp, Contrato cont where emp.cuit = cont.Empresa_cuil) EMP";
        String where ="Flota.Contrato_idContrato";
        String match = "EMP.idContrato order by idContrato";
        try {
            MysqlConect conect = new MysqlConect();
            ResultSet resultSet = conect.runQuery(columResultado, fromTables, where, match);
            System.out.println("");
            System.out.println("---------------------------------    FLOTA ACTIVA     --------------------------------------------------------------------");
            System.out.println("");
            while (resultSet.next()) {
                System.out.print("idContrato: "+ resultSet.getInt("idContrato")+" | CUIT: " + resultSet.getString("cuit")+" | ");
                System.out.print("Empresa: "+ resultSet.getString("razonSocial")+" | Dominio: " + resultSet.getString("Unidad_dominio")+" | ");
                System.out.println("Interno: "+ resultSet.getInt("nroInterno")+" | corredor: " + resultSet.getString("corredor"));
                System.out.println("-----------------------------------------------------------------------------------------------------------------------");
            }
            System.out.println("ENTER PARA CONTINUAR ....");
            scanner.nextLine();
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }


}
