# Patrón de Diseño: Mediator

### Mediador (Mediator)

### 2. ¿Qué problema resuelve?

Imagina un chat grupal donde cada participante necesita enviar mensajes a todos los demás. Sin un mediador, cada persona tendría que conocer a todas las demás y enviarles mensajes individualmente. Esto crea un caos de conexiones. El patrón Mediator resuelve esto centralizando toda la comunicación en un solo objeto.

### 3. Idea principal (explicación corta)

El patrón Mediator define un objeto que encapsula cómo interactúan un conjunto de objetos. Los objetos (colegas) no se comunican directamente entre sí, sino a través del mediador, reduciendo el acoplamiento.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Defines la interfaz Mediator.** Con un método `notificar(colega, evento)`.
2.  **Creas la clase base Colega.** Mantiene referencia al mediador.
3.  **Creas Colegas concretos.** Cada uno tiene su comportamiento y notifica al mediador cuando algo pasa.
4.  **Creas el Mediator concreto.** Conoce a todos los colegas y decide cómo reaccionar ante cada evento.
5.  **Los colegas se comunican:** Envían eventos al mediador, quien los redistribuye a los colegas correspondientes.

### 5. Estructura (clases y relaciones)

*   **Mediator:**
    *   Interfaz que define la comunicación entre colegas.
    *   *Ejemplo:* `Mediator` con `notificar(Colega, String)`
*   **Colega (Colleague):**
    *   Clase base para todos los componentes que se comunican.
    *   Mantiene referencia al `Mediator`.
    *   *Ejemplo:* `Colega`
*   **ColegaConcreto (ConcreteColleague):**
    *   Implementaciones específicas de colegas.
    *   Notifican al mediator cuando algo pasa.
    *   Reciben mensajes del mediator.
    *   *Ejemplo:* `ColegaA`, `ColegaB`
*   **MediatorConcreto (ConcreteMediator):**
    *   Implementa la lógica de comunicación.
    *   Conoce a todos los colegas.
    *   Coordina las interacciones.
    *   *Ejemplo:* `MediatorConcreto`

**Relación:** Los `Colega` se comunican solo con el `Mediator`. El `MediatorConcreto` conoce a todos los `Colega` y coordina sus interacciones.

### 6. Mini ejemplo

Vamos a crear un sistema de control de tráfico aéreo donde aviones se comunican a través de una torre de control.

```java
// 1. Mediator: Interfaz
interface TorreControl {
    void registrarAvion(Avion avion);
    void notificarDespegue(Avion avion);
    void notificarAterrizaje(Avion avion);
}

// 2. Colega: Avion base
abstract class Avion {
    protected TorreControl torre;

    public Avion(TorreControl torre) { this.torre = torre; }

    abstract void despegar();
    abstract void aterrizar();
}

// 3. ColegaConcreto
class AvionComercial extends Avion {
    private String nombre;

    public AvionComercial(TorreControl torre, String nombre) {
        super(torre);
        this.nombre = nombre;
    }

    @Override
    void despegar() {
        System.out.println(nombre + " despega");
        torre.notificarDespegue(this);
    }

    @Override
    void aterrizar() {
        System.out.println(nombre + " aterriza");
        torre.notificarAterrizaje(this);
    }

    void recibirAlerta(String mensaje) {
        System.out.println(nombre + " recibio alerta: " + mensaje);
    }
}

// 4. MediatorConcreto
class TorreControlAeropuerto implements TorreControl {
    private List<Avion> aviones = new ArrayList<>();

    @Override
    public void registrarAvion(Avion avion) {
        aviones.add(avion);
    }

    @Override
    public void notificarDespegue(Avion avion) {
        for (Avion a : aviones) {
            if (a != avion && a instanceof AvionComercial) {
                ((AvionComercial) a).recibirAlerta(avion + " despego");
            }
        }
    }

    @Override
    public void notificarAterrizaje(Avion avion) {
        for (Avion a : aviones) {
            if (a != avion && a instanceof AvionComercial) {
                ((AvionComercial) a).recibirAlerta(avion + " aterrizo");
            }
        }
    }
}

// 5. Uso en Main
public class Main {
    public static void main(String[] args) {
        TorreControl torre = new TorreControlAeropuerto();

        Avion avion1 = new AvionComercial(torre, "Vuelo 101");
        Avion avion2 = new AvionComercial(torre, "Vuelo 202");

        torre.registrarAvion(avion1);
        torre.registrarAvion(avion2);

        avion1.despegar();
    }
}
```

En este ejemplo:
*   **`TorreControl`** es el mediador que coordina aviones.
*   **`Avion`** es la clase base colega.
*   **`AvionComercial`** notifica a la torre y recibe alertas.
*   La **`TorreControlAeropuerto`** redistribuye información entre aviones.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Agregar nuevos Colegas:** Sin modificar otros colegas.
    *   **La lógica del Mediator:** Cambiar cómo se coordinan las interacciones.
    *   **Agregar más eventos:** El mediator puede manejar más tipos de comunicación.
*   **No debes cambiar:**
    *   **El principio de comunicación:** Los colegas NUNCA deben comunicarse directamente.
    *   **La interfaz del Mediator:** Es el contrato que todos los colegas usan.

### 8. Cuándo usarlo (muy importante)

*   **Cuando muchos objetos se comunican entre sí de forma compleja:** El mediator centraliza la lógica.
*   **Cuando quieres evitar acoplamiento directo entre componentes:** Los colegas no deben conocerse.
*   **Cuando la comunicación sigue patrones predecibles:** El mediator puede encapsular estos patrones.
*   **Ejemplos típicos:** Sistemas de chat, UI con componentes interdependientes, control de tráfico, workflows.

### 9. Resumen en una frase

El patrón Mediator centraliza la comunicación entre objetos en un solo mediador, evitando que se comuniquen directamente y reduciendo el acoplamiento entre ellos.
