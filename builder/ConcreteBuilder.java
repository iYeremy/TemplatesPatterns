// Implementacion concreta del Builder
//
// Aqui se define COMO se construye el producto

public class ConcreteBuilder implements Builder {

    private Producto producto;

    public ConcreteBuilder() {
        this.producto = new Producto();
    }

    @Override
    public void buildParteA() {
        producto.setParteA("Parte A construida");
    }

    @Override
    public void buildParteB() {
        producto.setParteB("Parte B construida");
    }

    @Override
    public void buildParteC() {
        producto.setParteC("Parte C construida");
    }

    @Override
    public Producto getResultado() {
        return producto;
    }
}
