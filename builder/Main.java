// Prueba del patron Builder

public class Main {
    public static void main(String[] args) {

        Builder builder = new ConcreteBuilder();
        Director director = new Director();

        // El director controla el proceso
        director.construir(builder);

        Producto producto = builder.getResultado();

        System.out.println(producto);
    }
}
