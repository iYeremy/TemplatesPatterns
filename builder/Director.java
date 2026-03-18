// Director: define el orden de construccion (este es algo opcional, no complicarse la vida implementandolo)
//
// Importante:
// - NO construye directamente
// - Solo indica el proceso

public class Director {

    public void construir(Builder builder) {
        builder.buildParteA();
        builder.buildParteB();
        builder.buildParteC();
    }
}
