// Prueba del patron Interpreter

public class Main {
    public static void main(String[] args) {

        Contexto contexto = new Contexto();
        contexto.setVariable("x", 10);
        contexto.setVariable("y", 5);

        // x + y
        Expresion suma = new ExpresionSuma(
            new ExpresionTerminal("x"),
            new ExpresionTerminal("y")
        );

        // (x + y) - y
        Expresion resta = new ExpresionResta(
            suma,
            new ExpresionTerminal("y")
        );

        System.out.println("x + y = " + suma.interpretar(contexto));
        System.out.println("(x + y) - y = " + resta.interpretar(contexto));
    }
}
