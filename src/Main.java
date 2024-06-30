/**
 * {@summary }
 *
 * La aplicación, en esta primera iteración, se centra en la implementación
 * del CU07: "CargarUnidad". Para llevar la ejecución, se debe tener en cuenta
 * como pre-requisitos las siguientes reglas del negocio:
 *
 * a) Una empresa debe tener un contrato activo/vigente con la organización.
 * Esto se refleja en la relación que existe en la tabla "Contrato.idEmpresa"
 * con la tabla Empresa.idEmpresa"
 * A su vez, por cada contrato existe "una flota" de vehículos asociada a esta.
 * Una flota está formada por uno o más unidades, lo que termina de cerrar la
 * relación Empresa -> Contrato -> Flota -> Unidad. Haciendo que se cumpla
 * por transitividad Empresa -> Unidad. Que representa la pertenencia de una
 * unidad pertenece a una "empresa".
 *
 * b) Una RevisionTécnica (RTO) es emitida como paso previo a que se cargue
 * una nueva unidad en el sistema. Por lo tanto, es pre-requisito que el nro
 * de técnica ya se encuentre cargado en el sistema al momento de realizar él
 * CU. Esto se ve reflejado en la tabla RevisionTecnica. Esta tabla representa
 * la relación que existe entre la clase RevisionTécnica y Unidad.
 *
 * c) La tabla Flota, como se mencionó representa la relación de agregación
 * que se representaba en el diagrama del dominio entre Unidad - Flota - Empresa.
 *
 * @author Sergio Alejandro Andino.
 */

import GestionInterfaces.MainMenu;

public class Main {

    public static void main(String[] args) {


        new MainMenu().display();

        }
    }
