// Decorador concreto A
//
// Agrega comportamiento ANTES o DESPUES de delegar
// Puede envolver cualquier Componente (base u otro decorador)

public class DecoradorConcretoA extends Decorador {

    public DecoradorConcretoA(Componente componente) {
        super(componente);
    }

    @Override
    public String operacion() {
        return componente.operacion() + " + DecoradorA";
    }
}
