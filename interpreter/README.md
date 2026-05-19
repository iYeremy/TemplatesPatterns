# Patrón de Diseño: Interpreter

### Intérprete (Interpreter)

### 2. ¿Qué problema resuelve?

Imagina que quieres crear un lenguaje simple para evaluar expresiones matemáticas o consultas. En lugar de escribir un parser complejo desde cero, el patrón Interpreter te permite representar cada regla del lenguaje como una clase. Así, puedes evaluar expresiones creando un árbol de objetos que representan la gramática del lenguaje.

### 3. Idea principal (explicación corta)

El patrón Interpreter define una representación para la gramática de un lenguaje y un intérprete que usa esa representación para evaluar sentencias en el lenguaje. Cada regla gramatical se convierte en una clase.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines la gramática del lenguaje.** Qué expresiones son válidas y cómo se combinan.
2.  **Creas la interfaz Expresion.** Con un método `interpretar(Contexto)`.
3.  **Creas expresiones terminales.** Representan los elementos básicos (variables, números).
4.  **Creas expresiones no terminales.** Combinan otras expresiones (suma, resta, etc.).
5.  **El Contexto** almacena información global (valores de variables).
6.  **Construyes un árbol de expresiones** y llamas a `interpretar()` en la raíz.

### 5. Estructura (clases y relaciones)

*   **Contexto:**
    *   Contiene información global que las expresiones necesitan.
    *   Puede ser un mapa de variables, un stack, etc.
    *   *Ejemplo:* `Contexto` con variables `x`, `y`
*   **Expresion (AbstractExpression):**
    *   Interfaz que declara `interpretar(Contexto)`.
    *   *Ejemplo:* `Expresion`
*   **ExpresionTerminal (TerminalExpression):**
    *   Representa un elemento básico del lenguaje.
    *   No contiene otras expresiones.
    *   *Ejemplo:* `ExpresionTerminal` (variable "x")
*   **ExpresionNoTerminal (NonterminalExpression):**
    *   Representa una regla gramatical que combina expresiones.
    *   Contiene referencias a otras expresiones (hijos).
    *   *Ejemplo:* `ExpresionSuma`, `ExpresionResta`

**Relación:** Las `ExpresionNoTerminal` contienen otras `Expresion` (terminales o no terminales). Todas implementan `interpretar(Contexto)`.

### 6. Mini ejemplo

Vamos a crear un intérprete para expresiones booleanas simples.

```java
// 1. Contexto: almacena valores de variables
class Contexto {
    private Map<String, Boolean> variables = new HashMap<>();

    public void set(String nombre, boolean valor) { variables.put(nombre, valor); }
    public boolean get(String nombre) { return variables.getOrDefault(nombre, false); }
}

// 2. Expresion: interfaz
interface Expresion {
    boolean interpretar(Contexto contexto);
}

// 3. Expresion Terminal: variable
class Variable implements Expresion {
    private String nombre;

    public Variable(String nombre) { this.nombre = nombre; }

    @Override
    public boolean interpretar(Contexto contexto) {
        return contexto.get(nombre);
    }
}

// 4. Expresiones No Terminales
class AND implements Expresion {
    private Expresion izq, der;

    public AND(Expresion izq, Expresion der) { this.izq = izq; this.der = der; }

    @Override
    public boolean interpretar(Contexto contexto) {
        return izq.interpretar(contexto) && der.interpretar(contexto);
    }
}

class OR implements Expresion {
    private Expresion izq, der;

    public OR(Expresion izq, Expresion der) { this.izq = izq; this.der = der; }

    @Override
    public boolean interpretar(Contexto contexto) {
        return izq.interpretar(contexto) || der.interpretar(contexto);
    }
}

// 5. Uso en Main
public class Main {
    public static void main(String[] args) {
        Contexto ctx = new Contexto();
        ctx.set("a", true);
        ctx.set("b", false);

        // a AND b
        Expresion and = new AND(new Variable("a"), new Variable("b"));
        System.out.println("a AND b = " + and.interpretar(ctx));

        // a OR b
        Expresion or = new OR(new Variable("a"), new Variable("b"));
        System.out.println("a OR b = " + or.interpretar(ctx));
    }
}
```

En este ejemplo:
*   **`Contexto`** almacena los valores de las variables booleanas.
*   **`Variable`** es la expresión terminal.
*   **`AND`** y **`OR`** son expresiones no terminales que combinan otras expresiones.
*   Se construye un **árbol de expresiones** y se evalúa desde la raíz.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevas expresiones:** NOT, XOR, multiplicación, etc.
    *   **El Contexto:** Agregar más información o funciones auxiliares.
    *   **Optimizar:** Cache de resultados, evaluación perezosa.
*   **No debes cambiar:**
    *   **La interfaz `Expresion`:** Es el contrato que todas las expresiones deben seguir.
    *   **La estructura de árbol:** Cada expresión debe delegar correctamente a sus hijos.

### 8. Cuándo usarlo (muy importante)

*   **Cuando tienes un lenguaje simple que interpretar:** Expresiones matemáticas, consultas, reglas.
*   **Cuando la gramática es simple:** No uses este patrón para lenguajes complejos (mejor usar parser generators).
*   **Cuando la eficiencia no es crítica:** El patrón crea muchos objetos pequeños.
*   **Cuando quieres que la gramática sea extensible:** Agregar nuevas reglas es fácil (nueva clase).

### 9. Resumen en una frase

El patrón Interpreter representa cada regla de un lenguaje como una clase, permitiendo evaluar expresiones construyendo un árbol de objetos que interpretan la gramática del lenguaje.
