package GestionInterfaces;
import GestionUnidades.CargarUnidad;
import java.util.Scanner;


/**
 *@summary
 * Clase boundary/interface encargada de transmitir los datos a la capa "controladora"
 * para realizar los cálculos correspondientes a la carga y asociación de una unidad
 * a la flota de una empresa.
 * La clase será la responsable de obtener la información del operador del sistema y
 * validar los datos ingresados cuando el mismo esté realizando "CU07-CargarUnidad".
 *
 */

/**
 * Como toda clase de tipo "meú" hereda de la clase Menú.
 */
public class CargarUnidadMenu extends Menu {

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


    public CargarUnidadMenu() {
    }

    /**
     * Sobre escritura del método heredado Menu.display().
     * El método en este caso será el encargado de solicitar
     * los datos al operador para iniciar el CU07.
     */
    public void display() {
        System.out.println("Metodo de la clase Menu ");
        System.out.println("--- MENU CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        /**
         * Se realiza un control estricto de los datos ingresados, utilizando la funcionalidad
         * heredada de la clase base "Menu".
         * Se utilizan excepciones para manejar los errores de formato de los datos ingresados
         * y mantener bajo control el flujo de ejecución del programa.
         */
        try {
            this.itemDominio = scanner.nextLine().trim();
            if (!validarDominio(this.itemDominio)) {
                System.out.println("Formato Esperado: 'ABC123' !");
                throw new IllegalArgumentException("ERROR Dominio inválido");
            }
        } catch (Exception e1) {
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
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
            return;
        }

        System.out.print("Nro Interno: ");
        try {
            this.itemNroInterno = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea
            if (!validarNro(this.itemNroInterno)) {
                throw new IllegalArgumentException("ERROR Nro Interno inválido");
            }
        } catch (IllegalArgumentException e3) {
            System.out.println(e3.getMessage());
            return;
        }

        System.out.print("Corredor: ");
        try {
            this.itemCorredor = scanner.nextLine();
            if (!validarCadena(this.itemCorredor)) {
                throw new IllegalArgumentException("ERROR Corredor invalido");
            }
        } catch (Exception e4) {
            System.out.println(e4.getMessage());
            return;
        }

        System.out.print("Nro RTO: ");
        try {
            this.itemNroRTO = scanner.nextInt();
            scanner.nextLine();
            if (!validarNro(this.itemNroRTO)) {
                throw new IllegalArgumentException("ERROR Nro RTO invalido.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("CUIT Empresa: ");
        try {
            this.itemCuitEmpresa = scanner.nextLine();
            if (!validarCuitEmpresa(this.itemCuitEmpresa))
                throw new IllegalArgumentException("ERROR CuitEmpresa: debe ser de 11 digitos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Expediente: ");
        try {
            this.itemNroExpediente = scanner.nextLine();
            if (!validarExpediente(this.itemNroExpediente))
                throw new IllegalArgumentException("ERROR formato EXP: EXP-NnNnNnN/nN");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Resolución: ");
        try {
            this.itemNroResolucion = scanner.nextLine();
            if (!validarResolucion(this.itemNroResolucion))
                throw new IllegalArgumentException("ERROR formato Resolución: NnN/nN");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Chasis: ");
        try {
            this.nroChasis = scanner.nextLine();
            if (!validarCadena(this.nroChasis))
                throw new IllegalArgumentException("ERROR formato Nro Chasis");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Nro Motor: ");
        try {
            this.nroMotor = scanner.nextLine();
            if (!validarCadena(this.nroMotor))
                throw new IllegalArgumentException("ERROR formato Nro Motor");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.print("Carroceria: ");
        try {
            this.carroceria = scanner.nextLine();
            if (!validarCadena(this.carroceria))
                throw new IllegalArgumentException("ERROR formato Carrocería");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            cargarUnidad = new CargarUnidad(this.itemDominio, this.itemModelo,
                    this.itemNroInterno, this.itemNroRTO, this.itemCorredor,
                    this.itemCuitEmpresa, this.itemNroExpediente, this.itemNroResolucion,
                    this.nroChasis, this.nroMotor, this.carroceria);
            cargarUnidad.altaNuevaUnidad();

        } catch (Exception e) {
            //System.out.println("ERROR CargarUnidadMenu.java: 201");
            System.out.println(e.getMessage());
            return;
        }
    }
}
