// Decorador base: clase abstracta que implementa Componente
//
// Importante:
// - Mantiene una referencia a otro Componente
// - Delega la operacion al componente envuelto

public abstract class Decorador implements Componente {

    protected Componente componente;

    public Decorador(Componente componente) {
        this.componente = componente;
    }

    @Override
    public String operacion() {
        return componente.operacion();
    }
}
