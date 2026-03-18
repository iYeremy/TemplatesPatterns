# Patrón de Diseño: Strategy

### Estrategia (Strategy)

### 2. ¿Qué problema resuelve?

Imagina que estás construyendo una aplicación de carrito de compras. Los clientes pueden pagar de diferentes maneras: con tarjeta de crédito, PayPal, transferencia bancaria, etc. Si intentas manejar todas estas opciones de pago dentro de la misma clase `CarritoDeCompras` con un montón de `if/else if` o un `switch` para cada método de pago, la clase se volverá gigante, difícil de entender y de extender. Cada vez que añades un nuevo método de pago, tienes que modificar esa clase `CarritoDeCompras`.

El patrón Strategy resuelve este problema.

### 3. Idea principal (explicación corta)

El patrón Strategy te permite definir una familia de algoritmos, encapsular cada uno de ellos como un objeto separado y hacerlos intercambiables. Esto permite que el algoritmo varíe independientemente de los clientes que lo usan.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes una "acción" o "comportamiento" que puede realizarse de varias maneras.** Por ejemplo, "procesar pago".
2.  **Creas una interfaz común para todas esas maneras de hacer la acción.** A esta la llamamos "Estrategia" (Strategy). Por ejemplo, `MetodoDePago`.
3.  **Implementas cada una de las "maneras" como una clase separada.** A estas las llamamos "Estrategias Concretas" (Concrete Strategies). Por ejemplo, `PagoConTarjeta`, `PagoConPayPal`.
4.  **Tienes una clase "Contexto" (Context) que necesita realizar esa acción.** El `CarritoDeCompras` necesita procesar un pago.
5.  **El Contexto *no* implementa la acción directamente, sino que tiene una referencia a un objeto de tipo Estrategia.** El `CarritoDeCompras` tiene una referencia a un `MetodoDePago`.
6.  **El Contexto delega la ejecución de la acción a la Estrategia que tiene asignada.** El `CarritoDeCompras` llama a `metodoDePago.pagar(monto)`.
7.  **Puedes cambiar la Estrategia en tiempo de ejecución.** Un `CarritoDeCompras` puede cambiar de `PagoConTarjeta` a `PagoConPayPal` fácilmente.

¡Así, el Contexto no necesita saber cómo se realiza el pago, solo que alguien lo hará!

### 5. Estructura (clases y relaciones)

*   **Strategy (Estrategia):**
    *   Declara una interfaz común para todos los algoritmos soportados.
    *   El `Context` usa esta interfaz para llamar al algoritmo definido por una `ConcreteStrategy`.
    *   *Ejemplo:* `ComportamientoDeVuelo`, `MetodoDePago`
*   **ConcreteStrategy (Estrategia Concreta):**
    *   Implementa el algoritmo definido por la interfaz `Strategy`.
    *   *Ejemplo:* `VuelaConAlas`, `VuelaConCohete`, `PagarConTarjeta`, `PagarConPayPal`
*   **Context (Contexto):**
    *   Mantiene una referencia a un objeto `Strategy`.
    *   Puede definir una interfaz que permite a la `Strategy` acceder a sus datos si es necesario.
    *   Delega la ejecución del algoritmo a su objeto `Strategy` enlazado.
    *   *Ejemplo:* `Pato` (que vuela), `CarritoDeCompras` (que paga)

**Relación:** El `Context` tiene una relación de "has-a" con la `Strategy`. El `Client` crea el `Context` y le pasa la `Strategy` que debe usar.

### 6. Mini ejemplo

Vamos a simular diferentes formas en que un "Contexto" (`Calculadora`) puede realizar una operación matemática (`Estrategia`).

```java
// 1. Interfaz Strategy: Define el comportamiento común
interface OperationStrategy {
    int doOperation(int num1, int num2);
}

// 2. Concrete Strategy: Implementa las diferentes formas de comportamiento
class Addition implements OperationStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements OperationStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements OperationStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

// 3. Contexto: Utiliza una estrategia para realizar la acción
class CalculatorContext {
    private OperationStrategy strategy;

    public CalculatorContext(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    // Permite cambiar la estrategia en tiempo de ejecución
    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

// Uso en Main
public class Main {
    public static void main(String[] args) {
        // Usamos la estrategia de Suma
        CalculatorContext calculator = new CalculatorContext(new Addition());
        System.out.println("10 + 5 = " + calculator.executeStrategy(10, 5)); // Output: 10 + 5 = 15

        // Cambiamos a la estrategia de Resta
        calculator.setStrategy(new Subtraction());
        System.out.println("10 - 5 = " + calculator.executeStrategy(10, 5)); // Output: 10 - 5 = 5

        // Cambiamos a la estrategia de Multiplicación
        calculator.setStrategy(new Multiplication());
        System.out.println("10 * 5 = " + calculator.executeStrategy(10, 5)); // Output: 10 * 5 = 50
    }
}
```

En este ejemplo:
*   **`OperationStrategy`** es la interfaz que define cómo se hace una operación.
*   **`Addition`**, **`Subtraction`** y **`Multiplication`** son las estrategias concretas, cada una implementando una operación diferente.
*   **`CalculatorContext`** es el contexto. Tiene una referencia a una `OperationStrategy` y delega la ejecución de la operación a esa estrategia.
*   El `Main` (cliente) puede cambiar la estrategia de la `CalculatorContext` en cualquier momento, lo que modifica el comportamiento de la calculadora sin modificar su código interno.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Añadir nuevos `ConcreteStrategy`s:** Puedes añadir nuevos algoritmos (ej: `Division`) sin modificar la interfaz `Strategy` ni la clase `Context`.
    *   **Cambiar la `ConcreteStrategy` de un `Context` en tiempo de ejecución:** Esto permite un comportamiento dinámico.
    *   **La lógica interna de cada `ConcreteStrategy`:** Mientras implemente la interfaz `Strategy`, puede tener cualquier lógica interna.
*   **No debes cambiar:**
    *   **La interfaz `Strategy`:** Es el contrato común para todos los algoritmos.
    *   **La responsabilidad del `Context`:** El Contexto debe encargarse de *usar* la estrategia, no de *implementarla* directamente. No debe saber los detalles de las estrategias concretas.

### 8. Cuándo usarlo (muy importante)

*   **Cuando quieres usar diferentes variantes de un algoritmo dentro de un objeto:** Y quieres que el algoritmo pueda ser intercambiado en tiempo de ejecución.
*   **Cuando un objeto tiene muchos comportamientos que se pueden organizar como jerarquías de clases separadas.** Evita los `if/else if/switch` grandes.
*   **Para aislar la lógica de negocio específica de la clase que la usa.** Mantiene el `Context` más limpio.
*   **Cuando múltiples clases tienen un comportamiento similar pero con pequeñas diferencias:** Puedes extraer ese comportamiento en estrategias.
*   **Tipo parcial:** Si te preguntan cómo implementar diferentes formas de calcular impuestos, diferentes algoritmos de ordenación, o diferentes formas de pago.

### 9. Resumen en una frase

El patrón Strategy permite encapsular algoritmos intercambiables en clases separadas, permitiendo que un objeto cambie su comportamiento dinámicamente delegando la ejecución a la estrategia actual.

### 10. Error común

Un error común es sobrecomplicar el patrón creando interfaces `Strategy` demasiado granulares o con demasiados métodos. A veces, un simple `Function` o `Consumer` en Java puede ser suficiente si la lógica es muy sencilla. Otro error es que el `Context` conozca demasiado las `ConcreteStrategy`s, lo que va en contra del principio de encapsulamiento.
```