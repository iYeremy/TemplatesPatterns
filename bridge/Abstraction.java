// Parte ABSTRACCION
//
// Relacion clave:
// - Tiene una referencia a Implementor (composicion)
//
// Idea:
// - Separa "que hago" de "como se hace"

public abstract class Abstraction {

    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operacion();
}
