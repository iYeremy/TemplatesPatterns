# Patrón de Diseño: Memento

### Memento (Memento)

### 2. ¿Qué problema resuelve?

Imagina un editor de texto donde quieres poder deshacer cambios. Necesitas guardar el estado del documento en diferentes puntos del tiempo para poder volver a ellos. Pero no quieres exponer los detalles internos del documento a otros objetos. El patrón Memento resuelve esto permitiendo guardar y restaurar el estado de un objeto sin violar su encapsulamiento.

### 3. Idea principal (explicación corta)

El patrón Memento captura y externaliza el estado interno de un objeto sin violar la encapsulación, de modo que el objeto puede ser restaurado a ese estado más tarde.

### 4. ¿Cómo funciona? (flujo simple)

1.  **El Originator** es el objeto cuyo estado quieres guardar.
2.  **Crea un Memento** con su estado actual cuando necesita guardar.
3.  **El Caretaker** almacena el Memento sin poder ver o modificar su contenido.
4.  **Cuando necesitas deshacer**, el Caretaker devuelve el Memento al Originator.
5.  **El Originator restaura** su estado desde el Memento.

### 5. Estructura (clases y relaciones)

*   **Memento:**
    *   Contiene el estado del Originator.
    *   Es inmutable una vez creado.
    *   Solo el Originator puede acceder a su contenido.
    *   *Ejemplo:* `Memento` con `estado`
*   **Originator:**
    *   Crea Mementos para guardar su estado.
    *   Usa Mementos para restaurar su estado.
    *   Es el único que puede leer/escribir en el Memento.
    *   *Ejemplo:* `Originator`
*   **Caretaker:**
    *   Gestiona (almacena/devuelve) los Mementos.
    *   NUNCA accede al contenido del Memento.
    *   Actúa como historial de snapshots.
    *   *Ejemplo:* `Caretaker` con stack de mementos

**Relación:** `Originator` crea `Memento` y lo pasa a `Caretaker`. Cuando necesita restaurar, `Caretaker` devuelve el `Memento` y `Originator` extrae el estado.

### 6. Mini ejemplo

Vamos a crear un historial para un juego donde el jugador puede guardar y cargar su progreso.

```java
// 1. Memento: snapshot del estado del juego
class Memento {
    private int nivel;
    private int vida;
    private int oro;

    public Memento(int nivel, int vida, int oro) {
        this.nivel = nivel;
        this.vida = vida;
        this.oro = oro;
    }

    // Package-private: solo Game puede acceder
    int getNivel() { return nivel; }
    int getVida() { return vida; }
    int getOro() { return oro; }
}

// 2. Originator: el juego
class Game {
    private int nivel = 1;
    private int vida = 100;
    private int oro = 0;

    public void jugar(int minutos) {
        nivel += minutos / 10;
        vida -= 10;
        oro += 50;
        System.out.println("Nivel: " + nivel + ", Vida: " + vida + ", Oro: " + oro);
    }

    public Memento guardar() {
        return new Memento(nivel, vida, oro);
    }

    public void cargar(Memento memento) {
        nivel = memento.getNivel();
        vida = memento.getVida();
        oro = memento.getOro();
        System.out.println("Cargado - Nivel: " + nivel + ", Vida: " + vida + ", Oro: " + oro);
    }
}

// 3. Caretaker: gestiona los saves
class SaveManager {
    private Memento ultimoSave;

    public void save(Memento memento) {
        ultimoSave = memento;
        System.out.println("Juego guardado!");
    }

    public Memento load() {
        System.out.println("Cargando ultimo save...");
        return ultimoSave;
    }
}

// 4. Uso en Main
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        SaveManager saveManager = new SaveManager();

        game.jugar(30);
        saveManager.save(game.guardar());

        game.jugar(20); // Pierde vida jugando

        // Algo malo paso, cargar el save
        game.cargar(saveManager.load());
    }
}
```

En este ejemplo:
*   **`Memento`** contiene nivel, vida y oro del juego.
*   **`Game`** es el Originator que guarda y carga su estado.
*   **`SaveManager`** es el Caretaker que gestiona los saves.
*   El `SaveManager` **nunca accede** directamente al estado del juego.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Múltiples mementos:** El Caretaker puede almacenar un historial completo (stack).
    *   **Estado complejo:** El Memento puede contener cualquier cantidad de datos.
    *   **Mementos nombrados:** Agregar nombres o timestamps a los mementos.
*   **No debes cambiar:**
    *   **La encapsulación del Memento:** Solo el Originator debe poder acceder al estado.
    *   **La inmutabilidad del Memento:** Una vez creado, no debe cambiar.

### 8. Cuándo usarlo (muy importante)

*   **Cuando necesitas implementar undo/redo.**
*   **Cuando quieres guardar snapshots del estado** sin exponer los detalles internos del objeto.
*   **Cuando el estado es complejo** y no quieres que otros objetos dependan de su estructura.
*   **Ejemplos típicos:** Editores de texto, juegos (saves), transacciones de base de datos, versiones de documentos.

### 9. Resumen en una frase

El patrón Memento permite guardar y restaurar el estado de un objeto sin violar su encapsulación, usando un objeto Memento que solo el Originator puede leer.
