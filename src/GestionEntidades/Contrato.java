package GestionEntidades;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import GestionEntidades.BaseDatos.MysqlConect;

public class Contrato {
    private int idContrato;
    private String cuitEmpresa = null;
    private String nroExpAltaEmpresa = null;
    private String nroExpBajaEmpresa = null;
    private String nroResolucion = null;
    private LocalDate fechaInicioContrato = null;
    private String fechaFinalizacionContrato = null;
    private String  dniGerente = null;
    private String nombreApellidoGerente = null;

    public Contrato(String cuitEmpresa) {
        this.cuitEmpresa = cuitEmpresa;
    }

    public Contrato(String cuitEmpresa, String nroExpAltaEmpresa,
                    String nroExpBajaEmpresa, String nroResolucion,
                    LocalDate fechaInicioContrato, String fechaFinalizacionContrato,
                    String dniGerente, String nombreApellidoGerente) {
        this.idContrato = idContrato;
        this.cuitEmpresa = cuitEmpresa;
        this.nroExpAltaEmpresa = nroExpAltaEmpresa;
        this.nroExpBajaEmpresa = nroExpBajaEmpresa;
        this.nroResolucion = nroResolucion;
        this.fechaInicioContrato = fechaInicioContrato;
        this.fechaFinalizacionContrato = fechaFinalizacionContrato;
        this.dniGerente = dniGerente;
        this.nombreApellidoGerente = nombreApellidoGerente;
    }

    /**
     *
     * @return idContrato, 0 -> si no existe un contrato o no hay un contrato vigente.
     */
    public int getIdContrato() {
        String where ="nroExpBajaEmpresa IS NULL AND Empresa_cuil";
        try {
            MysqlConect conect = new MysqlConect();
            ResultSet resultado = conect.runQuery("idContrato", "Contrato",where, this.cuitEmpresa);
            if (resultado.next())
                return (resultado.getInt("idContrato"));
            else
                //
                return 0;
        } catch (SQLException sql) {
            sql.printStackTrace();
            return -1;
        }
    }
}
