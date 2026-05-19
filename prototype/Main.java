// Prueba del patron Prototype

public class Main {
    public static void main(String[] args) {

        // Crear prototipo original
        ConcretePrototypeA original = new ConcretePrototypeA("Original");
        System.out.println("Original: " + original);

        // Clonar sin conocer la clase concreta
        Prototype copia = original.clone();
        System.out.println("Copia: " + copia);

        // Modificar la copia no afecta al original
        if (copia instanceof ConcretePrototypeA) {
            ((ConcretePrototypeA) copia).setAtributo("Modificado");
        }
        System.out.println("Copia modificada: " + copia);
        System.out.println("Original despues: " + original);

        // Tambien funciona con otros prototypes
        ConcretePrototypeB protoB = new ConcretePrototypeB(42);
        Prototype copiaB = protoB.clone();
        System.out.println("PrototypeB copia: " + copiaB);
    }
}
