// Clase de prueba para Strategy

public class Main {
    public static void main(String[] args) {

        // Usar estrategia A
        Context contexto = new Context(new ConcreteStrategyA());
        contexto.ejecutarEstrategia();

        // Cambiar a estrategia B en tiempo de ejecucion
        contexto.setStrategy(new ConcreteStrategyB());
        contexto.ejecutarEstrategia();
    }
}
