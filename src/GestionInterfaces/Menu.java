package GestionInterfaces;

import java.time.LocalDate;

public class Menu {

    public Menu() {
    }

    /**
     * Validación de los datos de entrada.
     * @return true: se cumple las condiciones de formato de datos según sea necesario.
     */
    public void display(){

    }
    /**
     * Método para limpiar las salidas de consola agregando nuevas lineas.
     */
    public void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public boolean validarDominio(String dominio) {
        return( dominio != null && dominio.matches("[A-Z0-9]{6,8}"));
    }

    public boolean validarCorredor(String corredor) {
        return( corredor != null && !corredor.trim().isEmpty() && corredor.length() <22);
    }

    public boolean validarModelo(int modelo) {
        int currentYear = LocalDate.now().getYear();
        return( modelo > 2000 && modelo <= currentYear);
    }

    public boolean validarNro(int nroInterno) {
        return( nroInterno > 0 && nroInterno <= 9999);
    }

    public boolean validarNroRTO(int nroRTO) {
        return( nroRTO > 0 && nroRTO <= 9999);
    }

    public boolean validarCuitEmpresa(String cuitEmpresa) {
        return (cuitEmpresa != null && cuitEmpresa.matches("[0-9]{11}"));
    }

    public boolean validarExpediente(String nroExpediente){
        return(nroExpediente != null && nroExpediente.matches("^EXP-\\d{7}/\\d{2}$") );
    }

    public boolean validarResolucion(String NroResolucion){
        return(NroResolucion != null && NroResolucion.matches("^\\d{1,3}/\\d{2}$") );
    }

    public boolean validarCadena(String cadena) {
        return (cadena != null && !cadena.trim().isEmpty() && cadena.length() <30);
    }

}
