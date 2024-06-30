package GestionInterfaces;
import GestionUnidades.BuscarUnidad;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @summary
 * Clase representa el RF10: "El sistema debe permitir realizar búsquedas sobre unidades, empresas y
 * el personal de esta"
 * El cual es representado por el CU10: "GestionarBúsquedas", el cual "no" es parte de esta iteración.
 *
 * A fines prácticos, de mostrar los resultados que se suceden con el "CU10:CargarUnidad"
 * de esta primera iteración, se ha desarrollado parte de la funcionalidad del CU10.
 */

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
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea
            switch (opcion) {
                case 1:
                    new BuscarUnidad().getAllUnidades();
                    break;
                case 2:
                    new BuscarUnidad().getAllFlota();
                    break;
                default:
                    System.out.println("OPCIÓN NO VALIDA.");
            }
        }catch (InputMismatchException in){
            System.out.println("VALOR OPCIÓN INCORRECTA: DEBE SER UN NUMERO !!! ");
            return;
        }catch (Exception x){
            System.out.println("REVISE LA CONEXIÓN A LA BD !!!  ");
            return;
        }
    }
}
