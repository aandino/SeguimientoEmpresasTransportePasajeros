/**
 * {@summary }
 * La aplicación se centra en la implementación del CU07: "CargarUnidad"
 * Para llevar la ejecución, se debe tener en cuenta como pre-requisitos
 * las siguientes reglas del negocio:
 *
 * a) Una empresa debe estar en estado activa == 1, lo que significa
 * que la empresa tine un contrato vigente con la organización. Una
 * unidad pertenece a una "empresa", puntualmente a su flota, por lo que no
 * puede existir estas relaciones si no existe una empresa.activa ==1. Es por
 * ello que se encuentra:
 * "public static Empresa[] listaEmpresas = new Empresa[2];"
 *
 * b) Una RevisionTécnica (RTO) es emitida como paso previo a que se cargue
 * una nueva unidad en el sistema. Por lo tanto, es pre-requisito que el nro
 * de técnica ya se encuentre cargado en el sistema al momento de realizar él
 * CU. Es por ello que se encuentra:
 * public static RevisionTecnica[] listaRTO = new RevisionTecnica[6];
 * a modo de representar la relación que existe entre la clase RevisionTécnica
 * y Unidad.
 *
 * c) public static Flota[] listaFlota = new Flota[10]; representa la relación
 * de agregación que existe entre Unidad - Flota - Empresa. Donde flota unifica
 * los identificadores principales de Unidad-Empresa, haciendo posible relacionarlos
 * entre sí.
 *
 * Los índices han sido seteados a modo de permitir la agregación de unidades a la
 * flota ya existente de una empresa. No asi empresas o RTO ya que son partes de
 * otros CU.
 *
 * Las relaciones, ahora arrays estáticos, se verán reflejadas cuando sea el turno
 * de implementar la persistencia en la base de datos.
 *
 * @author Alejandro Andino.
 */

/**
 * Set de Prueba para dar de alta una unidad.
 *
 * Pre-Requisito:
 *  Debe estar cargada la relación Unidad-RevisionTecnica.
 *  Para este caso se suministra la relación en:
 *  RevisionTecnica.listaRTO[4] = new RevisionTecnica(2606,"AA381BB",120,1,1);
 *  Donde se refleja que la unidad con dominio:AA381BB se le asignó el
 *  nro de técnica "2606", la cual está aprobada, bit 1 final.
 *  El sistema no permite cargar una nueva unidad que no haya pasado
 *  el proceso de revision técnica obligatoria, es decir un registro
 *  RevisionTecnica.listaRTO.
 *
 *  Datos Unidad:
 *      Dominio: AA381BB
 *      Modelo: 2016
 *      Nro. Interno: 120
 *      Corredor: San Luis - La Carolina
 *      Nro. Revision Técnica: 2606
 *      CUIT: 30710760965
 *      EXP de Alta: EXP-1290150/22
 *      Resolución: 44/11
 *      Nro. Chasis: 8BBC51A1AGM001214
 *      Nro. Motor: DCA000280
 *      Carrocería: TODOBUS
 */

public class Main {

    public static void main(String[] args) {


        new MainMenu().display();

        }
    }
