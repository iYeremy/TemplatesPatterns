// Otro prototype concreto: muestra como cada clase
// implementa su propia logica de clonado

public class ConcretePrototypeB implements Prototype {

    private int valor;

    public ConcretePrototypeB(int valor) {
        this.valor = valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototypeB(this.valor);
    }

    @Override
    public String toString() {
        return "ConcretePrototypeB: " + valor;
    }
}
