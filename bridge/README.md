# Patrón de Diseño: Bridge

### Puente (Bridge)

### 2. ¿Qué problema resuelve?

Imagina que tienes una aplicación de dibujo. Podrías tener diferentes formas (Círculo, Cuadrado) y también diferentes formas de dibujarlas (con una API de gráficos, con OpenGL, etc.). Si intentas manejar esto creando una clase `CírculoOpenGL`, `CírculoGraphicsAPI`, `CuadradoOpenGL`, etc., tu código se vuelve un desastre enorme y difícil de mantener. Cada nueva forma o nueva forma de dibujar duplica el trabajo.

El patrón Bridge resuelve este problema.

### 3. Idea principal (explicación corta)

El patrón Bridge te permite separar una clase grande (que hace muchas cosas) en dos jerarquías independientes: una para las "abstracciones" (lo que *es*) y otra para las "implementaciones" (cómo *hace*). Estas dos se "conectan" a través de un "puente".

### 4. ¿Cómo funciona? (flujo simple)

1.  **Tienes una "cosa" (Abstracción) que necesita hacer algo.** Por ejemplo, una `Forma` (círculo, cuadrado).
2.  **Esa "cosa" no sabe *cómo* hacerlo, solo sabe *que* tiene que hacerlo.** La `Forma` sabe que tiene que `dibujarse()`, pero no si es con OpenGL o una Graphics API.
3.  **Para el *cómo*, la "cosa" le pide a un "ayudante" (Implementador).** La `Forma` tiene una referencia a un `Dibujante`.
4.  **El "ayudante" (Implementador) sabe hacer el trabajo específico.** `DibujanteOpenGL` sabe dibujar usando OpenGL, y `DibujanteGraphicsAPI` sabe hacerlo con la Graphics API.
5.  **Cuando la "cosa" quiere hacer algo, le dice a su "ayudante" que lo haga.** La `Forma` llama a `dibujante.dibujarAlgo()`.

¡Así, puedes cambiar la `Forma` sin afectar al `Dibujante`, y viceversa!

### 5. Estructura (clases y relaciones)

*   **Abstracción (Abstraction):**
    *   Define la interfaz de alto nivel para el cliente.
    *   Contiene una referencia al `Implementador`.
    *   *Ejemplo:* `Forma`
*   **Abstracción Refinada (RefinedAbstraction):**
    *   Extensiones de la `Abstracción`.
    *   *Ejemplo:* `Círculo`, `Cuadrado`
*   **Implementador (Implementor):**
    *   Define la interfaz para las clases de implementación. No tiene por qué coincidir con la interfaz de la `Abstracción`.
    *   *Ejemplo:* `Dibujante`
*   **Implementadores Concretos (ConcreteImplementor):**
    *   Implementan la interfaz del `Implementador`.
    *   *Ejemplo:* `DibujanteOpenGL`, `DibujanteGraphicsAPI`

**Relación:** La `Abstracción` usa (tiene una referencia a) el `Implementador`. El cliente interactúa con la `Abstracción`.

### 6. Mini ejemplo

Imaginemos que tenemos la clase `Abstraction` que usa un `Implementor`.

```java
// Implementador: Cómo se hacen las cosas
interface Implementor {
    void operationImpl();
}

// Implementador Concreto A: Una forma específica de hacer las cosas
class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("Soy el implementador concreto A");
    }
}

// Implementador Concreto B: Otra forma específica de hacer las cosas
class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImpl() {
        System.out.println("Soy el implementador concreto B");
    }
}

// Abstracción: Lo que se va a hacer, y delega el cómo
abstract class Abstraction {
    protected Implementor implementor; // Aquí está el "puente"

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}

// Abstracción Refinada: Una versión específica de la abstracción
class RefinedAbstraction extends Abstraction {
    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.print("Operación de RefinedAbstraction: ");
        implementor.operationImpl(); // Delega en el implementador
    }
}

// Uso en Main
public class Main {
    public static void main(String[] args) {
        // Creamos una abstracción con un implementador A
        Abstraction absA = new RefinedAbstraction(new ConcreteImplementorA());
        absA.operation(); // Output: Operación de RefinedAbstraction: Soy el implementador concreto A

        // Creamos la misma abstracción con un implementador B
        Abstraction absB = new RefinedAbstraction(new ConcreteImplementorB());
        absB.operation(); // Output: Operación de RefinedAbstraction: Soy el implementador concreto B
    }
}
```

En este ejemplo:
*   **`Implementor`** y sus implementaciones (`ConcreteImplementorA`, `ConcreteImplementorB`) son la parte del *cómo*.
*   **`Abstraction`** y `RefinedAbstraction` son la parte del *qué*.
*   El constructor de `Abstraction` es donde se pasa el `Implementor`, creando el "puente". Esto permite que `RefinedAbstraction` use cualquier `Implementor` sin cambiar su propio código.

### 7. ¿Qué se puede cambiar y qué no?

*   **Puedes cambiar:**
    *   **Nuevas abstracciones:** Puedes crear `RefinedAbstraction`s nuevas sin tocar los `Implementor`s. Por ejemplo, una `Forma3D` que use los mismos dibujantes.
    *   **Nuevos implementadores:** Puedes añadir `ConcreteImplementor`s nuevos (como un `DibujanteVR`) sin tocar las `Abstracciones`.
    *   **La lógica interna de las clases:** Mientras respeten la interfaz, cada `ConcreteImplementor` o `RefinedAbstraction` puede tener su propia lógica.
*   **No debes cambiar:**
    *   **La relación entre Abstracción e Implementador:** La `Abstracción` *siempre* debe tener una referencia a un `Implementador` y delegar el trabajo importante en él.
    *   **Las interfaces `Abstraction` e `Implementor`:** Estas definen el contrato entre las dos jerarquías. Modificarlas puede romper la flexibilidad.

### 8. Cuándo usarlo (muy importante)

*   **Cuando quieres evitar una explosión de clases:** Si ves que estás creando `ClaseA_VarianteX`, `ClaseA_VarianteY`, `ClaseB_VarianteX`, `ClaseB_VarianteY`... ¡es una señal clara!
*   **Cuando quieres que un objeto cambie su "comportamiento" en tiempo de ejecución:** Puedes cambiar el `Implementor` de una `Abstracción` dinámicamente.
*   **Cuando necesitas que dos jerarquías evolucionen de forma independiente:** Si sabes que las "cosas" y las "formas de hacer" van a crecer por separado.
*   **Tipo parcial:** Si te encuentras con un problema donde tienes "varias cosas" que pueden hacerse de "varias formas", y quieres combinarlas sin generar un montón de clases duplicadas.

### 9. Resumen en una frase

El patrón Bridge desacopla una abstracción de su implementación para que ambas puedan variar independientemente.

### 10. Error común

NO CONFUNDIR Bridge con Strategy. La diferencia clave es que Bridge se enfoca en separar una clase grande en *dos jerarquías completamente independientes* que pueden evolucionar por separado, mientras que Strategy se enfoca más en cambiar un *algoritmo específico* de una clase en tiempo de ejecución. En Bridge, la "abstracción" casi siempre tiene un ciclo de vida con su "implementación", y es una relación más fuerte de composición. En Strategy, el "contexto" puede cambiar su "estrategia" más libremente.
