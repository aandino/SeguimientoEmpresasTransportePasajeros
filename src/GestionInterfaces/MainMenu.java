package GestionInterfaces;
import java.util.Scanner;
import GestionUnidades.*;


/**
 * @summary
 * La clase GestionInterfaces.MainMenu: será la primera interacción del usuario
 * con el sistema. El acceso a los demás CU que se irá implementando de manera
 * incremental.
 * Por el momento solo se provee la funcionalidad necesaria para el CU07.
 */
public class MainMenu extends Menu{
    private Scanner scanner = new Scanner(System.in);

    public MainMenu() {
    }

    /**
     * Sobre escritura del método heredado Menu.display()
     */
    public void display() {
        while (true) {
            System.out.println("+------------------+");
            System.out.println("|                  |");
            System.out.println("|  Menu Principal  |");
            System.out.println("|                  |");
            System.out.println("+------------------+");
            System.out.println("1) Cargar Nueva Unidad");
            System.out.println("2) Mostar RESULTADOS");
            System.out.println("0) SALIR ");
            System.out.print("Leer opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 0:
                    return;
                case 1:
                    clearConsole();
                    new CargarUnidadMenu().display();
                    break;
                case 2:
                    //clearConsole();
                    new BuscarMenu().display();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
