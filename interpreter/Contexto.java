// Contexto: contiene informacion global del interpreter
//
// Idea:
// - Almacena datos que las expresiones pueden necesitar
// - Puede ser un stack, un mapa de variables, etc.

import java.util.HashMap;
import java.util.Map;

public class Contexto {

    private Map<String, Integer> variables;

    public Contexto() {
        this.variables = new HashMap<>();
    }

    public void setVariable(String nombre, int valor) {
        variables.put(nombre, valor);
    }

    public int getVariable(String nombre) {
        return variables.getOrDefault(nombre, 0);
    }
}
