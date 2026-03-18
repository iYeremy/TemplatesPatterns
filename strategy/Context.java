// Contexto: clase que usa una estrategia
//
// Relacion clave:
// - Tiene una referencia a Strategy (composicion)
// - NO sabe cual implementacion concreta usa
//
// Idea:
// - Delega el comportamiento a la estrategia

public class Context {

    private Strategy strategy;

    // Se puede inyectar por constructor (buena practica)
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    // Tambien se puede cambiar en tiempo de ejecucion
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void ejecutarEstrategia() {
        strategy.ejecutar();
    }
}
