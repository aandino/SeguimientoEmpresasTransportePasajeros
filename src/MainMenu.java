import java.util.Scanner;
import java.io.IOException;
import GestionUnidades.CargarUnidadMenu;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);

    public static void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    public void display() {
        while (true) {
            System.out.println("===================");
            System.out.println("");
            System.out.println("Menu Principal");
            System.out.println("");
            System.out.println("1) Cargar Nueva Unidad");
            System.out.println("2) Buscar Unidad");
            System.out.println("3) Cargar Contrato");
            System.out.print("Leer opcion: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    clearConsole();
                    new CargarUnidadMenu();
                    break;
                case 2:
                    clearConsole();
                    //new SearchMenu().display();
                    System.out.print("Display MENU BUSCAR UNIDDAD: ");
                    System.out.println("");
                    break;
                case 3:
                    //new LoadContractMenu().display();
                    System.out.print("Display MENU CARGAR CONTRATO: ");
                    System.out.println("");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
