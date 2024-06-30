package GestionInterfaces;
import java.time.LocalDate;


/**
 * Clase abstracta padre para todas las clases de tipo "menu"
 * Esta provee de métodos comunes de validación
 * de entradas para todas las subclases
 */

public abstract class Menu {
    /**
     * Constructor NULO.
     */
    public Menu() {
    }

    /**
     * Se define un contrato para todas las subclases redefinan
     * "sobre escribán" este método según su contexto.
     */
    public abstract void display();

    /**
     * Validación de los datos de entrada.
     * @return true: se cumple las condiciones de formato de datos según sea necesario.
     */

    /**
     * Método para limpiar las salidas de consola agregando nuevas líneas.
     */
    public void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    /**
     * Válida el formato de entrada de los dominios/patentes que se ingresen.
     * @param dominio cadena de longitud [6-8] cáracteres con valores de A-Z y 0-9.
     * @return true si el valor cumple con la condición.
     */
    public boolean validarDominio(String dominio) {
        return( dominio != null && dominio.matches("[A-Z0-9]{6,8}"));
    }

    /**
     * Válida el formato de entrada del corredor de un vehículo que se ingrese.
     * @param corredor cadena no vacía de a lo sumo 22 caracteres de longitud.
     * @return true si la cadena cumple las condiciones
     */
    public boolean validarCorredor(String corredor) {
        return( corredor != null && !corredor.trim().isEmpty() && corredor.length() <22);
    }

    /**
     * Válida el formato de entrada del año de fabricación del vehículo.
     * @param modelo debe ser un valor > al año 2000 y menos al año corriente en curso.
     * @return tru si cumple las condiciones.
     */
    public boolean validarModelo(int modelo) {
        int currentYear = LocalDate.now().getYear();
        return( modelo > 2000 && modelo <= currentYear);
    }

    /**
     * Válida el formato de entrada del número de interno.
     * @param nroInterno se espera un número menos 9999
     * @return tru si cumple las condiciones.
     */
    public boolean validarNro(int nroInterno) {
        return( nroInterno > 0 && nroInterno <= 9999);
    }

    /**
     * Válida el formato de entrada del número
     * @param nroRTO se espera un número menos 9999
     * @return tru si cumple las condiciones.
     */
    public boolean validarNroRTO(int nroRTO) {
        return( nroRTO > 0 && nroRTO <= 9999);
    }

    /**
     * Válida el formato de entrada del número de CUIT por lo general 11 caracteres de
     * longitud, sin guiones.
     * @param cuitEmpresa valor alfanumérico de 11 caracteres de longitud.
     * @return tru si cumple las condiciones.
     */
    public boolean validarCuitEmpresa(String cuitEmpresa) {
        return (cuitEmpresa != null && cuitEmpresa.matches("[0-9]{11}"));
    }
    /**
     * Válida el formato de entrada del EXPEDIENTE del sistema general del gobierno
     * comienza con EXP- seguido de 7 dígitos una "/" más 2 dígitos más.
     * @param nroExpediente valor alfanumérico de 11 caracteres de longitud.
     * @return tru si cumple las condiciones.
     */
    public boolean validarExpediente(String nroExpediente){
        return(nroExpediente != null && nroExpediente.matches("^EXP-\\d{7}/\\d{2}$") );
    }
    /**
     * Válida el formato de entrada de resolución generada por el propio ente en libros notaraiales
     * para tal fin, por lo general un número que indica la secuencia seguído del año.
     * @param NroResolucion valor alfanumérico de 3 a 5 caracteres de longitud.
     * @return tru si cumple las condiciones.
     */
    public boolean validarResolucion(String NroResolucion){
        return(NroResolucion != null && NroResolucion.matches("^\\d{1,3}/\\d{2}$") );
    }

    /**
     * Control de entrada para evitar entradas nulas y con longitud mayor a 30 cáracteres.
     * @param cadena
     * @return
     */
    public boolean validarCadena(String cadena) {
        return (cadena != null && !cadena.trim().isEmpty() && cadena.length() <30);
    }

}
