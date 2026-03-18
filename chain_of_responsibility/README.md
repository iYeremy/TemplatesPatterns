# Patrón de Diseño: Chain of Responsibility

### Cadena de Responsabilidad (Chain of Responsibility)

### 2. ¿Qué problema resuelve?

Imagina que tienes una solicitud (por ejemplo, una petición de compra o un evento en un sistema) y hay varios objetos que podrían procesarla, pero no sabes de antemano cuál de ellos será el adecuado. Si intentas manejar esto con un montón de `if/else if` anidados o un `switch` gigante, el código se vuelve difícil de leer, expandir y mantener. Cada vez que añades un nuevo tipo de manejador, tienes que modificar esa lógica central.

El patrón Chain of Responsibility resuelve este problema.

### 3. Idea principal (explicación corta)

El patrón Chain of Responsibility te permite pasar una solicitud a través de una cadena de "manejadores" potenciales. Cada manejador decide si procesa la solicitud o si la pasa al siguiente manejador de la cadena.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes una solicitud (Request) que necesita ser procesada.** Por ejemplo, una `PeticionDeCompra`.
2.  **Creas una interfaz para un "manejador" (Handler).** Esta interfaz define un método `handleRequest()` que toma la solicitud y posiblemente un método para establecer el siguiente manejador (`setNext()`).
3.  **Implementas varios "manejadores concretos" (Concrete Handlers).** Cada uno sabe cómo procesar un tipo específico de solicitud o una parte de ella.
4.  **Cada manejador concreto decide:**
    *   Si *puede* procesar la solicitud, lo hace.
    *   Si *no puede* (o no debe) procesar la solicitud, se la pasa al `siguienteManejador` en la cadena.
5.  **El cliente envía la solicitud al primer manejador de la cadena.** Si nadie la procesa, la solicitud puede quedar sin manejar o llegar a un manejador por defecto al final.

### 5. Estructura (clases y relaciones)

*   **Handler (Manejador):**
    *   Define la interfaz para manejar las solicitudes.
    *   Típicamente, tiene un método para manejar la solicitud y otro para establecer el siguiente manejador (`setNextHandler`).
    *   *Ejemplo:* `Aprobador`
*   **Concrete Handler (Manejador Concreto):**
    *   Implementa la interfaz `Handler`.
    *   Contiene la lógica para procesar la solicitud.
    *   Si puede procesar la solicitud, lo hace; de lo contrario, la pasa al siguiente manejador.
    *   Mantiene una referencia opcional a su `siguienteManejador`.
    *   *Ejemplo:* `Gerente`, `Director`, `Vicepresidente` (cada uno aprueba montos diferentes)
*   **Client (Cliente):**
    *   Crea la cadena de manejadores (generalmente solo la primera vez).
    *   Envía la solicitud al primer manejador de la cadena.
    *   *Ejemplo:* `Usuario` que envía una `PeticionDeCompra`.

**Relación:** Los `ConcreteHandler`s se conectan entre sí formando una lista enlazada (la "cadena"). El `Client` solo conoce el primer `Handler`.

### 6. Mini ejemplo

Imaginemos un sistema donde los empleados piden vacaciones, y un jefe diferente aprueba según la cantidad de días.

```java
// Clase para la solicitud (Request)
class Request {
    private int days;

    public Request(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}

// Interfaz Handler
interface Handler {
    void setNextHandler(Handler nextHandler);
    void handleRequest(Request request);
}

// Concrete Handler: Aprobador de Nivel 1 (ej: Gerente)
class ConcreteHandlerA implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getDays() <= 5) {
            System.out.println("Solicitud de " + request.getDays() + " días aprobada por el Gerente.");
        } else if (nextHandler != null) {
            System.out.println("Gerente no puede aprobar " + request.getDays() + " días. Pasando al siguiente aprobador.");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Solicitud de " + request.getDays() + " días no aprobada por nadie.");
        }
    }
}

// Concrete Handler: Aprobador de Nivel 2 (ej: Director)
class ConcreteHandlerB implements Handler {
    private Handler nextHandler;

    @Override
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(Request request) {
        if (request.getDays() <= 10) {
            System.out.println("Solicitud de " + request.getDays() + " días aprobada por el Director.");
        } else if (nextHandler != null) {
            System.out.println("Director no puede aprobar " + request.getDays() + " días. Pasando al siguiente aprobador.");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("Solicitud de " + request.getDays() + " días no aprobada por nadie.");
        }
    }
}

// Main (Cliente)
public class Main {
    public static void main(String[] args) {
        // Creamos los manejadores
        Handler gerente = new ConcreteHandlerA();
        Handler director = new ConcreteHandlerB();

        // Construimos la cadena: Gerente -> Director
        gerente.setNextHandler(director);

        // Enviamos solicitudes
        Request req1 = new Request(3); // Aprobada por Gerente
        Request req2 = new Request(7); // Aprobada por Director
        Request req3 = new Request(15); // No aprobada

        System.out.println("--- Solicitud 1 ---");
        gerente.handleRequest(req1); // La solicitud inicia en el Gerente

        System.out.println("
--- Solicitud 2 ---");
        gerente.handleRequest(req2); // La solicitud inicia en el Gerente

        System.out.println("
--- Solicitud 3 ---");
        gerente.handleRequest(req3); // La solicitud inicia en el Gerente
    }
}
```

En este ejemplo:
*   **`Request`** es la solicitud de vacaciones.
*   **`Handler`** define cómo se maneja una solicitud y cómo se pasa al siguiente.
*   **`ConcreteHandlerA` (Gerente)** puede aprobar hasta 5 días. Si no, la pasa.
*   **`ConcreteHandlerB` (Director)** puede aprobar hasta 10 días. Si no, la pasa (y como no hay más en la cadena, nadie la aprueba).
*   El `Main` (cliente) construye la cadena (`gerente.setNextHandler(director)`) y envía las solicitudes al primer manejador (`gerente.handleRequest(...)`).

Podemos añadir un `VicepresidenteHandler` fácilmente a la cadena sin modificar `Gerente` ni `Director`, simplemente en la configuración del `Main`.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Añadir nuevos `ConcreteHandler`s:** Es la gran ventaja. Puedes añadir nuevos tipos de procesadores a la cadena sin modificar los existentes.
    *   **Cambiar el orden de los manejadores:** Puedes configurar la cadena de diferentes maneras en tiempo de ejecución o en la inicialización.
    *   **La lógica interna de cada `ConcreteHandler`:** Mientras siga la interfaz `Handler`, cada uno puede decidir cómo procesar y cuándo pasar la solicitud.
*   **No debes cambiar:**
    *   **La interfaz `Handler`:** Esta define el contrato que permite que los manejadores se conecten y pasen solicitudes.
    *   **La responsabilidad de delegación:** Cada manejador *debe* decidir si procesa o pasa la solicitud. Si no se pasa la solicitud cuando no se procesa, la cadena se rompe.

### 8. Cuándo usarlo (muy importante)

*   **Cuando múltiples objetos pueden manejar una solicitud:** Y no sabes de antemano cuál será el más adecuado.
*   **Cuando quieres emitir una solicitud sin especificar el receptor explícitamente.**
*   **Cuando el conjunto de objetos que pueden manejar una solicitud debe ser especificable dinámicamente.** Por ejemplo, un flujo de trabajo que puede cambiar.
*   **Para implementar un sistema de permisos o niveles de aprobación.** Como en el ejemplo de las vacaciones.
*   **Tipo parcial:** Si ves que tienes una serie de condiciones `if/else if/else` muy largas para decidir quién procesa algo.

### 9. Resumen en una frase

El patrón Chain of Responsibility permite que una solicitud pase por una secuencia de objetos, cada uno decidiendo si la procesa o la pasa al siguiente.

### 10. Error común

El error común es hacer que los manejadores sepan demasiado sobre la estructura de la cadena o sobre qué otros manejadores existen. La gracia del patrón es que cada manejador solo necesita saber quién es *su* siguiente en la cadena, no la cadena completa. Además, a veces se olvida incluir la opción de que la solicitud quede sin manejar si llega al final de la cadena sin que nadie la haya procesado.
