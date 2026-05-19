// Visitor: define operaciones para cada elemento concreto
//
// Idea:
// - Permite agregar operaciones sin cambiar las clases de elementos
// - Cada visitante conoce el tipo exacto del elemento

public interface Visitor {
    void visitElementA(ElementConcretoA elemento);
    void visitElementB(ElementConcretoB elemento);
}
