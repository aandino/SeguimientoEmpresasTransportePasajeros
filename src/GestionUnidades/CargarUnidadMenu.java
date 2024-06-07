package GestionUnidades;
import java.util.Scanner;
public class CargarUnidadMenu {

    private Scanner scanner = new Scanner(System.in);
    private String itemDominio,itemModelo;
    private int itemNroInterno,itemNroRTO;
    private String itemCorredor,itemCuitEmpresa;
    private String itemNroExpediente,itemNroResolucion;
    private String nroChasis,nroMotor,nroCarroceria;

    private CargarUnidad nuevaUndiad;

    public CargarUnidadMenu() {
        System.out.print("--- MENUA CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        try {
            this.itemDominio = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el dominio");
        }
        System.out.print("Modelo: ");
        try {
            this.itemModelo = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el modelo");
        }
        System.out.print("Nro Interno: ");
        try {
            this.itemNroInterno = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el nro de interno");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }

        System.out.print("Corredor: ");
        try {
            this.itemCorredor = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el corredor");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
        System.out.print("Nro RTO: ");
        try{
            this.itemNroRTO = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el nro de rto");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
        System.out.print("CUIT Empresa: ");
        try{
            this.itemCuitEmpresa = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el cuit empresa");
        }
        System.out.print("Nro Expediente: ");
        try{
            this.itemNroExpediente = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.print("ERROR: el formato del expediente es incorrecto ");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
        System.out.print("Nro Resolución: ");
        try{
            this.itemNroResolucion = scanner.nextLine();
            scanner.nextLine();  // Consumir nueva línea
        }
        catch (Exception e) {
            System.out.print("ERROR: el formato del resolucion es incorrecto ");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
// FALTA INICIALIZAR ,nroChasis,nroMotor,nroCarroceria ¡¡¡ OJO !!!!
        try {
            nuevaUndiad = new CargarUnidad();
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el nuevo unidad");
        }
    }

    public void display() {
        System.out.print("--- MENUA CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        this.itemDominio = scanner.nextLine();
        System.out.print("Modelo: ");
        this.itemModelo = scanner.nextLine();
        System.out.print("Nro Interno: ");
        this.itemNroInterno = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("Corredor: ");
        this.itemCorredor = scanner.nextLine();
        System.out.print("Nro RTO: ");
        this.itemNroRTO = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea
        System.out.print("CUIT Empresa: ");
        this.itemCuitEmpresa = scanner.nextLine();
        System.out.print("Nro Expediente: ");
        this.itemNroExpediente = scanner.nextLine();
        System.out.print("Nro Resolución: ");
        this.itemNroResolucion = scanner.nextLine();

        System.out.println("Cargar/Cancelar: ");
        String decision = scanner.nextLine();
        if ("Cargar".equalsIgnoreCase(decision)) {
            // Lógica para cargar la unidad
            System.out.println("Unidad cargada.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}
