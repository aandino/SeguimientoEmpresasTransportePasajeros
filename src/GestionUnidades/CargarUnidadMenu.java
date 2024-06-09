package GestionUnidades;
import java.util.Scanner;
import java.time.LocalDate;

public class CargarUnidadMenu {

    private Scanner scanner = new Scanner(System.in);
    private String itemDominio;
    private int itemNroInterno,itemNroRTO,itemModelo=0;
    private String itemCorredor,itemCuitEmpresa="";
    private String itemNroExpediente,itemNroResolucion="";
    private String nroChasis,nroMotor,nroCarroceria="";

    private CargarUnidad cargarUnidad;

    public CargarUnidadMenu() {
        System.out.println("--- MENU CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        try {
            this.itemDominio = scanner.nextLine();
            if (!validarDominio(this.itemDominio)) {
                System.out.println("Formato Esperado: 'ABC 123' !");
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
            if (!validarModelo(this.itemModelo)) {
                System.out.println("Formato Esperado: Año Fabricación Unidad !.");
                throw new IllegalArgumentException("ERROR Modelo inválido");
            }
        }
        catch (Exception e2) {
            System.out.println(e2.getMessage());
            return;
        }

        System.out.print("Nro Interno: ");
        try {
            this.itemNroInterno = scanner.nextInt();
            if(!validarNro(this.itemNroInterno)){
                System.out.println("Formato Esperado: Nro interno !");
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
            if(! validarCadena(this.itemCorredor)){
                System.out.println("Formato Esperado: Corredor Unidad !.");
                throw new IllegalArgumentException("ERROR Corredor inválido");
            }
        }
        catch (Exception e4) {
            System.out.println(e4.getMessage());
            return;
        }

        System.out.print("Nro RTO: ");
        try{
            this.itemNroRTO = scanner.nextInt();
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el nro de rto");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
        System.out.print("CUIT Empresa: ");
        try{
            this.itemCuitEmpresa = scanner.nextLine();
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el cuit empresa");
        }
        System.out.print("Nro Expediente: ");
        try{
            this.itemNroExpediente = scanner.nextLine();
        }
        catch (Exception e) {
            System.out.print("ERROR: el formato del expediente es incorrecto ");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
        System.out.print("Nro Resolución: ");
        try{
            this.itemNroResolucion = scanner.nextLine();
        }
        catch (Exception e) {
            System.out.print("ERROR: el formato del resolucion es incorrecto ");
            System.out.print("VOLVIENDO AL MENU PRINCIPAL.... ");
        }
// FALTA INICIALIZAR ,nroChasis,nroMotor,nroCarroceria ¡¡¡ OJO !!!!
        try {
            cargarUnidad = new CargarUnidad(this.itemDominio,this.itemModelo,
                    this.itemNroInterno,this.itemNroRTO,this.itemCorredor,
                    this.itemCuitEmpresa,this.itemNroExpediente,this.itemNroResolucion,
                    this.nroChasis,this.nroMotor,this.nroCarroceria);
            cargarUnidad.altaNuevaUnidad();
        }
        catch (Exception e) {
            System.out.println("ERROR: No se puede ingresar el nuevo unidad");
        }
    }

    public boolean validarDominio(String dominio) {
        return dominio != null && dominio.matches("[A-Z]{3} [0-9]{3}");
    }

    public boolean validarCorredor(String corredor) {
        return corredor != null && !corredor.trim().isEmpty() && corredor.length() <22;
    }

    public boolean validarModelo(int modelo) {
        int currentYear = LocalDate.now().getYear();
        return modelo > 0 && modelo <= currentYear ;
    }

    public boolean validarNro(int nroInterno) {
        return nroInterno > 0;
    }

    public static boolean validarNroRTO(int nroRTO) {
        return nroRTO > 0;
    }

    public static boolean validarCUITEmpresa(String cuitEmpresa) {
        return cuitEmpresa != null && cuitEmpresa.matches("\\d{2}-\\d{8}-\\d");
    }

    public static boolean validarCadena(String cadena) {
        return cadena != null && !cadena.trim().isEmpty();
    }

    public void display() {
        System.out.print("--- MENUA CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
        this.itemDominio = scanner.nextLine();
        System.out.print("Modelo: ");
        this.itemModelo = scanner.nextInt();
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
