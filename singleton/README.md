# Patrón de Diseño: Singleton

### Instancia Única (Singleton)

### 2. ¿Qué problema resuelve?

Imagina que en tu aplicación tienes un gestor de configuración, un registro de eventos (logger) o una conexión a base de datos. Para este tipo de objetos, solo necesitas (y de hecho, solo *debes*) tener una única instancia en todo el sistema. Si permites que se creen múltiples instancias, podrías tener problemas como:

*   Conflictos de datos (varias configuraciones en memoria).
*   Consumo excesivo de recursos (múltiples conexiones a base de datos).
*   Resultados inconsistentes.

El patrón Singleton resuelve este problema asegurando que una clase tenga solo una instancia y proporcionando un punto de acceso global a ella.

### 3. Idea principal (explicación corta)

El patrón Singleton asegura que una clase específica tenga *una y solo una* instancia durante toda la ejecución de un programa, y proporciona una forma fácil y global de acceder a esa única instancia desde cualquier parte del código.

### 4. ¿Cómo funciona? (flujo simple)

1.  **Haces el constructor de la clase "privado".** Así, nadie desde fuera puede crear instancias de esa clase directamente con `new`.
2.  **Dentro de la propia clase, creas una instancia "estática" de sí misma.** Esta es la única instancia que existirá.
3.  **Proporcionas un método "estático" y "público"** (generalmente llamado `getInstance()`) que devuelve esa única instancia.
4.  **Cada vez que alguien quiera usar un objeto de esta clase, llamará a `getInstance()`** y siempre obtendrá la misma única instancia.

### 5. Estructura (clases y relaciones)

*   **Singleton:**
    *   Declara un constructor privado para que otras clases no puedan instanciarlo directamente.
    *   Declara un campo estático y privado para contener su única instancia.
    *   Proporciona un método de creación estático y público (`getInstance`) que se encarga de devolver la misma instancia de la clase.

**Relación:** Una clase `Singleton` es una entidad autocontenida que gestiona su propia creación y acceso. No interactúa directamente con otras interfaces o clases para su funcionamiento básico, sino que otras clases la usan.

### 6. Mini ejemplo

Vamos a crear un `ConfigurationManager` que solo debe tener una instancia.

```java
class ConfigurationManager {
    // 1. Instancia única de la clase (estática y privada)
    private static ConfigurationManager instance;

    // 2. Constructor privado para evitar la creación externa
    private ConfigurationManager() {
        System.out.println("ConfigurationManager: Inicializando...");
        // Aquí iría la lógica de carga de configuración (ej. leer un archivo)
    }

    // 3. Método estático y público para obtener la instancia
    public static ConfigurationManager getInstance() {
        if (instance == null) { // Si la instancia no existe, la creamos
            instance = new ConfigurationManager();
        }
        return instance; // Siempre devolvemos la misma instancia
    }

    public void loadSettings() {
        System.out.println("ConfigurationManager: Cargando configuraciones.");
        // Lógica para cargar settings
    }

    public void saveSettings() {
        System.out.println("ConfigurationManager: Guardando configuraciones.");
        // Lógica para guardar settings
    }
}

// Uso en Main
public class Main {
    public static void main(String[] args) {
        // Intentamos obtener la instancia varias veces
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        config1.loadSettings();

        ConfigurationManager config2 = ConfigurationManager.getInstance();
        config2.saveSettings();

        ConfigurationManager config3 = ConfigurationManager.getInstance();

        // Verificamos si todas las referencias apuntan a la misma instancia
        System.out.println("
¿config1 y config2 son la misma instancia? " + (config1 == config2));
        System.out.println("¿config1 y config3 son la misma instancia? " + (config1 == config3));
    }
}
```

En este ejemplo:
*   El mensaje "ConfigurationManager: Inicializando..." solo aparece *una vez*, lo que demuestra que solo se crea una instancia.
*   `config1`, `config2` y `config3` son, de hecho, referencias al *mismo* objeto en memoria.
*   Cualquier parte del código que necesite la configuración solo tiene que llamar a `ConfigurationManager.getInstance()`.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **La lógica interna de la clase `Singleton`:** Puedes añadir o modificar métodos y propiedades, siempre que no afecten su característica de instancia única.
    *   **El momento de la inicialización (Lazy vs Eager):** En el ejemplo anterior, la instancia se crea la primera vez que se llama a `getInstance()` (Lazy Initialization). Podrías crearla en el momento en que se carga la clase (Eager Initialization) si siempre se va a necesitar.
*   **No debes cambiar:**
    *   **El constructor privado:** Es esencial para evitar la creación de múltiples instancias.
    *   **El método `getInstance()`:** Debe ser estático y público, y debe garantizar que siempre se devuelve la misma instancia.
    *   **La referencia estática a la instancia única:** Es el corazón del patrón.

### 8. Cuándo usarlo (muy importante)

*   **Cuando debe haber exactamente una instancia de una clase:** Y debe ser accesible desde un punto bien conocido (globalmente).
*   **Para gestionar recursos compartidos:** Como conexiones a bases de datos, pools de hilos, configuración global.
*   **Cuando necesitas un punto de control centralizado para un servicio:** Por ejemplo, un gestor de logs.
*   **Tipo parcial:** Si te preguntan cómo asegurarte de que una base de datos o un registro de eventos solo exista una vez en toda la aplicación.

### 9. Resumen en una frase

El patrón Singleton garantiza que una clase tenga una única instancia y proporciona un punto de acceso global a ella.

### 10. Error común

Un error común es abusar del Singleton, usándolo para clases que realmente no necesitan ser únicas. Esto puede llevar a un código difícil de probar y con un acoplamiento alto. Otro error es no manejar correctamente la concurrencia en entornos multihilo, donde múltiples hilos podrían intentar crear la instancia al mismo tiempo, rompiendo la garantía de unicidad (esto se soluciona con técnicas como `double-checked locking` o `Enum Singleton`).
```