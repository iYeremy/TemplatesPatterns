// Decorador concreto B
//
// Otra decoracion independiente que se puede combinar
// Se puede encadenar con otros decorators

public class DecoradorConcretoB extends Decorador {

    public DecoradorConcretoB(Componente componente) {
        super(componente);
    }

    @Override
    public String operacion() {
        return componente.operacion() + " + DecoradorB";
    }
}
