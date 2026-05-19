// Expresion terminal: representa un valor o variable
//
// No contiene otras expresiones dentro
// Es la unidad basica del lenguaje

public class ExpresionTerminal implements Expresion {

    private String variable;

    public ExpresionTerminal(String variable) {
        this.variable = variable;
    }

    @Override
    public int interpretar(Contexto contexto) {
        return contexto.getVariable(variable);
    }
}
