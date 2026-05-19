// Elemento concreto A
//
// Implementa accept llamando al metodo correcto del visitor
// Esto se llama "double dispatch"

public class ElementConcretoA implements Element {

    private String atributoA;

    public ElementConcretoA(String atributoA) {
        this.atributoA = atributoA;
    }

    public String getAtributoA() {
        return atributoA;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementA(this);
    }
}
