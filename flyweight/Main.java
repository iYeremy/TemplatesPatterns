// Prueba del patron Flyweight

public class Main {
    public static void main(String[] args) {

        FlyweightFactory factory = new FlyweightFactory();

        // Reutiliza objetos con misma clave
        Flyweight f1 = factory.getFlyweight("A");
        Flyweight f2 = factory.getFlyweight("A"); // MISMO objeto

        Flyweight f3 = factory.getFlyweight("B");

        f1.operar("posicion 1");
        f2.operar("posicion 2");
        f3.operar("posicion 3");
    }
}
