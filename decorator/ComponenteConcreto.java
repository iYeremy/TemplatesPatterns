// Componente concreto base
//
// Este es el objeto original que vamos a decorar
// No sabe nada de los decorators

public class ComponenteConcreto implements Componente {

    @Override
    public String operacion() {
        return "Componente Base";
    }
}
