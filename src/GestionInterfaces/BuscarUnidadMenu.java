package GestionInterfaces;

import java.util.Scanner;

public class BuscarUnidadMenu {
    private Scanner scanner = new Scanner(System.in);

    public void display() {
        System.out.print("Dominio: ");
        String dominio = scanner.nextLine();

        System.out.println("Buscar/Cancelar: ");
        String decision = scanner.nextLine();
        if ("Buscar".equalsIgnoreCase(decision)) {
            // Lógica para buscar la unidad
            System.out.println("Resultado de la búsqueda.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}
