# Patrón de Diseño: Observer

### Observador (Observer)

### 2. ¿Qué problema resuelve?

Imagina que tienes una aplicación de noticias. Cuando una noticia importante se publica (por ejemplo, "¡Oferta del día!"), quieres que varios componentes de tu aplicación se actualicen automáticamente: la pantalla principal, una notificación en la barra de estado, un widget en el escritorio, etc. Si cada vez que hay una noticia, tienes que ir actualizando manualmente cada uno de esos componentes, el código se vuelve enredado, difícil de mantener y propenso a errores. Además, si añades un nuevo componente que deba reaccionar, tienes que modificar la fuente de la noticia.

El patrón Observer resuelve este problema.

### 3. Idea principal (explicación corta)

El patrón Observer define una dependencia de uno a muchos entre objetos, de manera que cuando un objeto cambia de estado (el "Sujeto"), todos sus dependientes (los "Observadores") son notificados y actualizados automáticamente.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes un objeto que tiene un estado importante y que quieres que otros conozcan cuando cambie.** A este le llamamos "Sujeto" o "Publicador".
2.  **Tienes otros objetos que están interesados en los cambios de estado del Sujeto.** A estos les llamamos "Observadores" o "Suscriptores".
3.  **Los Observadores se "registran" en el Sujeto.** Es como suscribirse a un boletín de noticias.
4.  **Cuando el estado del Sujeto cambia, el Sujeto "notifica" a todos sus Observadores registrados.** Es como enviar el boletín a todos los suscriptores.
5.  **Cada Observador, al recibir la notificación, reacciona a su manera.** Actualiza su propia información o realiza alguna acción.

¡Así, el Sujeto no necesita saber nada específico sobre los Observadores, solo que tiene que notificarlos!

### 5. Estructura (clases y relaciones)

*   **Subject (Sujeto / Publicador):**
    *   Conoce a sus Observadores.
    *   Proporciona una interfaz para "adjuntar" (`attach` / `subscribe`), "desadjuntar" (`detach` / `unsubscribe`) y "notificar" (`notify`) a los Observadores.
    *   *Ejemplo:* `CanalDeNoticias`, `AlmacenDeDatos`
*   **ConcreteSubject (Sujeto Concreto):**
    *   Almacena el estado que interesa a los `ConcreteObserver`s.
    *   Envía una notificación a sus Observadores cuando su estado cambia.
    *   *Ejemplo:* `Noticiero`, `Stock`
*   **Observer (Observador / Suscriptor):**
    *   Define una interfaz para recibir notificaciones del `Subject`.
    *   Típicamente, tiene un método `update()`.
    *   *Ejemplo:* `LectorDeNoticias`, `SeguidorDeStock`
*   **ConcreteObserver (Observador Concreto):**
    *   Implementa la interfaz `Observer`.
    *   Mantiene una referencia al `ConcreteSubject` (opcional, para consultar su estado).
    *   Almacena el estado que debe ser consistente con el del `Subject`.
    *   Implementa el método `update()` para responder a las notificaciones.
    *   *Ejemplo:* `AppMovil`, `WidgetDeEscritorio`

**Relación:** El `Subject` mantiene una lista de `Observer`s. El `ConcreteSubject` notifica a los `ConcreteObserver`s cuando su estado cambia.

### 6. Mini ejemplo

Imaginemos un sistema donde un "Sujeto" emite un mensaje y varios "Observadores" lo reciben.

```java
import java.util.ArrayList;
import java.util.List;

// 1. Sujeto: Lo que se observa
interface Subject {
    void attach(Observer o);    // Registrar un observador
    void detach(Observer o);    // Desregistrar un observador
    void notifyObservers(); // Notificar a todos los observadores
}

// Sujeto Concreto: Mantiene un estado y notifica cuando cambia
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers(); // ¡El estado cambió, a notificar!
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(); // Llamamos al método update de cada observador
        }
    }
}

// 2. Observador: Reacciona a los cambios del sujeto
interface Observer {
    void update(); // Método llamado cuando el sujeto notifica
}

// Observador Concreto: Cada uno reacciona de una forma
class ConcreteObserver implements Observer {
    private String name;
    private ConcreteSubject subject; // Referencia al sujeto para obtener su estado

    public ConcreteObserver(String name, ConcreteSubject subject) {
        this.name = name;
        this.subject = subject;
        this.subject.attach(this); // El observador se registra en el sujeto
    }

    @Override
    public void update() {
        String subjectMessage = subject.getMessage(); // Obtiene el último mensaje del sujeto
        System.out.println(name + " ha recibido el mensaje: '" + subjectMessage + "'");
    }
}

// Uso en Main
public class Main {
    public static void main(String[] args) {
        ConcreteSubject mySubject = new ConcreteSubject();

        // Creamos Observadores y los registramos
        ConcreteObserver obs1 = new ConcreteObserver("Observador A", mySubject);
        ConcreteObserver obs2 = new ConcreteObserver("Observador B", mySubject);
        ConcreteObserver obs3 = new ConcreteObserver("Observador C", mySubject);

        System.out.println("Cambiando el mensaje por primera vez:");
        mySubject.setMessage("¡Hola a todos!"); // Esto notificará a los 3 observadores

        System.out.println("
Cambiando el mensaje por segunda vez:");
        mySubject.setMessage("Una nueva actualización."); // Esto volverá a notificar

        System.out.println("
Desadjuntando al Observador B:");
        mySubject.detach(obs2); // El observador B ya no recibirá notificaciones

        System.out.println("
Cambiando el mensaje por tercera vez:");
        mySubject.setMessage("Mensaje final."); // Solo notificará a A y C
    }
}
```

En este ejemplo:
*   **`ConcreteSubject`** (`mySubject`) es el que tiene el mensaje que cambia.
*   **`ConcreteObserver`** (`obs1`, `obs2`, `obs3`) son los que reaccionan a esos mensajes.
*   Cuando `mySubject.setMessage()` es llamado, automáticamente se llama a `notifyObservers()`, lo que a su vez llama a `update()` en cada observador registrado.
*   Podemos añadir o quitar observadores dinámicamente sin modificar la clase `ConcreteSubject`.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Añadir nuevos `ConcreteObserver`s:** Puedes añadir cualquier cantidad de observadores nuevos sin tocar el `Subject`.
    *   **Añadir nuevos `ConcreteSubject`s:** Puedes tener diferentes tipos de sujetos que emitan diferentes tipos de eventos.
    *   **La lógica interna de los `ConcreteObserver`s:** Cada observador puede reaccionar de una forma completamente diferente al mismo evento.
    *   **El mecanismo de notificación:** Puedes enviar datos específicos en la notificación (Patrón Push) o que el observador consulte el estado del sujeto (Patrón Pull).
*   **No debes cambiar:**
    *   **Las interfaces `Subject` y `Observer`:** Estas interfaces definen el contrato fundamental para la interacción de notificación.
    *   **La independencia del `Subject`:** El Sujeto no debe saber nada específico sobre la implementación de los Observadores, solo cómo notificarlos.

### 8. Cuándo usarlo (muy importante)

*   **Cuando un cambio en un objeto requiere que otros objetos cambien también:** Y no sabes cuántos objetos o cuáles serán.
*   **Cuando quieres que los objetos puedan reaccionar a eventos sin estar fuertemente acoplados a la fuente del evento.** El Sujeto no tiene que saber qué hacen los Observadores, solo que existen.
*   **Para implementar sistemas de eventos o sistemas de publicación/suscripción (Pub/Sub).**
*   **Cuando necesitas que las dos jerarquías (Sujetos y Observadores) evolucionen de forma independiente.**
*   **Tipo parcial:** Si te preguntan cómo hacer que múltiples componentes de la UI se actualicen cuando cambia un dato, o cómo implementar notificaciones en una aplicación.

### 9. Resumen en una frase

El patrón Observer permite que múltiples objetos dependan del estado de otro objeto, siendo notificados y actualizados automáticamente cuando este cambia, sin que la fuente del cambio los conozca directamente.

### 10. Error común

Un error común es crear un acoplamiento demasiado fuerte entre el `ConcreteSubject` y los `ConcreteObserver`s. El `Subject` no debería conocer los detalles internos de cómo un `Observer` reacciona. Otro error es no manejar correctamente el "desadjuntar" (`detach`) a los observadores, lo que puede llevar a problemas de memoria o a notificaciones a objetos ya no válidos.

```