// Prueba del patron Visitor

public class Main {
    public static void main(String[] args) {

        ElementConcretoA elemA = new ElementConcretoA("Dato A");
        ElementConcretoB elemB = new ElementConcretoB(123);

        Visitor visitor = new VisitorConcreto();

        // Los elementos aceptan el visitor
        elemA.accept(visitor);
        elemB.accept(visitor);
    }
}
