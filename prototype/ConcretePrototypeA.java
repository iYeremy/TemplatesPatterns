// Prototype concreto A: implementa clone
//
// Importante:
// - Debe hacer una copia profunda si tiene objetos mutables
// - El cliente no necesita saber la clase concreta para clonar

public class ConcretePrototypeA implements Prototype {

    private String atributo;

    public ConcretePrototypeA(String atributo) {
        this.atributo = atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }

    @Override
    public Prototype clone() {
        // Crear nueva instancia con los mismos valores
        return new ConcretePrototypeA(this.atributo);
    }

    @Override
    public String toString() {
        return "ConcretePrototypeA: " + atributo;
    }
}
