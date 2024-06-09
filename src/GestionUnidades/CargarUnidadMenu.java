package GestionUnidades;
import java.util.Scanner;
import java.time.LocalDate;

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

    public CargarUnidadMenu() {
        System.out.println("--- MENU CARGAR UNIDAD ---");
        System.out.println("");
        System.out.print("Dominio: ");
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
