// Visitor concreto: implementa operaciones para cada elemento
//
// Importante:
// - Aqui va la logica de las operaciones
// - Puedes agregar mas Visitors sin cambiar los Elements

public class VisitorConcreto implements Visitor {

    @Override
    public void visitElementA(ElementConcretoA elemento) {
        System.out.println("Visitando ElementoA: " + elemento.getAtributoA());
    }

    @Override
    public void visitElementB(ElementConcretoB elemento) {
        System.out.println("Visitando ElementoB con valor: " + elemento.getAtributoB());
    }
}
