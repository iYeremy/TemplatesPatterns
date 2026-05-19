# Patrón de Diseño: Command

### Comando (Command)

### 2. ¿Qué problema resuelve?

Imagina que quieres crear un sistema de botones en una interfaz gráfica. Un botón "Guardar" debe ejecutar la lógica de guardar, pero el botón no debería conocer los detalles de cómo se guarda. Además, ¿qué pasa si quieres agregar undo/redo, cola de comandos o logging? El patrón Command resuelve esto encapsulando cada petición como un objeto independiente.

### 3. Idea principal (explicación corta)

El patrón Command convierte una petición en un objeto independiente que contiene toda la información sobre esa petición. Esto permite parametrizar métodos con diferentes comandos, encolar peticiones o deshacer operaciones.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines la interfaz Command.** Con un método `ejecutar()`.
2.  **Creas el Receiver.** Es quien sabe cómo realizar la operación real.
3.  **Creas ConcreteCommand.** Vincula un Receiver con la acción, almacenando una referencia a él.
4.  **Creas el Invoker.** Recibe un Command y lo ejecuta sin saber qué hace.
5.  **El Cliente** configura el Command con su Receiver y se lo pasa al Invoker.

### 5. Estructura (clases y relaciones)

*   **Command:**
    *   Interfaz que declara el método de ejecución.
    *   *Ejemplo:* `Command` con `ejecutar()`
*   **ConcreteCommand:**
    *   Implementa `Command`.
    *   Mantiene referencia al `Receiver`.
    *   En `ejecutar()`, llama al método apropiado del `Receiver`.
    *   *Ejemplo:* `ConcreteCommand`
*   **Receiver:**
    *   Sabe cómo realizar la operación real.
    *   Cualquier clase puede ser un Receiver.
    *   *Ejemplo:* `Receiver` con `accion()`
*   **Invoker:**
    *   Solicita la ejecución del Command.
    *   No conoce los detalles de la operación.
    *   Puede almacenar historial de commands.
    *   *Ejemplo:* `Invoker`

**Relación:** `Cliente` crea `ConcreteCommand` con un `Receiver`, lo pasa al `Invoker`. `Invoker` llama a `ejecutar()` sin saber qué hace.

### 6. Mini ejemplo

Vamos a crear un control remoto con comandos para encender/apagar un dispositivo.

```java
// 1. Command: Interfaz
interface Command {
    void ejecutar();
}

// 2. Receiver: Sabe como realizar la operacion
class Luz {
    public void encender() { System.out.println("Luz encendida"); }
    public void apagar() { System.out.println("Luz apagada"); }
}

// 3. ConcreteCommand: Encapsula la peticion
class EncenderCommand implements Command {
    private Luz luz;

    public EncenderCommand(Luz luz) { this.luz = luz; }

    @Override
    public void ejecutar() { luz.encender(); }
}

class ApagarCommand implements Command {
    private Luz luz;

    public ApagarCommand(Luz luz) { this.luz = luz; }

    @Override
    public void ejecutar() { luz.apagar(); }
}

// 4. Invoker: Ejecuta commands
class ControlRemoto {
    private Command command;

    public void setCommand(Command command) { this.command = command; }

    public void presionarBoton() { command.ejecutar(); }
}

// 5. Uso en Main
public class Main {
    public static void main(String[] args) {
        Luz luz = new Luz();

        ControlRemoto control = new ControlRemoto();

        control.setCommand(new EncenderCommand(luz));
        control.presionarBoton();

        control.setCommand(new ApagarCommand(luz));
        control.presionarBoton();
    }
}
```

En este ejemplo:
*   **`Command`** es la interfaz con `ejecutar()`.
*   **`Luz`** es el Receiver que sabe encenderse/apagarse.
*   **`EncenderCommand`** y **`ApagarCommand`** encapsulan las peticiones.
*   **`ControlRemoto`** es el Invoker que ejecuta sin saber qué hace el command.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevos Commands:** Sin modificar el Invoker ni el Receiver.
    *   **Agregar undo/redo:** Agregando método `deshacer()` a la interfaz Command.
    *   **Encolar commands:** El Invoker puede almacenar una lista de Commands.
    *   **Logging/Macro commands:** Combinar múltiples commands en uno solo.
*   **No debes cambiar:**
    *   **La interfaz `Command`:** Es el contrato entre Invoker y ConcreteCommand.
    *   **El desacoplamiento:** El Invoker nunca debe conocer al Receiver directamente.

### 8. Cuándo usarlo (muy importante)

*   **Cuando necesitas parametrizar objetos con operaciones:** Botones de UI, menús contextuales.
*   **Cuando quieres encolar operaciones o ejecutarlas en diferentes momentos.**
*   **Cuando necesitas soportar undo/redo.**
*   **Cuando quieres desacoplar el objeto que invoca del que realiza la operación.**
*   **Cuando necesitas logging o transacciones:** Puedes registrar commands ejecutados.

### 9. Resumen en una frase

El patrón Command encapsula una petición como un objeto, desacoplando quien invoca la operación de quien la ejecuta, permitiendo undo/redo, colas y parametrización de operaciones.
