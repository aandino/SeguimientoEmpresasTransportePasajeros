package GestionEntidades.BaseDatos;
import java.sql.*;


public class MysqlConect {
    private String usuario = "remoto";
    private String contrasena = "password0123";
    private String driver = "com.mysql.jdbc.Driver";
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
                throw new RuntimeException("Error al conectar con la base de datos");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        /* cantidad de veces que se pido una conexión, puedo decrementarlo
        cada vez que termino de usar un query. Sí es cero debería cerrar
        la conexión a la DB.
        */
        cantConexiones++;
    }


    public ResultSet runExist(String tabla, String inColumna, String match){
        String query = "SELECT activa FROM ? WHERE ? = ? AND (activa = 0 OR activa = 1)";
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            pstm.setString(1, tabla);
            pstm.setString(2, inColumna);
            pstm.setString(3, match);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR en funcion MysqlConect.java->runExist en la base de datos");
        }
    }

    public ResultSet runQuery(String colResult,String tabla, String matchColumn, int matchValue){
        String query = "SELECT ? FROM ? WHERE ? = ? ";
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            pstm.setString(1, colResult);
            pstm.setString(2, tabla);
            pstm.setString(3, matchColumn);
            pstm.setInt(4, matchValue);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR en funcion MysqlConect.java->runExist linea 64");
        }
    }

    public ResultSet runQuery(String colResult,String tabla, String matchColumn, String matchValue){
        String query = "SELECT ? FROM ? WHERE ? = ? ";
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            pstm.setString(1, colResult);
            pstm.setString(2, tabla);
            pstm.setString(3, matchColumn);
            pstm.setString(4, matchValue);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR en funcion MysqlConect.java->runExist linea 64");
        }
    }

    public ResultSet runQuery(String colResult,String tabla){
        String query = "SELECT ? FROM ?";
        try {
            PreparedStatement pstm = conexion.prepareStatement(query);
            pstm.setString(1, colResult);
            pstm.setString(2, tabla);
            return(pstm.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR en funcion MysqlConect.java->runExist linea 64");
        }
    }

    public int runInsertUnidadFlota(String tabla, String inColumna, String valores){
        String insert = "INSERT INTO ? (?) VALUES (?)";
        try{
            ps = conexion.prepareStatement(insert);
            ps.setString(1, tabla);
            ps.setString(2, inColumna);
            ps.setString(3, valores);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }
    }

    public int runInsertNuevaUnidad(String tabla, String inColumna, String valores){
        String insert = "INSERT INTO ? (?) VALUES (?)";
        try{
            ps = conexion.prepareStatement(insert);
            ps.setString(1, tabla);
            ps.setString(2, inColumna);
            ps.setString(3, valores);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
            return -1;
        }

    }

    public int runUpdateActivo(String tabla, String inColumna, String criterio){
        int resultado = -1;
        String query = "UPDATE ? SET activa = 1 WHERE ? = ?";
        try{
            ps = conexion.prepareStatement(query);
            ps.setString(1, tabla);
            ps.setString(2, inColumna);
            ps.setString(3, criterio);
            return(ps.executeUpdate());
        }catch (SQLException sql){
            sql.printStackTrace();
        }
        return resultado;
    }

}
