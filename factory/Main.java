// Prueba del patron Factory

public class Main {
    public static void main(String[] args) {

        Producto productoA = Factory.crearProducto("a");
        productoA.usar();

        Producto productoB = Factory.crearProducto("b");
        productoB.usar();
    }
}
