# Patrón de Diseño: Flyweight

### Peso Ligero (Flyweight)

### 2. ¿Qué problema resuelve?

Imagina un juego de guerra donde tienes miles de soldados en pantalla. Cada soldado necesita tener datos como su posición, estado de salud (datos extrínsecos, que cambian), pero también datos como el tipo de arma, el uniforme, la textura de su modelo 3D (datos intrínsecos, que son los mismos para todos los soldados de un mismo tipo). Si cada objeto `Soldado` almacena *todos* estos datos, terminarás con un consumo de memoria enorme y tu juego irá lento.

El patrón Flyweight resuelve este problema, especialmente cuando necesitas muchos objetos con muchas características en común.

### 3. Idea principal (explicación corta)

El patrón Flyweight te permite optimizar el uso de memoria compartiendo la mayor cantidad de datos posible entre objetos. Para lograr esto, separa la información que es *común* a muchos objetos (intrínseca) de la que es *única* a cada objeto (extrínseca).

### 4. ¿Cómo funciona? (flujo simple)

1.  **Identifica qué datos son "intrínsecos" y cuáles "extrínsecos".**
    *   **Intrínsecos:** Comunes a muchos objetos (ej: tipo de arma, uniforme del soldado). Son inmutables.
    *   **Extrínsecos:** Únicos de cada objeto (ej: posición X, Y, Z del soldado, su salud). Son mutables.
2.  **Crea un objeto "peso ligero" (Flyweight) para los datos intrínsecos.** Este objeto contendrá los datos compartidos.
3.  **Crea una "fábrica de pesos ligeros" (Flyweight Factory).** Esta fábrica se encarga de crear o reutilizar los objetos `Flyweight`. Si le pides un `SoldadoTipoRifle`, y ya tiene uno, te devuelve el existente en lugar de crear uno nuevo.
4.  **Cada objeto individual (Contexto) solo almacenará sus datos extrínsecos.** Y una referencia a uno de los objetos `Flyweight` compartidos.
5.  **Cuando un objeto individual necesita hacer algo,** llama a su `Flyweight` y le pasa sus datos extrínsecos para que el `Flyweight` pueda actuar con la información completa.

### 5. Estructura (clases y relaciones)

*   **Flyweight (Peso Ligero):**
    *   Define la interfaz para los objetos peso ligero.
    *   Contiene los datos intrínsecos (compartidos).
    *   Puede tener un método `operation()` que toma los datos extrínsecos como parámetro.
    *   *Ejemplo:* `TipoSoldado` (con tipo de arma, uniforme)
*   **ConcreteFlyweight (Peso Ligero Concreto):**
    *   Implementa la interfaz `Flyweight`.
    *   Almacena los datos intrínsecos y, opcionalmente, implementa el método `operation()`.
    *   *Ejemplo:* `SoldadoRifle`, `SoldadoFrancotirador`
*   **UnsharedConcreteFlyweight:**
    *   (Opcional) Para objetos que no pueden ser compartidos o tienen muchos datos extrínsecos. Se usan raramente.
*   **FlyweightFactory (Fábrica de Pesos Ligeros):**
    *   Crea y gestiona los objetos `Flyweight`.
    *   Asegura que los objetos `Flyweight` se compartan correctamente, devolviendo instancias existentes cuando sea posible.
    *   *Ejemplo:* `FabricaDeSoldados`
*   **Client (Cliente):**
    *   Crea y gestiona los objetos que contienen los datos extrínsecos.
    *   Solicita objetos `Flyweight` a la `FlyweightFactory`.
    *   *Ejemplo:* El bucle principal del juego que crea `SoldadoEnCampoDeBatalla` (que tiene posición, salud) y le asigna un `TipoSoldado`.

**Relación:** El `Client` utiliza la `FlyweightFactory` para obtener instancias de `Flyweight` que luego son usadas por los objetos `Context` (que contienen los datos extrínsecos).

### 6. Mini ejemplo

Imagina un sistema de caracteres de texto en un editor. Para cada letra mostrada (un `CharacterContext`), no queremos que cada una almacene la fuente, el tamaño, el color, etc., si es la misma para muchas letras.

```java
import java.util.HashMap;
import java.util.Map;

// Interfaz Flyweight: define la interfaz para los objetos compartidos
interface Flyweight {
    void display(int x, int y); // Los datos extrínsecos (posición) se pasan aquí
}

// Concrete Flyweight: implementa el Flyweight y contiene el estado intrínseco
class ConcreteFlyweight implements Flyweight {
    private char character; // Estado intrínseco (la letra en sí)
    private String font;    // Estado intrínseco (fuente)
    private int size;       // Estado intrínseco (tamaño)

    public ConcreteFlyweight(char character, String font, int size) {
        this.character = character;
        this.font = font;
        this.size = size;
        System.out.println("Creando Flyweight para: " + character + ", " + font + ", " + size);
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Mostrando '" + character + "' en (" + x + "," + y + ") con fuente " + font + ", tamaño " + size);
    }
}

// Flyweight Factory: crea y gestiona los objetos Flyweight
class FlyweightFactory {
    private static final Map<String, Flyweight> flyweights = new HashMap<>();

    public static Flyweight getFlyweight(char character, String font, int size) {
        String key = "" + character + font + size; // Clave para identificar el Flyweight
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(character, font, size));
        }
        return flyweights.get(key);
    }
}

// Contexto (Cliente): contiene el estado extrínseco y usa el Flyweight
class CharacterContext {
    private Flyweight flyweight;
    private int x; // Posición X (estado extrínseco)
    private int y; // Posición Y (estado extrínseco)

    public CharacterContext(char character, String font, int size, int x, int y) {
        this.flyweight = FlyweightFactory.getFlyweight(character, font, size);
        this.x = x;
        this.y = y;
    }

    public void display() {
        flyweight.display(x, y); // El Flyweight usa los datos extrínsecos para su operación
    }
}

// Main (Cliente)
public class Main {
    public static void main(String[] args) {
        // Cientos de caracteres compartiendo los mismos objetos Flyweight
        CharacterContext c1 = new CharacterContext('A', "Arial", 12, 10, 20);
        CharacterContext c2 = new CharacterContext('B', "Arial", 12, 30, 20);
        CharacterContext c3 = new CharacterContext('A', "Arial", 12, 50, 20); // Reutiliza el Flyweight de 'A'
        CharacterContext c4 = new CharacterContext('C', "Times New Roman", 14, 70, 20);

        c1.display();
        c2.display();
        c3.display();
        c4.display();

        // Verificamos cuántos Flyweights se crearon realmente
        System.out.println("
Número total de Flyweights creados: " + FlyweightFactory.flyweights.size());
    }
}
```

En este ejemplo:
*   **`Flyweight`** es la interfaz común.
*   **`ConcreteFlyweight`** almacena el carácter, fuente y tamaño (datos intrínsecos). Notarás que solo se imprimen los mensajes "Creando Flyweight para..." una vez por combinación única de carácter, fuente y tamaño.
*   **`FlyweightFactory`** es la que se encarga de que los `ConcreteFlyweight` se reutilicen.
*   **`CharacterContext`** almacena la posición (datos extrínsecos) y tiene una referencia a un `Flyweight`.
*   El `Main` (cliente) crea múltiples `CharacterContext`s, pero solo se crean unos pocos objetos `ConcreteFlyweight`, ahorrando memoria.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Los datos extrínsecos:** Estos son únicos para cada instancia y pueden variar sin afectar a los objetos `Flyweight` compartidos.
    *   **Añadir nuevos `ConcreteFlyweight`s:** Si necesitas nuevos tipos de objetos compartidos, puedes crearlos y la fábrica los gestionará.
    *   **La forma en que se gestiona la fábrica:** Podrías cambiar el mecanismo de almacenamiento de la fábrica (ej: usar una caché de expiración).
*   **No debes cambiar:**
    *   **Los datos intrínsecos dentro de un `ConcreteFlyweight`:** Una vez creado, el estado intrínseco de un `Flyweight` debe ser inmutable. Si cambias un dato intrínseco, cambiará para *todos* los objetos que lo compartan.
    *   **La interfaz `Flyweight`:** Esta define el contrato para los objetos compartidos.

### 8. Cuándo usarlo (muy importante)

*   **Cuando una aplicación usa una gran cantidad de objetos:** Y el costo de almacenamiento de estos objetos es muy alto.
*   **Cuando la mayoría de los estados de los objetos pueden hacerse extrínsecos:** Es decir, se pueden pasar como parámetros a los métodos de los objetos `Flyweight`.
*   **Cuando muchos grupos de objetos comparten datos intrínsecos comunes:** Y las variaciones son solo en los datos extrínsecos.
*   **Tipo parcial:** Si en un examen te preguntan cómo reducir el consumo de memoria en una aplicación con muchos objetos parecidos, o si necesitas representar miles de elementos visuales (árboles, edificios, soldados) de un mismo tipo.

### 9. Resumen en una frase

El patrón Flyweight reduce la cantidad de objetos creados compartiendo datos intrínsecos comunes entre múltiples objetos, ahorrando memoria y mejorando el rendimiento.

### 10. Error común

El error más común es no distinguir correctamente entre los datos intrínsecos (compartidos e inmutables) y los extrínsecos (únicos y mutables). Si se hace un `Flyweight` con datos extrínsecos, se perderá la ventaja del patrón y se introducirán errores, ya que un cambio en un "objeto compartido" afectaría a todos los que lo usan de forma inesperada.
