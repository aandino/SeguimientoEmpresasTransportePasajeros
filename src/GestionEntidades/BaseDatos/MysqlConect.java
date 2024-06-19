package GestionEntidades.BaseDatos;
import java.sql.*;


public class MysqlConect {
    private String usuario = "remoto";
    private String contrasena = "password0123";
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:33306/SeguimientoEmpresasTransportePasajeros";
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public MysqlConect() {
        try{
            Class.forName(this.driver);
            con = DriverManager.getConnection(url,usuario,contrasena);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
