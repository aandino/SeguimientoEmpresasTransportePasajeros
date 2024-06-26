package GestionInterfaces;
import GestionUnidades.BuscarUnidad;
import java.util.Scanner;


public class BuscarMenu extends Menu{
    private Scanner scanner = new Scanner(System.in);

    public void display(){
        System.out.println("+-----------------+");
        System.out.println("|    MOSTRAR      |");
        System.out.println("+-----------------+");
        System.out.println("");
        System.out.println("1) MOSTRAR UNIDADES");
        System.out.println("2) MOSTRAR FLOTA");
//        System.out.println("3) MOSTARAR EMPRESAS");
//        System.out.println("3) MOSTARAR CONTRATOS");
//        System.out.println("3) MOSTARAR EMPLEADOS");
//        System.out.println("3) BUSCAR UNIDAD");
//        System.out.println("3) BUSCAR EMPRESA-FLOTA ");

        System.out.print("Leer opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        switch (opcion) {
            case 1:
                new BuscarUnidad().getAllUnidades();
                break;
            case 2:
                new BuscarUnidad().getAllFlota();
                break;
            case 3:
                //clearConsole();

                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
        }
    }
}
