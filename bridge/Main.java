// Prueba del patron Bridge

//Abstracción = lo visible / lo que usas
//Implementación = lo interno / lo que ejecuta

public class Main {
    public static void main(String[] args) {

        // Combinar abstraccion con implementacion A
        Abstraction abs1 = new RefinedAbstraction(new ConcreteImplementorA());
        abs1.operacion();

        // Cambiar implementacion en tiempo de ejecucion
        Abstraction abs2 = new RefinedAbstraction(new ConcreteImplementorB());
        abs2.operacion();
    }
}
