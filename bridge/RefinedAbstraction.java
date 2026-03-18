// Abstraccion concreta
//
// Aqui defines comportamiento usando el implementor

public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operacion() {
        System.out.println("RefinedAbstraction delega:");
        implementor.operacionImpl();
    }
}
