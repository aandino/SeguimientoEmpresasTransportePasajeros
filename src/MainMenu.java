import java.util.Scanner;
import GestionUnidades.*;

/**
 * A posteriori todas las clases de tipo menu heredarán (extemd) a una
 * clase superior Menu.java todas las funcionalidades comunes, como los
 * métodos referentes a la validación de entradas de usuarios.
 */

/**
 * La clase MainMenu: será la primera interacción con el usuario, la que dara
 * el acceso a los demás CU que se vayan implementando.
 */
public class MainMenu {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Método para limpiar las salidas de consola agregando nuevas lineas.
     */
    public void clearConsole() {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    /**
     * @summary
     */
    public void display() {
        while (true) {
            System.out.println("===================");
            System.out.println("");
            System.out.println("Menu Principal");
            System.out.println("");
            System.out.println("1) Cargar Nueva Unidad");
            System.out.println("2) Mostar Unidades");
            System.out.println("3) Mostrar Flota");
            System.out.print("Leer opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

            switch (opcion) {
                case 1:
                    clearConsole();
                    new CargarUnidadMenu();
                    break;
                case 2:
                    //clearConsole();
                    CargarUnidad.imprimirUnidades();
                    break;
                case 3:
                    //clearConsole();
                    CargarUnidad.imprimirFlota();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}
