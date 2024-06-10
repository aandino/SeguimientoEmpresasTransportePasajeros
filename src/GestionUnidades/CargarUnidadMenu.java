/**
 * Set de Prueba para dar de alta una unidad.
 *
 * Pre-Requisito:
 *  Debe estar cargada la relación Unidad-RevisionTecnica.
 *  Para este caso se suministra la relación en:
 *  RevisionTecnica.listaRTO[4] = new RevisionTecnica(2606,"AA381BB",120,1,1);
 *  Donde se refleja que la unidad con dominio:AA381BB se le asignó el
 *  nro de técnica "2606", la cual está aprobada, bit 1 final.
 *  El sistema no permite cargar una nueva unidad que no haya pasado
 *  el proceso de revision técnica obligatoria, es decir un registro
 *  RevisionTecnica.listaRTO.
 *
 *  Datos Unidad:
 *      Dominio: AA381BB
 *      Modelo: 2016
 *      Nro. Interno: 120
 *      Corredor: San Luis - La Carolina
 *      Nro. Revision Técnica: 2606
 *      CUIT: 30710760965
 *      EXP de Alta: EXP-1290150/22
 *      Resolución: 44/11
 *      Nro. Chasis: 8BBC51A1AGM001214
 *      Nro. Motor: DCA000280
 *      Carrocería: TODOBUS
 */

package GestionUnidades;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * A posteriori todas las clases de tipo menu heredarán (extend) a una
 * clase superior Menu.java todas las funcionalidades comunes, como los
 * métodos referentes a la validación de entradas de usuarios.
 */

public class CargarUnidadMenu {

    private Scanner scanner = new Scanner(System.in);
    private String itemDominio;
    private int itemNroInterno;
    private int itemNroRTO;
    private int itemModelo;
    private String itemCorredor;
    private String itemCuitEmpresa;
    private String itemNroExpediente;
    private String itemNroResolucion;
    private String nroChasis;
    private String nroMotor;
    private String carroceria;

    private CargarUnidad cargarUnidad;

    /**
     * El constructor la clase CargarUnidadMenu.java es el encargado
     * de mostrar el menu para que el usuario pueda ingresar los datos
     * necesarios para la que la aplicación inicie la carga de una nueva unidad.
     */
    public CargarUnidadMenu() {
        System.out.println("--- MENU CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        /**
         * Se utilizan excepciones para validar los datos ingresados
         * y mantener bajo control el flujo de ejecución del programa.
         */
        try {
            this.itemDominio = scanner.nextLine().trim();
            if (!validarDominio(this.itemDominio)) {
                System.out.println("Formato Esperado: 'ABC123' !");
                throw new IllegalArgumentException("ERROR Dominio inválido");
            }
        }
        catch (Exception e1) {
            System.out.println(e1.getMessage());
            return;
        }
        System.out.print("Modelo: ");
        try {
            this.itemModelo = scanner.nextInt();
            scanner.nextLine();
            if (!validarModelo(this.itemModelo)) {
                throw new IllegalArgumentException("Año en curso < [Fabricación Unidad] > 2000");
            }
        }
        catch (Exception e2) {
            System.out.println(e2.getMessage());
            return;
        }

        System.out.print("Nro Interno: ");
        try {
            this.itemNroInterno = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            if(!validarNro(this.itemNroInterno)){
                throw new IllegalArgumentException("ERROR Nro Interno inválido");
            }
        }
        catch (IllegalArgumentException e3) {
            System.out.println(e3.getMessage());
            return;
        }

        System.out.print("Corredor: ");
        try {
            this.itemCorredor = scanner.nextLine();
            if(!validarCadena(this.itemCorredor)){
                throw new IllegalArgumentException("ERROR Corredor invalido");
            }
        }
        catch (Exception e4) {
            System.out.println(e4.getMessage());
            return;
        }

        System.out.print("Nro RTO: ");
        try{
            this.itemNroRTO = scanner.nextInt();
            scanner.nextLine();
            if(!validarNro(this.itemNroRTO)){
                throw new IllegalArgumentException("ERROR Nro RTO invalido.");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("CUIT Empresa: ");
        try{
            this.itemCuitEmpresa = scanner.nextLine();
            if(!validarCuitEmpresa(this.itemCuitEmpresa))
                throw new IllegalArgumentException("ERROR CuitEmpresa: debe ser de 11 digitos");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Expediente: ");
        try{
            this.itemNroExpediente = scanner.nextLine();
            if(!validarExpediente(this.itemNroExpediente))
                   throw new IllegalArgumentException("ERROR formato EXP: EXP-NnNnNnN/nN");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Resolución: ");
        try{
            this.itemNroResolucion = scanner.nextLine();
            if(!validarResolucion(this.itemNroResolucion))
                throw new IllegalArgumentException("ERROR formato Resolución: NnN/nN");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Chasis: ");
        try{
            this.nroChasis = scanner.nextLine();
            if(!validarCadena(this.nroChasis))
                throw new IllegalArgumentException("ERROR formato Nro Chasis");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Motor: ");
        try{
            this.nroMotor = scanner.nextLine();
            if(!validarCadena(this.nroMotor))
                throw new IllegalArgumentException("ERROR formato Nro Motor");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Carroceria: ");
        try{
            this.carroceria = scanner.nextLine();
            if(!validarCadena(this.carroceria))
                throw new IllegalArgumentException("ERROR formato Carrocería");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            cargarUnidad = new CargarUnidad(this.itemDominio,this.itemModelo,
                    this.itemNroInterno,this.itemNroRTO,this.itemCorredor,
                    this.itemCuitEmpresa,this.itemNroExpediente,this.itemNroResolucion,
                    this.nroChasis,this.nroMotor,this.carroceria);
            cargarUnidad.altaNuevaUnidad();

        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar la nueva unidad");
            System.out.println(e.getMessage());
            return;
        }
    }

    /**
     * A posteriori todas las clases de tipo menu heredarán (extend) a una
     * clase superior Menu.java todas las funcionalidades comunes, como los
     * métodos referentes a la validación de entradas de usuarios.
     */

    /**
     * Cada funcionalidad
     * @param dominio, corredor, modelo, nroInterno: todos deben coincidir con su
     *                 formato y tipo establecido en los parámetros de clase.
     * @return true: se cumple las condiciones de formato de datos según sea necesario.
     */
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
