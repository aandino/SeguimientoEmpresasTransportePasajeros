package GestionEntidades.BaseDatos;
import java.sql.*;


public class MysqlConect {
    private String usuario = "remoto";
    private String contrasena = "password0123";
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:33306/SeguimientoEmpresasTransportePasajeros";
    private static Connection conexion = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query ="";
    public static int cantConexiones = 0;

    /**
     * Solo me conecto a la BD la primera vez !!
     */
    public MysqlConect() {
        if(conexion == null) {
            try {
                Class.forName(this.driver);
                conexion = DriverManager.getConnection(url, usuario, contrasena);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("ERROR linea 26 MysqlConect.java: Error al conectar con la base de datos ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        /* cantidad de veces que se pido una conexión, puedo decrementarlo
        cada vez que termino de usar un query. Sí es cero debería cerrar
        la conexión a la DB.
        */
        cantConexiones++;
    }


    public ResultSet runExist(String tabla, String inColumna, String matchValue){
        String query ="";
        if (tabla.contentEquals("Empresa")) {
            query = "SELECT activa FROM "+tabla+" WHERE "+inColumna+"="+matchValue+" AND (activa = 0 OR activa = 1)";
        }else if (tabla.contentEquals("Unidad")) {
            query = "SELECT activa FROM "+tabla+" WHERE "+inColumna+"='"+matchValue+"' AND (activa = 0 OR activa = 1)";
        }else {
            throw new RuntimeException("ERROR MysqlConnect linea 47: TABLA NO MATCH");
        }
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR MysqlConect linea 54 ");
        }
    }

    public ResultSet runQuery(String colResult,String tabla, String matchColumn, int matchValue){
        String query ="";
        if(tabla.contentEquals("RevisionTecnica")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Contrato")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;;
        }else if (tabla.contentEquals("Empresa")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Unidad")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Flota")) {
            query = "SELECT " + colResult + " FROM " + tabla + " WHERE " + matchColumn + "=" + matchValue;
        }else {
            throw new RuntimeException("ERROR MysqlConnect linea 71: TABLA NO MATCH");
        }
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
    //        pstm.setString(1, colResult);
    //        pstm.setString(2, tabla);
    //        pstm.setString(2, matchColumn);
    //        pstm.setInt(2, matchValue);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR linea 82 MysqlConect.java !!! ");
        }
    }

    public ResultSet runQuery(String colResult,String tabla, String matchColumn, String matchValue){
        String query ="";
        if(tabla.contentEquals("RevisionTecnica")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Contrato")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Empresa")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Unidad")) {
            query = "SELECT "+colResult+" FROM "+tabla+" WHERE "+matchColumn+"="+matchValue;
        }else if (tabla.contentEquals("Flota")) {
            query = "SELECT " + colResult + " FROM " + tabla + " WHERE " + matchColumn + "=" + matchValue;
        }else {
            throw new RuntimeException("ERROR MysqlConnect linea 99: TABLA NO MATCH");
        }
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            //pstm.setString(1, colResult);
            //pstm.setString(2, tabla);
            //pstm.setString(2, matchColumn);
            //pstm.setString(3, matchValue);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR linea 110 MysqlConect !! ");
        }
    }

    public ResultSet runQuery(String colResult,String tabla){
        String query = "SELECT "+colResult+" FROM "+tabla;
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR linea 121 MysqlConect !! ");
        }
    }

    public int runInsertUnidadFlota(String tabla, String inColumna, String valores){
        String insert = "INSERT INTO "+tabla+" ("+inColumna+") VALUES ("+valores+")";
        try{
            ps = conexion.prepareStatement(insert);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }
    }

    public int runInsertNuevaUnidad(String tabla, String inColumna, String valores){
        String insert = "INSERT INTO "+tabla+" ("+inColumna+") VALUES ("+valores+")";
        try{
            ps = conexion.prepareStatement(insert);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }

    }

    public int runUpdateActivo(String tabla, String inColumna, String criterio){
        int resultado = -1;
        String update = "UPDATE "+tabla+" SET activa = 1 WHERE "+inColumna+"="+criterio;
        try{
            ps = conexion.prepareStatement(update);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return resultado;
    }

}
