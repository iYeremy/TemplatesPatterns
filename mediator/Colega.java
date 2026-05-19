// Colega base: conoce al mediador
//
// Importante:
// - Se comunica con otros colegas SOLO a traves del mediador
// - No conoce directamente a otros colegas

public abstract class Colega {

    protected Mediador mediador;

    public Colega(Mediador mediador) {
        this.mediador = mediador;
    }
}
