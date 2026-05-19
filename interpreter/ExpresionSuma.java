// Expresion no terminal: combina otras expresiones
//
// Importante:
// - Contiene otras expresiones (hijos)
// - Delega la interpretacion a sus hijos y combina resultados

public class ExpresionSuma implements Expresion {

    private Expresion izquierda;
    private Expresion derecha;

    public ExpresionSuma(Expresion izquierda, Expresion derecha) {
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return izquierda.interpretar(contexto) + derecha.interpretar(contexto);
    }
}
