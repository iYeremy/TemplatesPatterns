// Elemento concreto B
//
// Misma estructura que A pero con su propia logica
// Tambien hace double dispatch al visitor

public class ElementConcretoB implements Element {

    private int atributoB;

    public ElementConcretoB(int atributoB) {
        this.atributoB = atributoB;
    }

    public int getAtributoB() {
        return atributoB;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitElementB(this);
    }
}
